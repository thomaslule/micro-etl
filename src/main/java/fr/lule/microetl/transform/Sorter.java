package fr.lule.microetl.transform;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import fr.lule.microetl.core.AbstractTransformer;
import fr.lule.microetl.core.Packet;
import fr.lule.microetl.core.exception.TransformException;

/**
 * A transformer component that sorts the data received.
 */
public class Sorter extends AbstractTransformer {

	private Comparator<Packet> comparator;
	private List<Packet> cache = new ArrayList<Packet>();

	/**
	 * Constructor.
	 * 
	 * @param comparator
	 *            the comparator to use for the sorting
	 */
	public Sorter(Comparator<Packet> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	protected void onReceivePacket(Packet packet) throws TransformException {
		cache.add(packet);
	}

	@Override
	protected void onFinishTransform() throws TransformException {
		cache.sort(comparator);
		for (Packet packetToSend : cache) {
			sendToReceivers(packetToSend);
		}
	}

}
