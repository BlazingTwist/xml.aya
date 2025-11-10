package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import com.ximpleware.NavException;
import com.ximpleware.VTDNav;
import io.blazingtwist.xml.exception.WrapperRuntimeException;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

import static io.blazingtwist.xml.AyaHelper.pushValue;

public class VtdNavToLastChild extends NamedOperator {
	public VtdNavToLastChild() {
		super("vtd.nav_to_last_child", "(xml_id|xmlns_id)::num -> bool :"
				+ " Move the cursor to the last child element of the current element."
				+ " If no such element exists, no position change occurs and false is returned.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);
		try {
			pushValue(blockEvaluator, xml.getNav().toElement(VTDNav.LAST_CHILD));
		} catch (NavException e) {
			throw new WrapperRuntimeException(e);
		}
	}
}