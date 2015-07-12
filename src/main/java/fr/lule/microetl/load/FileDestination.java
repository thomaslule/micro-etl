package fr.lule.microetl.load;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import fr.lule.microetl.core.AbstractReceiver;
import fr.lule.microetl.core.Packet;
import fr.lule.microetl.core.exception.TransformException;

/**
 * A destination component that loads data into a file.
 */
public class FileDestination extends AbstractReceiver {

	private PrintWriter writer;
	private String file;
	
	/**
	 * Constructor.
	 * @param file the path of file where to load the data.
	 * @throws TransformException
	 */
	public FileDestination(String file) throws TransformException {
		super();
		this.file = file;
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		} catch (IOException e) {
			throw new TransformException("Cannot write to file", e);
		}
	}

	@Override
	protected void onReceivePacket(Packet packet) throws TransformException {
		writer.println(String.join(";", packet.values()));
	}

	@Override
	protected void onFinishReceive() throws TransformException {
		writer.close();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileDestination " + file;
	}

}
