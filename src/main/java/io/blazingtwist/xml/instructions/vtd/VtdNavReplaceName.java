package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.exception.WrapperRuntimeException;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

public class VtdNavReplaceName extends NamedOperator {
	public VtdNavReplaceName() {
		super("vtd.nav_replace_name", "(xml_id|xmlns_id)::num new_name::str -> void :"
				+ " replaces the name of the element under the cursor with the new_name.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		String newName = AyaHelper.popString(blockEvaluator);
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);

		try {
			xml.getMod().updateElementName(newName);
		} catch (Exception e) {
			throw new WrapperRuntimeException(e);
		}
	}
}
