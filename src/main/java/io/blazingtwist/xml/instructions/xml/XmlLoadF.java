package io.blazingtwist.xml.instructions.xml;

import aya.eval.BlockEvaluator;
import aya.exceptions.runtime.IOError;
import aya.instruction.named.NamedOperator;
import aya.util.FileUtils;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

public class XmlLoadF extends NamedOperator {
	public XmlLoadF() {
		super("xml.loadf", "file::str ignore_whitespace::bool -> xml_id::num :"
				+ " load an XML document from a file\n"
				+ " - ignore_whitespace : true = no tokens are constructed for trivial whitespace (pure whitespace between elements)");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		boolean ignoreWhitespace = AyaHelper.popBool(this, blockEvaluator);
		String fileName = blockEvaluator.pop().str();
		final byte[] fileBytes;
		try {
			fileBytes = FileUtils.readAllBytes(FileUtils.resolveFile(fileName));
		} catch (Exception e) {
			throw new IOError(this.opName(), fileName, e.getMessage());
		}

		final XmlInstance instance;
		try {
			instance = new XmlInstance(fileBytes, ignoreWhitespace);
		} catch (Exception e) {
			throw new IOError(this.opName(), fileName, "Malformed XML document. " + e.getMessage());
		}
		int instanceId = InstanceManager.createInstance(InstanceType.Xml, instance);
		AyaHelper.pushValue(blockEvaluator, instanceId);
	}
}
