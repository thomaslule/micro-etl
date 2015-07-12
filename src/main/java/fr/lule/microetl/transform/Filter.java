package fr.lule.microetl.transform;

import fr.lule.microetl.core.AbstractTransformer;
import fr.lule.microetl.core.Packet;
import fr.lule.microetl.core.exception.TransformException;

/**
 * Use this class to filter the packets that transit through it.
 */
public class Filter extends AbstractTransformer {

	Filterer filterer;

	public interface Filterer {
		public boolean valid(Packet packet);
	}

	public Filter(Filterer filterer) {
		super();
		this.filterer = filterer;
	}

	@Override
	protected void onReceivePacket(Packet packet) throws TransformException {
		if (filterer.valid(packet)) {
			sendToReceivers(packet);
		}
	}

}
