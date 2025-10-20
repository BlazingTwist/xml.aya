package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.instances.AutoPilotInstance;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;

public class VtdXPathEvalStr extends NamedOperator {
	public VtdXPathEvalStr() {
		super("vtd.xpath_eval_str", "xpath_id::num -> str :"
				+ " Evaluate the xpath to a string. This operation may move the cursor.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		AutoPilotInstance api = InstanceManager.popInstance(this, blockEvaluator, InstanceType.XPath);
		AyaHelper.pushValue(blockEvaluator, api.getAutoPilot().evalXPathToString());
	}
}