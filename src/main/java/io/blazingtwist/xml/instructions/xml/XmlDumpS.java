package io.blazingtwist.xml.instructions.xml;

import aya.eval.BlockEvaluator;
import aya.exceptions.runtime.IOError;
import aya.instruction.named.NamedOperator;
import com.ximpleware.ModifyException;
import com.ximpleware.TranscodeException;
import io.blazingtwist.xml.exception.ModifyRuntimeException;
import io.blazingtwist.xml.exception.TranscodeRuntimeException;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class XmlDumpS extends NamedOperator {
	public XmlDumpS() {
		super("xml.dumps", "(xml_id|xmlns_id)::num -> str :"
				+ " write the xml and all modifications to a string.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		try {
			xml.getMod().output(bao);
		} catch (IOException e) {
			throw new IOError(opName(), "xml/ns", e);
		} catch (ModifyException e) {
			throw new ModifyRuntimeException(e);
		} catch (TranscodeException e) {
			throw new TranscodeRuntimeException(e);
		}
	}
}
