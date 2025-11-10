package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import com.ximpleware.NavException;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.exception.WrapperRuntimeException;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

import static io.blazingtwist.xml.AyaHelper.pushValue;

public class VtdTokenToRawString extends NamedOperator {
	public VtdTokenToRawString() {
		super("vtd.token_to_raw_string", "(xml_id|xmlns_id)::num token_idx::num -> str :"
				+ " Convert a token at the given index to a String, (entities and char references not expanded).");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		int tokenIdx = AyaHelper.popInt(this, blockEvaluator);
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);
		try {
			pushValue(blockEvaluator, xml.getNav().toRawString(tokenIdx));
		} catch (NavException e) {
			throw new WrapperRuntimeException(e);
		}
	}
}