package fr.lule.microetl.core;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.lule.microetl.core.exception.TransformException;

/**
 * Abstract class for all receiver components.
 */
public abstract class AbstractReceiver extends AbstractComponent implements Receiver {

	private static final Packet STOP_PACKET = new Packet();

	private static Logger logger = LogManager.getLogger(AbstractReceiver.class);

	private volatile boolean started = false;
	private volatile BlockingQueue<Packet> queue = new LinkedBlockingQueue<Packet>();

	public AbstractReceiver() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lule.microetl.core.Receiver#receive(fr.lule.microetl.core.Packet)
	 */
	@Override
	public synchronized final void receive(Packet packet) {
		queue.add(packet);
		if (!started) {
			startComponent();
			started = true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lule.microetl.core.Receiver#end()
	 */
	@Override
	public synchronized final void end() {
		receive(STOP_PACKET);
	}

	@Override
	protected final void doJob() throws TransformException {
		try {
			Packet packet;
			while ((packet = queue.take()) != STOP_PACKET) {
				onReceivePacket(packet);
			}
		} catch (InterruptedException e) {
			logger.error("Component " + this.toString() + " has been interrupted");
			return;
		} finally {
			onFinishReceive();
		}
	}

	/**
	 * Called on each packet received by the component.
	 * 
	 * @throws TransformException
	 */
	protected abstract void onReceivePacket(Packet packet) throws TransformException;

	/**
	 * Called when the last packet has been received.
	 * 
	 * @throws TransformException
	 */
	protected void onFinishReceive() throws TransformException {
	}

}
