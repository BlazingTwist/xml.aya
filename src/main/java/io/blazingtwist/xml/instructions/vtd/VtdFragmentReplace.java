package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import com.ximpleware.ModifyException;
import com.ximpleware.XMLModifier;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.compat.VtdEncoding;
import io.blazingtwist.xml.exception.ModifyRuntimeException;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

public class VtdFragmentReplace extends NamedOperator {
	public VtdFragmentReplace() {
		super("vtd.fragment_replace", "(xml_id|xmlns_id)::num offset::num length::num new_value::str -> void :"
				+ " Replaces a fragment of the document with new text.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		String text = AyaHelper.popString(blockEvaluator);
		int length = AyaHelper.popInt(this, blockEvaluator);
		int offset = AyaHelper.popInt(this, blockEvaluator);
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);

		VtdEncoding encoding = VtdEncoding.fromVtdInt(xml.getNav().getEncoding());
		byte[] textBytes = text.getBytes(encoding.charset);

		try {
			XMLModifier mod = xml.getMod();
			mod.removeContent(offset, length);
			mod.insertBytesAt(offset, textBytes);
		} catch (ModifyException e) {
			throw new ModifyRuntimeException(e);
		}
	}
}