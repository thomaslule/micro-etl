package fr.lule.microetl.transform;

import fr.lule.microetl.core.AbstractTransformer;
import fr.lule.microetl.core.Packet;
import fr.lule.microetl.core.exception.TransformException;

/**
 * A transformer component that change all data values to uppercase.
 */
public class Capslockizer extends AbstractTransformer {
	
	@Override
	protected void onReceivePacket(Packet packet) throws TransformException {
		Packet newPacket = new Packet();
		packet.forEach((field, value) -> {
			newPacket.put(field, value.toUpperCase());
		});
		sendToReceivers(newPacket);
	}

}
