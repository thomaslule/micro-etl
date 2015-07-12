package fr.lule.microetl.extract;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.lule.microetl.core.Packet;
import fr.lule.microetl.core.exception.TransformException;
import fr.lule.microetl.load.ListDestination;

public class FileSourceTest {

	@Test
	public void testStart() throws TransformException {
		FileSource source = new FileSource("src/test/resources/input.txt", new String[] { "surname", "city" });
		ListDestination destination = new ListDestination();
		source.to(destination);
		source.startProcess();
		List<Packet> result = destination.getResult();
		Assert.assertEquals(5, result.size());
		Assert.assertEquals("Patrick", result.get(0).get("surname"));
		Assert.assertEquals("Paris", result.get(0).get("city"));
	}

}
