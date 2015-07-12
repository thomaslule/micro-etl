package fr.lule.microetl.core;

import fr.lule.microetl.core.exception.TransformException;

/**
 * An ETL component.
 */
public interface Component {

	/**
	 * Blocking function until the component has finished its work.
	 */
	public void waitForFinish() throws TransformException;

	/**
	 * Blocking function until this component and all its successors finished
	 * their work.
	 */
	public void waitForAllFinish() throws TransformException;

}
