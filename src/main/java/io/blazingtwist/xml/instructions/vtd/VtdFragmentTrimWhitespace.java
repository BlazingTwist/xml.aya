package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import com.ximpleware.VTDNav;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.compat.VtdFragment;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

import static io.blazingtwist.xml.AyaHelper.pushValue;

public class VtdFragmentTrimWhitespace extends NamedOperator {
	public VtdFragmentTrimWhitespace() {
		super("vtd.fragment_trim_whitespace", "(xml_id|xmlns_id)::num direction::num offset::num length::num -> offset::num length::num :"
				+ " Trim white spaces around a segment.\n"
				+ " direction : -1 = leading, 0 = both, 1 = trailing");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		int length = AyaHelper.popInt(this, blockEvaluator);
		int offset = AyaHelper.popInt(this, blockEvaluator);
		int direction = AyaHelper.popInt(this, blockEvaluator);
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);

		final short wsAction;
		if (direction < 0) {
			wsAction = VTDNav.WS_LEADING;
		} else if (direction > 0) {
			wsAction = VTDNav.WS_TRAILING;
		} else {
			wsAction = VTDNav.WS_BOTH;
		}

		long expFragment = xml.getNav().trimWhiteSpaces(VtdFragment.toVtd(offset, length), wsAction);
		pushValue(blockEvaluator, VtdFragment.getOffset(expFragment));
		pushValue(blockEvaluator, VtdFragment.getLength(expFragment));
	}
}