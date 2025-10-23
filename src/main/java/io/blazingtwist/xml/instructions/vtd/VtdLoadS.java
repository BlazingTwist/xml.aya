package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.exceptions.runtime.IOError;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;
import io.blazingtwist.xml.instances.XmlnsInstance;

import java.nio.charset.Charset;

public class VtdLoadS extends NamedOperator {
	public VtdLoadS() {
		super("vtd.loads", "xml::str ignore_whitespace::bool namespaces::bool -> (xml_id|xmlns_id)::num :"
				+ " load an XML-literal from a string\n"
				+ " - ignore_whitespace : true = no tokens are constructed for trivial whitespace (pure whitespace between elements)\n"
				+ " - namespaces : true = enable namespace support (returns xmlns_id, otherwise xml_id)");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		boolean namespaces = AyaHelper.popBool(this, blockEvaluator);
		boolean ignoreWhitespace = AyaHelper.popBool(this, blockEvaluator);
		String xmlStr = AyaHelper.popString(blockEvaluator);

		final InstanceType instanceType;
		final XmlInstance instance;
		try {
			if (namespaces) {
				instanceType = InstanceType.Xmlns;
				instance = new XmlnsInstance(xmlStr.getBytes(Charset.defaultCharset()), ignoreWhitespace);
			} else {
				instanceType = InstanceType.Xml;
				instance = new XmlInstance(xmlStr.getBytes(Charset.defaultCharset()), ignoreWhitespace);
			}
		} catch (Exception e) {
			throw new IOError(this.opName(), "xml::str", "Malformed XML document. " + e.getMessage());
		}
		int instanceId = InstanceManager.createInstance(instanceType, instance);
		AyaHelper.pushValue(blockEvaluator, instanceId);
	}
}
