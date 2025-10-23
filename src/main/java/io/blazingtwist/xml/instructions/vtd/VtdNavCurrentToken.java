package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

import static io.blazingtwist.xml.AyaHelper.pushValue;

public class VtdNavCurrentToken extends NamedOperator {
	public VtdNavCurrentToken() {
		super("vtd.nav_current_token", "(xml_id|xmlns_id)::num -> token_idx::num :"
				+ " Returns the index of the current token.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);
		// fyi: the documentation for 'getCurrentIndex' is wrong. ""Get the index value of the current element."" element != token...
		pushValue(blockEvaluator, xml.getNav().getCurrentIndex());
	}
}