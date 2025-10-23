package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;

public class VtdClose extends NamedOperator {
	public VtdClose() {
		super("vtd.close", "(xml_id|xmlns_id)::num -> void :"
				+ " close an xml document to free up some memory.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		try {
			InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns).close();
		} catch (Exception ignored) {
		}
	}
}
