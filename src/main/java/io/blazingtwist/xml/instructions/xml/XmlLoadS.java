package io.blazingtwist.xml.instructions.xml;

import aya.eval.BlockEvaluator;
import aya.exceptions.runtime.IOError;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

import java.nio.charset.Charset;

public class XmlLoadS extends NamedOperator {
	public XmlLoadS() {
		super("xml.loads", "xml::str ignore_whitespace::bool -> xml_id::num :"
				+ " load an XML-literal from a string\n"
				+ " - ignore_whitespace : true = no tokens are constructed for trivial whitespace (pure whitespace between elements)");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		boolean ignoreWhitespace = AyaHelper.popBool(this, blockEvaluator);
		String xmlStr = AyaHelper.popString(blockEvaluator);

		final XmlInstance instance;
		try {
			instance = new XmlInstance(xmlStr.getBytes(Charset.defaultCharset()), ignoreWhitespace);
		} catch (Exception e) {
			throw new IOError(this.opName(), "xml::str", "Malformed XML document. " + e.getMessage());
		}
		int instanceId = InstanceManager.createInstance(InstanceType.Xml, instance);
		AyaHelper.pushValue(blockEvaluator, instanceId);
	}
}
