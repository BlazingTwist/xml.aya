package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.instances.AutoPilotInstance;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;

public class VtdXPathEvalNum extends NamedOperator {
	public VtdXPathEvalNum() {
		super("vtd.xpath_eval_num", "xpath_id::num -> num :"
				+ " Evaluate the xpath to a number. This operation may move the cursor.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		AutoPilotInstance api = InstanceManager.popInstance(this, blockEvaluator, InstanceType.XPath);
		AyaHelper.pushValue(blockEvaluator, api.getAutoPilot().evalXPathToNumber());
	}
}