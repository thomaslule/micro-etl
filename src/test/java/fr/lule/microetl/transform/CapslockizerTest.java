package fr.lule.microetl.transform;

import org.junit.Assert;
import org.junit.Test;

import fr.lule.microetl.core.Packet;
import fr.lule.microetl.load.ListDestination;

public class CapslockizerTest {

	@Test
	public void testReceive() throws Exception {
		Capslockizer capslockizer = new Capslockizer();
		ListDestination destination = new ListDestination();
		capslockizer.to(destination);
		capslockizer.receive(new Packet(new String[] { "field1", "value1" }, new String[] { "field2", "value2" }));
		capslockizer.end();
		destination.waitForFinish();
		Packet packet = destination.getResult().get(0);
		Assert.assertEquals("VALUE1", packet.get("field1"));
		Assert.assertEquals("VALUE2", packet.get("field2"));
	}

}
