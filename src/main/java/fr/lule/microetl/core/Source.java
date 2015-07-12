package fr.lule.microetl.core;

import fr.lule.microetl.core.exception.TransformException;

/**
 * A Source component.
 */
public interface Source extends Sender {

	/**
	 * Begin the ETL process.
	 * 
	 * @throws TransformException
	 */
	public void startProcess() throws TransformException;

}
