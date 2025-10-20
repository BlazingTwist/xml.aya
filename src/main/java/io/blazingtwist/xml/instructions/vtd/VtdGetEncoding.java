package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.compat.VtdEncoding;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

import static io.blazingtwist.xml.AyaHelper.pushValue;

public class VtdGetEncoding extends NamedOperator {
	public VtdGetEncoding() {
		super("vtd.get_encoding", "(xml_id|xmlns_id)::num -> charset_name::sym :"
				+ " Get the encoding of the XML document. Returns the charset name as a symbol. E.g. 'iso-8859-1'.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);
		pushValue(blockEvaluator, VtdEncoding.fromVtdInt(xml.getNav().getEncoding()).symbol);
	}
}
