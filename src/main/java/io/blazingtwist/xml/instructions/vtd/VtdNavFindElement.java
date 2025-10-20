package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

import static io.blazingtwist.xml.AyaHelper.pushValue;

public class VtdNavFindElement extends NamedOperator {
	public VtdNavFindElement() {
		super("vtd.nav_find_element", "(xml_id|xmlns_id)::num -> num :"
				+ " Returns the token index of the current element.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);
		pushValue(blockEvaluator, xml.getNav().getCurrentIndex());
	}
}