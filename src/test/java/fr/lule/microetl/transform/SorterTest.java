package fr.lule.microetl.transform;

import java.util.Comparator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.lule.microetl.core.Packet;
import fr.lule.microetl.core.exception.TransformException;
import fr.lule.microetl.load.ListDestination;

public class SorterTest {

	@Test
	public void test() throws TransformException {
		Sorter sorter = new Sorter(new Comparator<Packet>() {
			@Override
			public int compare(Packet o1, Packet o2) {
				return o1.get("name").compareTo(o2.get("name"));
			}
		});
		ListDestination destination = new ListDestination();
		sorter.to(destination);
		sorter.receive(new Packet(new String[] { "name", "z" }));
		sorter.receive(new Packet(new String[] { "name", "a" }));
		sorter.receive(new Packet(new String[] { "name", "b" }));
		sorter.end();
		destination.waitForFinish();
		List<Packet> result = destination.getResult();
		Assert.assertEquals("a", result.get(0).get("name"));
		Assert.assertEquals("b", result.get(1).get("name"));
		Assert.assertEquals("z", result.get(2).get("name"));
	}

}
