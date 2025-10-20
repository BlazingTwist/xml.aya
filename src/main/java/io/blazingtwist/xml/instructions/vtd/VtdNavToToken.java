package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import com.ximpleware.NavException;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.exception.NavRuntimeException;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

public class VtdNavToToken extends NamedOperator {
	public VtdNavToToken() {
		super("vtd.nav_to_token", "(xml_id|xmlns_id)::num token_idx::num -> void :"
				+ " Move the cursor to the element identified by the token_idx."
				+ " The Token's type must NOT be one of [::attr_val, ::dec_attr_name, ::dec_attr_val, ::pi_val].");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		int tokenIdx = AyaHelper.popInt(this, blockEvaluator);
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);
		try {
			xml.getNav().recoverNode(tokenIdx);
		} catch (NavException e) {
			throw new NavRuntimeException(e);
		}
	}
}