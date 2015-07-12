package fr.lule.microetl.core;

import fr.lule.microetl.core.exception.TransformException;

/**
 * Component that can receive packets.
 */
public interface Receiver extends Component {

	/**
	 * Receive a packet.
	 * 
	 * @param packet
	 * @throws TransformException
	 */
	public void receive(Packet packet);

	/**
	 * Indicate that there is no more packet to send.
	 */
	public void end();

}
