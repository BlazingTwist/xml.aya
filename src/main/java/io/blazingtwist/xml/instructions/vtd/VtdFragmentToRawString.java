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

public class VtdFragmentToRawString extends NamedOperator {
	public VtdFragmentToRawString() {
		super("vtd.fragment_to_raw_string", "(xml_id|xmlns_id)::num offset::num length::num -> str :"
				+ " Convert a segment of XML bytes a into string, without entity resolution");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		int length = AyaHelper.popInt(this, blockEvaluator);
		int offset = AyaHelper.popInt(this, blockEvaluator);
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);
		try {
			pushValue(blockEvaluator, xml.getNav().toRawString(offset, length));
		} catch (NavException e) {
			throw new NavRuntimeException(e);
		}
	}
}