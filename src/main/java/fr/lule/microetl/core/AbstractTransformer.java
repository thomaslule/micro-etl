package fr.lule.microetl.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.lule.microetl.core.exception.TransformException;
import fr.lule.microetl.core.helper.ReceiversList;

/**
 * Abstract class for all transformer components.
 */
public abstract class AbstractTransformer extends AbstractReceiver implements Transformer {

	private static Logger logger = LogManager.getLogger(AbstractTransformer.class);

	private final ReceiversList receivers = new ReceiversList();

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lule.microetl.core.Sender#to(fr.lule.microetl.core.Receiver)
	 */
	@Override
	public final Receiver to(Receiver target) {
		return receivers.add(target);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lule.microetl.core.Sender#to(fr.lule.microetl.core.Transformer)
	 */
	@Override
	public final Transformer to(Transformer target) {
		return receivers.add(target);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lule.microetl.core.AbstractReceiver#waitForAllFinish()
	 */
	@Override
	public final void waitForAllFinish() throws TransformException {
		waitForFinish();
		receivers.waitForAllFinish();
	}

	@Override
	protected final void onFinishReceive() throws TransformException {
		onFinishTransform();
		receivers.end();
	}

	/**
	 * Actions to be executed after the last packet was received.
	 * 
	 * @throws TransformException
	 */
	protected void onFinishTransform() throws TransformException {
	};

	/**
	 * Send a packet to this component's receivers.
	 * 
	 * @param packet
	 *            the packet to send
	 */
	protected final void sendToReceivers(Packet packet) {
		logger.debug("[{}] sends packet [{}]", this, packet);
		receivers.receive(packet);
	}
}
