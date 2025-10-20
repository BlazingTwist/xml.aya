package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import com.ximpleware.NavException;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.exception.NavRuntimeException;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

import static io.blazingtwist.xml.AyaHelper.pushValue;

public class VtdTokenToNormalizedString extends NamedOperator {
	public VtdTokenToNormalizedString() {
		super("vtd.token_to_normalized_string", "(xml_id|xmlns_id)::num token_idx::num -> str :"
				+ " This method normalizes a token into a string value of character data and attr val in a way that resembles DOM."
				+ " The leading and trailing white space characters will be stripped."
				+ " The entity and character references will be resolved."
				+ " Multiple whitespaces char will be collapsed into one."
				+ " Whitespaces via entities will nonetheless be preserved.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		int tokenIdx = AyaHelper.popInt(this, blockEvaluator);
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);
		try {
			pushValue(blockEvaluator, xml.getNav().toNormalizedString(tokenIdx));
		} catch (NavException e) {
			throw new NavRuntimeException(e);
		}
	}
}