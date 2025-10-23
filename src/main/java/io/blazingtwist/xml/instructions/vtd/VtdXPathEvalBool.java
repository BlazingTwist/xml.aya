package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.instances.AutoPilotInstance;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;

public class VtdXPathEvalBool extends NamedOperator {
	public VtdXPathEvalBool() {
		super("vtd.xpath_eval_bool", "xpath_id::num -> bool :"
				+ " Evaluate the xpath to a bool, relative to the current Nav position.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		AutoPilotInstance api = InstanceManager.popInstance(this, blockEvaluator, InstanceType.XPath);
		AyaHelper.pushValue(blockEvaluator, api.getAutoPilot().evalXPathToBoolean());
	}
}