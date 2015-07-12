package fr.lule.microetl.extract;

import java.util.List;

import fr.lule.microetl.core.AbstractSource;
import fr.lule.microetl.core.Packet;
import fr.lule.microetl.core.exception.TransformException;

/**
 * A source component that extract data from a list given to the constructor.
 */
public class StaticSource extends AbstractSource {
	
	private List<Packet> data;
	
	/**
	 * Constructor.
	 * @param data the data to extract.
	 */
	public StaticSource(List<Packet> data) {
		super();
		this.data = data;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lule.microetl.core.AbstractSource#extract()
	 */
	@Override
	public void extract() throws TransformException {
		for (Packet packet : data) {
			sendToReceivers(packet);
		}
	}

}
