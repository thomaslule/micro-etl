package fr.lule.microetl.core;

import java.util.HashMap;

/**
 * A map of data.
 */
public class Packet extends HashMap<String, String> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Initialize a packet with arrays [key, value].
	 * @param dataCouples a list of arrays [key, value]
	 */
	public Packet(String[]... dataCouples) {
		super();
		for (String[] couple : dataCouples) {
			this.put(couple[0], couple[1]);
		}
	}

}
