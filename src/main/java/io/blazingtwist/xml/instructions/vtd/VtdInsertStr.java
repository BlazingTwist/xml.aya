package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import com.ximpleware.ModifyException;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.compat.VtdEncoding;
import io.blazingtwist.xml.exception.WrapperRuntimeException;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

public class VtdInsertStr extends NamedOperator {
	public VtdInsertStr() {
		super("vtd.insert_str", "(xml_id|xmlns_id)::num offset::num inserted::str -> void :"
				+ " inserts a string at the given offset into the xml");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		String text = AyaHelper.popString(blockEvaluator);
		int offset = AyaHelper.popInt(this, blockEvaluator);
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);

		VtdEncoding encoding = VtdEncoding.fromVtdInt(xml.getNav().getEncoding());
		byte[] textBytes = text.getBytes(encoding.charset);
		try {
			xml.getMod().insertBytesAt(offset, textBytes);
		} catch (ModifyException e) {
			throw new WrapperRuntimeException(e);
		}
	}
}
