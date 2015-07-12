package fr.lule.microetl.load;

import java.util.ArrayList;
import java.util.List;

import fr.lule.microetl.core.AbstractReceiver;
import fr.lule.microetl.core.Packet;
import fr.lule.microetl.core.exception.TransformException;

/**
 * A destination component that loads data into a java List.
 */
public class ListDestination extends AbstractReceiver {

	private List<Packet> list = new ArrayList<Packet>();

	/**
	 * Get the resulting list.
	 * @return the list
	 */
	public List<Packet> getResult() {
		return list;
	}

	@Override
	protected void onReceivePacket(Packet packet) throws TransformException {
		list.add(packet);
	}

}
