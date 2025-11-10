package io.blazingtwist.xml.instructions.xsd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import aya.util.FileUtils;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XsdInstance;

public class XsdAddF extends NamedOperator {
	public XsdAddF() {
		super("xsd.addf", "xsd_id::num file::str -> void : add a schema to the validator from a file (same as 'G xsd.adds' but with less memory allocations.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		String xsdFile = AyaHelper.popString(blockEvaluator);
		XsdInstance xsd = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xsd);
		xsd.addSchema(FileUtils.resolveFile(xsdFile));
	}
}
