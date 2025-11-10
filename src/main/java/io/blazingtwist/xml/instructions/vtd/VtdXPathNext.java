package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.exception.WrapperRuntimeException;
import io.blazingtwist.xml.instances.AutoPilotInstance;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;

public class VtdXPathNext extends NamedOperator {
	public VtdXPathNext() {
		super("vtd.xpath_next", "xpath_id::num -> num :"
				+ " This method returns the next node in the nodeset or -1 if there is are no more nodes.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		AutoPilotInstance api = InstanceManager.popInstance(this, blockEvaluator, InstanceType.XPath);
		try {
			AyaHelper.pushValue(blockEvaluator, api.getAutoPilot().evalXPath());
		} catch (Exception e) {
			throw new WrapperRuntimeException(e);
		}
	}
}