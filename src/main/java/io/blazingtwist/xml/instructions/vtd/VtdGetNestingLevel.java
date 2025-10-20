package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

import static io.blazingtwist.xml.AyaHelper.pushValue;

public class VtdGetNestingLevel extends NamedOperator {
	public VtdGetNestingLevel() {
		super("vtd.get_nesting_level", "(xml_id|xmlns_id)::num -> num : Get the maximum nesting depth of the XML document (>0). max depth is nestingLevel -1");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);
		pushValue(blockEvaluator, xml.getNav().getNestingLevel());
	}
}
