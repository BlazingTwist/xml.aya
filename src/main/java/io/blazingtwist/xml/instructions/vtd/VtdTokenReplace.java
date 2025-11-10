package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.compat.VtdEncoding;
import io.blazingtwist.xml.exception.WrapperRuntimeException;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

import java.nio.charset.Charset;

public class VtdTokenReplace extends NamedOperator {
	public VtdTokenReplace() {
		super("vtd.token_replace", "(xml_id|xmlns_id)::num token_idx::num new_value::str -> void :"
				+ " replaces the entire token with the new_value.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		String newValue = AyaHelper.popString(blockEvaluator);
		int tokenIdx = AyaHelper.popInt(this, blockEvaluator);
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);

		Charset charset = VtdEncoding.fromVtdInt(xml.getNav().getEncoding()).charset;
		try {
			xml.getMod().updateToken(tokenIdx, newValue.getBytes(charset));
		} catch (Exception e) {
			throw new WrapperRuntimeException(e);
		}
	}
}
