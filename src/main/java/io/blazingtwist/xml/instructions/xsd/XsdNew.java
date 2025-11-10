package io.blazingtwist.xml.instructions.xsd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XsdInstance;

public class XsdNew extends NamedOperator {
	public XsdNew() {
		super("xsd.new", "void -> xsd_id::num : prepare a new validator (see also: xsd.adds or xsd.addf).\n"
				+ "  Supports XSD 1.0.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		AyaHelper.pushValue(blockEvaluator, InstanceManager.createInstance(InstanceType.Xsd, new XsdInstance()));
	}
}
