package io.blazingtwist.xml.instances;

import java.io.Closeable;

public abstract class InstanceData implements Closeable {
	private boolean isClosed = false;

	public boolean isClosed() {
		return isClosed;
	}

	@Override
	public void close() {
		isClosed = true;
	}
}
