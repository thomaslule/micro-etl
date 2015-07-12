package fr.lule.microetl.core;

import org.junit.Assert;
import org.junit.Test;

public class PacketTest {

	@Test
	public void testConstructor() {
		Packet packet = new Packet(new String[] { "name1", "value1" }, new String[] { "name2", "value2" });
		Assert.assertEquals("value1", packet.get("name1"));
		Assert.assertEquals("value2", packet.get("name2"));
	}

}
