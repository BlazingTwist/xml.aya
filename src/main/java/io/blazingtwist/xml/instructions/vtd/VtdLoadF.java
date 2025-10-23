package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.exceptions.runtime.IOError;
import aya.instruction.named.NamedOperator;
import aya.util.FileUtils;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;
import io.blazingtwist.xml.instances.XmlnsInstance;

public class VtdLoadF extends NamedOperator {
	public VtdLoadF() {
		super("vtd.loadf", "file::str ignore_whitespace::bool namespaces::bool -> xml_id::num :"
				+ " load an XML document from a file\n"
				+ " - ignore_whitespace : true = no tokens are constructed for trivial whitespace (pure whitespace between elements)\n"
				+ " - namespaces : true = enable namespace support (returns xmlns_id, otherwise xml_id)");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		boolean namespaces = AyaHelper.popBool(this, blockEvaluator);
		boolean ignoreWhitespace = AyaHelper.popBool(this, blockEvaluator);
		String fileName = blockEvaluator.pop().str();
		final byte[] fileBytes;
		try {
			fileBytes = FileUtils.readAllBytes(FileUtils.resolveFile(fileName));
		} catch (Exception e) {
			throw new IOError(this.opName(), fileName, e.getMessage());
		}

		final InstanceType instanceType;
		final XmlInstance instance;
		try {
			if (namespaces) {
				instanceType = InstanceType.Xmlns;
				instance = new XmlnsInstance(fileBytes, ignoreWhitespace);
			} else {
				instanceType = InstanceType.Xml;
				instance = new XmlInstance(fileBytes, ignoreWhitespace);
			}
		} catch (Exception e) {
			throw new IOError(this.opName(), fileName, "Malformed XML document. " + e.getMessage());
		}
		int instanceId = InstanceManager.createInstance(instanceType, instance);
		AyaHelper.pushValue(blockEvaluator, instanceId);
	}
}
