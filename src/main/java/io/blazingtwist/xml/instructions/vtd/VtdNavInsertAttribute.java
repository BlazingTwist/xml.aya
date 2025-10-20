package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import com.ximpleware.ModifyException;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.exception.ModifyRuntimeException;
import io.blazingtwist.xml.exception.UnsupportedEncodingRuntimeException;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

import java.io.UnsupportedEncodingException;

public class VtdNavInsertAttribute extends NamedOperator {
	public VtdNavInsertAttribute() {
		super("vtd.nav_insert_attribute", "(xml_id|xmlns_id)::num attr::str -> void :"
				+ " Insert an attribute after the starting tag of the element under the cursor.\n"
				+ " - attr : e. g. \" attrName='attrVal' \", notice the starting and ending white space.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		String text = AyaHelper.popString(blockEvaluator);
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);

		try {
			xml.getMod().insertAttribute(text);
		} catch (ModifyException e) {
			throw new ModifyRuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedEncodingRuntimeException(e);
		}
	}
}
