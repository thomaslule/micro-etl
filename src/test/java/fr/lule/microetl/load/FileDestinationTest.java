package fr.lule.microetl.load;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import fr.lule.microetl.core.Packet;
import fr.lule.microetl.core.exception.TransformException;

public class FileDestinationTest {

	@Test
	public void test() throws TransformException, IOException {
		FileDestination fileDestination = new FileDestination("src/test/resources/output.txt");
		Packet packet = new Packet(new String[] { "field1", "value1" }, new String[] { "field2", "value2" });
		fileDestination.receive(packet);
		packet = new Packet(new String[] { "field1", "valueA" }, new String[] { "field2", "valueB" });
		fileDestination.receive(packet);
		fileDestination.end();
		fileDestination.waitForFinish();
		try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/output.txt"))) {
			Assert.assertEquals("value1;value2", reader.readLine());
			Assert.assertEquals("valueA;valueB", reader.readLine());
			Assert.assertNull(reader.readLine());
		}
	}

}
