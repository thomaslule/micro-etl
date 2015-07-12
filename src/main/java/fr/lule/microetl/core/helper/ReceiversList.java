package fr.lule.microetl.core.helper;

import java.util.ArrayList;
import java.util.List;

import fr.lule.microetl.core.Packet;
import fr.lule.microetl.core.Receiver;
import fr.lule.microetl.core.Transformer;
import fr.lule.microetl.core.exception.TransformException;

/**
 * Helper class to handle a list of receivers.
 */
public class ReceiversList {

	private List<Receiver> receivers = new ArrayList<Receiver>();

	/**
	 * Add a receiver to the list.
	 * 
	 * @param target
	 *            the receiver to add
	 * @return the receiver
	 */
	public Receiver add(Receiver target) {
		receivers.add(target);
		return target;
	}

	/**
	 * Add a receiver to the list.
	 * 
	 * @param target
	 *            the receiver to add
	 * @return the receiver
	 */
	public Transformer add(Transformer target) {
		receivers.add(target);
		return target;
	}

	/**
	 * Send a packet to all receivers.
	 * 
	 * @param packet
	 *            the packet to send
	 */
	public void receive(Packet packet) {
		for (Receiver receiver : receivers) {
			receiver.receive(packet);
		}
	}

	/**
	 * Send the end signal to all receivers.
	 */
	public void end() {
		for (Receiver receiver : receivers) {
			receiver.end();
		}
	}

	/**
	 * Wait that all the receivers and their successors finish.
	 * 
	 * @throws TransformException
	 */
	public void waitForAllFinish() throws TransformException {
		for (Receiver receiver : receivers) {
			receiver.waitForAllFinish();
		}
	}

}
