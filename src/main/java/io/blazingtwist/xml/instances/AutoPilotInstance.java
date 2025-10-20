package io.blazingtwist.xml.instances;

import com.ximpleware.AutoPilot;

public class AutoPilotInstance extends InstanceData {
	private final XmlInstance xml;
	private AutoPilot autoPilot;

	public AutoPilotInstance(XmlInstance xml) {
		this.xml = xml;
		this.autoPilot = new AutoPilot(xml.getNav());
	}

	public AutoPilot getAutoPilot() {
		return autoPilot;
	}

	@Override
	public boolean isClosed() {
		boolean thisClosed = super.isClosed();
		if (!thisClosed && xml.isClosed()) {
			// associated xml was closed, the AutoPilot will not be reused.
			close();
			return true;
		}
		return thisClosed;
	}

	@Override
	public void close() {
		super.close();
		autoPilot = null;
	}
}
