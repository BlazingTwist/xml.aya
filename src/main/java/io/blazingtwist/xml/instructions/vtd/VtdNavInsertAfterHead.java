package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.exception.WrapperRuntimeException;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

public class VtdNavInsertAfterHead extends NamedOperator {
	public VtdNavInsertAfterHead() {
		super("vtd.nav_insert_after_head", "(xml_id|xmlns_id)::num text::str -> void :"
				+ " inserts the text after the head of the element under the cursor.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		String text = AyaHelper.popString(blockEvaluator);
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);

		try {
			xml.getMod().insertAfterHead(text);
		} catch (Exception e) {
			throw new WrapperRuntimeException(e);
		}
	}
}
