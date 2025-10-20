package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import com.ximpleware.ModifyException;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.exception.ModifyRuntimeException;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

public class VtdFragmentRemove extends NamedOperator {
	public VtdFragmentRemove() {
		super("vtd.fragment_remove", "(xml_id|xmlns_id)::num offset::num length::num -> void :"
				+ " Removes a fragment from the document.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		int length = AyaHelper.popInt(this, blockEvaluator);
		int offset = AyaHelper.popInt(this, blockEvaluator);
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);

		try {
			xml.getMod().removeContent(offset, length);
		} catch (ModifyException e) {
			throw new ModifyRuntimeException(e);
		}
	}
}