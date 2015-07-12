package fr.lule.microetl.core;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.lule.microetl.core.exception.TransformException;

/**
 * Abstract class for all components.
 */
public abstract class AbstractComponent implements Component {

	private static Logger logger = LogManager.getLogger(AbstractComponent.class);

	private static final ExecutorService POOL = Executors.newFixedThreadPool(8);

	private final FutureTask<Integer> future = new FutureTask<Integer>(new Worker());

	private class Worker implements Callable<Integer> {

		@Override
		public Integer call() throws Exception {
			logger.debug("[{}] starts", this);
			doJob();
			logger.debug("[{}] finished", this);
			return 0;
		}

	}

	/**
	 * Component's job.
	 * 
	 * @throws TransformException
	 */
	protected abstract void doJob() throws TransformException;

	/**
	 * Starts the component.
	 */
	protected final void startComponent() {
		POOL.execute(future);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lule.microetl.core.Component#waitForFinish()
	 */
	@Override
	public final void waitForFinish() throws TransformException {
		try {
			future.get();
		} catch (InterruptedException e) {
			return;
		} catch (ExecutionException e) {
			if (e.getCause() instanceof TransformException) {
				throw (TransformException) e.getCause();
			} else if (e.getCause() instanceof RuntimeException) {
				throw (RuntimeException) e.getCause();
			} else {
				// should never happen
				throw new RuntimeException(e.getCause());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lule.microetl.core.Component#waitForAllFinish()
	 */
	@Override
	public void waitForAllFinish() throws TransformException {
		waitForFinish();
	}

}
