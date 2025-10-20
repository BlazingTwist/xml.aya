package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import com.ximpleware.ModifyException;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.exception.ModifyRuntimeException;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

public class VtdTokenRemove extends NamedOperator {
	public VtdTokenRemove() {
		super("vtd.token_remove", "(xml_id|xmlns_id)::num token_idx::num -> void :"
				+ " Remove the token content."
				+ " If the token type is text, CDATA or comment, then the entire node, including the starting and ending delimiting text, will be removed as well");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		int tokenIdx = AyaHelper.popInt(this, blockEvaluator);
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);

		try {
			xml.getMod().removeToken(tokenIdx);
		} catch (ModifyException e) {
			throw new ModifyRuntimeException(e);
		}
	}
}
