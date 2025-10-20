package io.blazingtwist.xml.instructions.xmlns;

import aya.eval.BlockEvaluator;
import aya.exceptions.runtime.IOError;
import aya.instruction.named.NamedOperator;
import aya.util.FileUtils;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlnsInstance;

public class XmlnsLoadF extends NamedOperator
{
    public XmlnsLoadF( )
    {
        super( "xmlns.loadf", "file::str ignore_whitespace::bool -> xmlns_id::num :"
				+ " load an XML document from a file with namespace support\n"
				+ " - ignore_whitespace : true = no tokens are constructed for trivial whitespace (pure whitespace between elements)" );
    }

    @Override
    public void execute( BlockEvaluator blockEvaluator )
    {
		boolean ignoreWhitespace = AyaHelper.popBool(this, blockEvaluator);
		String fileName = AyaHelper.popString(blockEvaluator);
		final byte[] fileBytes;
		try {
			fileBytes = FileUtils.readAllBytes(FileUtils.resolveFile(fileName));
		} catch (Exception e) {
			throw new IOError(this.opName(), fileName, e.getMessage());
		}

		final XmlnsInstance instance;
		try {
			instance = new XmlnsInstance(fileBytes, ignoreWhitespace);
		} catch (Exception e) {
			throw new IOError(this.opName(), fileName, "Malformed XML document. " + e.getMessage());
		}
		int instanceId = InstanceManager.createInstance(InstanceType.Xmlns, instance);
		AyaHelper.pushValue(blockEvaluator, instanceId);
    }
}
