package io.blazingtwist.xml.instructions.xsd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XsdInstance;

public class XsdAddS extends NamedOperator {
	public XsdAddS() {
		super("xsd.adds", "xsd_id::num xsd::str -> void : add a schema to the validator from a string");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		String xsdStr = AyaHelper.popString(blockEvaluator);
		XsdInstance xsd = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xsd);
		xsd.addSchema(xsdStr);
	}
}
