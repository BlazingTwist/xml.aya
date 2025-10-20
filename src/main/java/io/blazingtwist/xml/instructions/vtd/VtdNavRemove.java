package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import com.ximpleware.ModifyException;
import com.ximpleware.NavException;
import io.blazingtwist.xml.exception.ModifyRuntimeException;
import io.blazingtwist.xml.exception.NavRuntimeException;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

public class VtdNavRemove extends NamedOperator {
	public VtdNavRemove() {
		super("vtd.nav_remove", "(xml_id|xmlns_id)::num -> void :"
				+ " Removes content from the master XML document.\n"
				+ " If the cursor is on an element, then the entire element referred is removed.\n"
				+ " If the cursor is on an attribute, then the corresponding attribute name/value pair is removed.\n"
				+ " If the token type is one of text, CDATA or comment, then the entire node, including the starting and ending delimiting text surrounding the content, is removed.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);
		try {
			xml.getMod().remove();
		} catch (NavException e) {
			throw new NavRuntimeException(e);
		} catch (ModifyException e) {
			throw new ModifyRuntimeException(e);
		}
	}
}