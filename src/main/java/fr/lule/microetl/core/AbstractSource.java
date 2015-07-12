package fr.lule.microetl.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.lule.microetl.core.exception.TransformException;
import fr.lule.microetl.core.helper.ReceiversList;

/**
 * Abstract class for all source components.
 */
public abstract class AbstractSource extends AbstractComponent implements Source {

	private static Logger logger = LogManager.getLogger(AbstractSource.class);

	private final ReceiversList receivers = new ReceiversList();

	/**
	 * Does the extract.
	 * 
	 * @throws TransformException
	 */
	protected abstract void extract() throws TransformException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lule.microetl.core.Sender#to(fr.lule.microetl.core.Receiver)
	 */
	@Override
	public Receiver to(Receiver target) {
		return receivers.add(target);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lule.microetl.core.Sender#to(fr.lule.microetl.core.Transformer)
	 */
	@Override
	public Transformer to(Transformer target) {
		return receivers.add(target);
	}

	@Override
	protected final void doJob() throws TransformException {
		extract();
		receivers.end();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lule.microetl.core.Source#startProcess()
	 */
	public void startProcess() throws TransformException {
		startComponent();
		waitForAllFinish();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lule.microetl.core.Component#waitForAllFinish()
	 */
	@Override
	public void waitForAllFinish() throws TransformException {
		waitForFinish();
		receivers.waitForAllFinish();
	}

	/**
	 * Send a packet to this component's receivers.
	 * 
	 * @param packet
	 *            the packet to send
	 */
	protected void sendToReceivers(Packet packet) {
		logger.debug("[{}] sends packet [{}]", this, packet.toString());
		receivers.receive(packet);
	}

}
