package fr.lule.microetl.transform;

import org.junit.Assert;
import org.junit.Test;

import fr.lule.microetl.core.Packet;
import fr.lule.microetl.load.ListDestination;
import fr.lule.microetl.transform.Filter.Filterer;

public class FilterTest {

	@Test
	public void test() throws Exception {
		Filter filter = new Filter(new Filterer() {

			@Override
			public boolean valid(Packet packet) {
				return packet.get("valid").equals("1");
			}

		});
		ListDestination destination = new ListDestination();
		filter.to(destination);
		filter.receive(new Packet(new String[] { "valid", "1" }, new String[] { "name", "first" }));
		filter.receive(new Packet(new String[] { "valid", "0" }, new String[] { "name", "second" }));
		filter.end();
		filter.waitForAllFinish();
		Assert.assertEquals(1, destination.getResult().size());
		Assert.assertEquals("first", destination.getResult().get(0).get("name"));
	}

}
