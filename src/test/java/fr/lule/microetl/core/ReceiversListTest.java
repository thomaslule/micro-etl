package fr.lule.microetl.core;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import fr.lule.microetl.core.exception.TransformException;
import fr.lule.microetl.core.helper.ReceiversList;

public class ReceiversListTest {

	@Test
	public void test() throws TransformException {
		ReceiversList list = new ReceiversList();
		Receiver receiver1 = mock(Receiver.class);
		Receiver receiver2 = mock(Receiver.class);
		list.add(receiver1);
		list.add(receiver2);
		Packet packet = new Packet();
		list.receive(packet);
		verify(receiver1).receive(packet);
		verify(receiver2).receive(packet);
	}

}
