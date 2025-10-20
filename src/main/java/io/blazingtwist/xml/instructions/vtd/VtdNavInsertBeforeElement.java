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

public class VtdNavInsertBeforeElement extends NamedOperator {
	public VtdNavInsertBeforeElement() {
		super("vtd.nav_insert_before_element", "(xml_id|xmlns_id)::num text::str -> void :"
				+ " inserts the text before the element under the cursor.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		String text = AyaHelper.popString(blockEvaluator);
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);

		try {
			xml.getMod().insertBeforeElement(text);
		} catch (ModifyException e) {
			throw new ModifyRuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedEncodingRuntimeException(e);
		}
	}
}
