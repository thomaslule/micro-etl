package fr.lule.microetl;

import java.util.Comparator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.lule.microetl.core.Packet;
import fr.lule.microetl.core.exception.TransformException;
import fr.lule.microetl.extract.FileSource;
import fr.lule.microetl.load.FileDestination;
import fr.lule.microetl.load.ListDestination;
import fr.lule.microetl.transform.Capslockizer;
import fr.lule.microetl.transform.Sorter;

public class IntegrationTest {

	@Test
	public void testApp() throws TransformException {
		FileSource source = new FileSource("src/test/resources/input.txt", new String[] { "surname", "city" });
		Sorter sorter = new Sorter(new Comparator<Packet>() {
			@Override
			public int compare(Packet o1, Packet o2) {
				return o1.get("city").compareTo(o2.get("city"));
			}
		});
		Capslockizer capslockizer = new Capslockizer();
		ListDestination listDestination = new ListDestination();
		FileDestination fileDestination = new FileDestination("src/test/resources/output.txt");
		source.to(capslockizer).to(sorter).to(fileDestination);
		sorter.to(listDestination);
		source.startProcess();
		List<Packet> result = listDestination.getResult();
		Assert.assertEquals(5, result.size());
		Assert.assertEquals("LYON", result.get(0).get("city"));
		Assert.assertEquals("VIENNE", result.get(4).get("city"));
	}

}
