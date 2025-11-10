package io.blazingtwist.xml.instructions.vtd.xmlns;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.exception.WrapperRuntimeException;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlnsInstance;

import static io.blazingtwist.xml.AyaHelper.pushValue;

public class VtdNavFindAttrValueNs extends NamedOperator {
	public VtdNavFindAttrValueNs() {
		super("vtd.nav_find_attr_value_ns", "xmlns_id::num namespace::str name::str -> num :"
				+ " Get the token index of the attribute value of given URL and local name."
				+ " Returns -1 if no such attribute exists on the current element.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		String attrName = AyaHelper.popString(blockEvaluator);
		String namespace = AyaHelper.popString(blockEvaluator);
		XmlnsInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xmlns);
		try {
			pushValue(blockEvaluator, xml.getNav().getAttrValNS(namespace, attrName));
		} catch (Exception e) {
			throw new WrapperRuntimeException(e);
		}
	}
}