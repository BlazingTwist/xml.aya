package io.blazingtwist.xml.instructions.xsd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;

public class XsdClose extends NamedOperator {
	public XsdClose() {
		super("xsd.close", "xsd_id::num -> void : close an xsd instance to release some memory.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		try {
			InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xsd).close();
		} catch (Exception ignored) {
		}
	}
}
