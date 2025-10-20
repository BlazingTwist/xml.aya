package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.instances.AutoPilotInstance;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;

public class VtdXPathReset extends NamedOperator {
	public VtdXPathReset() {
		super("vtd.xpath_reset", "xpath_id::num -> void :"
				+ " Reset the XPath so the XPath Expression can be reused and reevaluated in another context position");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		AutoPilotInstance api = InstanceManager.popInstance(this, blockEvaluator, InstanceType.XPath);
		api.getAutoPilot().resetXPath();
	}
}