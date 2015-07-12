package fr.lule.microetl.extract;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import fr.lule.microetl.core.AbstractSource;
import fr.lule.microetl.core.Packet;
import fr.lule.microetl.core.exception.TransformException;

/**
 * A source component that extract data from a file.
 */
public class FileSource extends AbstractSource {

	private File file;
	private String[] fields;

	/**
	 * Constructor
	 * 
	 * @param file
	 *            the file to read
	 * @param fields
	 *            the list of fields
	 */
	public FileSource(String file, String[] fields) {
		super();
		this.file = new File(file);
		this.fields = fields;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lule.microetl.core.AbstractSource#extract()
	 */
	@Override
	protected void extract() throws TransformException {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				Packet packet = new Packet();
				String[] lineArray = line.split(";");
				for (int i = 0; i < lineArray.length; i++) {
					packet.put(fields[i], lineArray[i]);
				}
				sendToReceivers(packet);
			}
		} catch (IOException e) {
			throw new TransformException("Error during extract", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileSource " + file.getPath();
	}
}
