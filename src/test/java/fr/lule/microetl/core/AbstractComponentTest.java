package fr.lule.microetl.core;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Assert;
import org.junit.Test;

import fr.lule.microetl.core.exception.TransformException;

public class AbstractComponentTest {

	@Test
	public void testNominal() throws Exception {
		AtomicBoolean called = new AtomicBoolean(false);
		Component component = (new AbstractComponent() {
			@Override
			protected void doJob() throws TransformException {
				called.set(true);
			}

			public Component start() {
				startComponent();
				return this;
			}
		}).start();
		component.waitForFinish();
		Assert.assertEquals(true, called.get());
	}

	@Test(expected = TransformException.class)
	public void testTransformException() throws Exception {
		Component component = (new AbstractComponent() {
			@Override
			protected void doJob() throws TransformException {
				throw new TransformException("error!");
			}

			public Component start() {
				startComponent();
				return this;
			}
		}).start();
		component.waitForFinish();
	}

	@Test(expected = RuntimeException.class)
	public void testRuntimeException() throws Exception {
		Component component = (new AbstractComponent() {
			@Override
			protected void doJob() throws TransformException {
				throw new RuntimeException("error!");
			}

			public Component start() {
				startComponent();
				return this;
			}
		}).start();
		component.waitForFinish();
	}
}
