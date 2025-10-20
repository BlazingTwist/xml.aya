package io.blazingtwist.xml.instructions.vtd.xml;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import com.ximpleware.NavException;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.exception.NavRuntimeException;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

import static io.blazingtwist.xml.AyaHelper.pushValue;

public class VtdNavFindAttrValue extends NamedOperator {
	public VtdNavFindAttrValue() {
		super("vtd.nav_find_attr_value", "xml_id::num name::str -> num :"
				+ " Get the token index of the attribute value given an attribute name."
				+ " Returns -1 if no such attribute exists on the current element.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		String attrName = AyaHelper.popString(blockEvaluator);
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml);
		try {
			pushValue(blockEvaluator, xml.getNav().getAttrVal(attrName));
		} catch (NavException e) {
			throw new NavRuntimeException(e);
		}
	}
}