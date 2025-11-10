package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import com.ximpleware.NavException;
import io.blazingtwist.xml.compat.VtdFragment;
import io.blazingtwist.xml.exception.WrapperRuntimeException;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

import static io.blazingtwist.xml.AyaHelper.pushValue;

public class VtdNavGetContentFragment extends NamedOperator {
	public VtdNavGetContentFragment() {
		super("vtd.nav_get_content_fragment", "(xml_id|xmlns_id)::num -> offset::num length::num :"
				+ " returns the content offset and length of current element, which is the byte segment between the starting tag and ending tag."
				+ " (-1 -1) is returned if the current element is an empty element");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);
		try {
			long fragment = xml.getNav().getContentFragment();
			pushValue(blockEvaluator, VtdFragment.getOffset(fragment));
			pushValue(blockEvaluator, VtdFragment.getLength(fragment));
		} catch (NavException e) {
			throw new WrapperRuntimeException(e);
		}
	}
}