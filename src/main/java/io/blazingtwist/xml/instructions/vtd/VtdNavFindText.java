package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

import static io.blazingtwist.xml.AyaHelper.pushValue;

public class VtdNavFindText extends NamedOperator {
	public VtdNavFindText() {
		super("vtd.nav_find_text", "(xml_id|xmlns_id)::num -> num :"
				+ " Returns the token index of the type character data or CDATA."
				+ " Notice that it is intended to support data orient XML (not mixed-content XML)."
				+ " return the index of the text token, or -1 if none exists.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);
		pushValue(blockEvaluator, xml.getNav().getText());
	}
}