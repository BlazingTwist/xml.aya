package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import com.ximpleware.NavException;
import io.blazingtwist.xml.compat.VtdFragment;
import io.blazingtwist.xml.exception.NavRuntimeException;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

import static io.blazingtwist.xml.AyaHelper.pushValue;

public class VtdNavGetElementFragment extends NamedOperator {
	public VtdNavGetElementFragment() {
		super("vtd.nav_get_element_fragment", "(xml_id|xmlns_id)::num -> offset::num length::num :"
				+ " Get the starting offset and length of the current element.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);
		try {
			long fragment = xml.getNav().getElementFragment();
			pushValue(blockEvaluator, VtdFragment.getOffset(fragment));
			pushValue(blockEvaluator, VtdFragment.getLength(fragment));
		} catch (NavException e) {
			throw new NavRuntimeException(e);
		}
	}
}