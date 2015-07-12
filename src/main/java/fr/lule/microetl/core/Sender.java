package fr.lule.microetl.core;

/**
 * Component that can send packets.
 */
public interface Sender {

	/**
	 * Register a receiver for this component.
	 * 
	 * @param target
	 *            the receiver
	 * @return the receiver
	 */
	public Receiver to(Receiver target);

	/**
	 * Register a receiver for this component.
	 * 
	 * @param target
	 *            the receiver
	 * @return the receiver
	 */
	public Transformer to(Transformer target);

}
