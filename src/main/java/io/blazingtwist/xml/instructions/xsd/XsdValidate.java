package io.blazingtwist.xml.instructions.xsd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.compat.XmlErrorHandler;
import io.blazingtwist.xml.exception.WrapperRuntimeException;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;
import io.blazingtwist.xml.instances.XsdInstance;
import org.xml.sax.SAXParseException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Validator;
import java.io.ByteArrayInputStream;

public class XsdValidate extends NamedOperator {
	public XsdValidate() {
		super("xsd.validate", "xsd_id::num (xml_id|xmlns_id)::num -> list<error> : validate the given xml document.\n"
				+ "  typedef error = dict {\n"
				+ "    ::num :line_number;\n"
				+ "    ::num :column_number;\n"
				+ "    ::str :message;\n"
				+ "    ::sym :severity; - one of [::warning ::error ::fatal]\n"
				+ "  }");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);
		XsdInstance xsd = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xsd);
		final Validator validator;
		try {
			validator = xsd.getValidator();
		} catch (SAXParseException e) {
			String message = "Invalid XSD at line " + e.getLineNumber() + " col " + e.getColumnNumber() + " : " + e.getMessage();
			throw new WrapperRuntimeException(message, e);
		} catch (Exception e) {
			throw new WrapperRuntimeException(e);
		}
		XmlErrorHandler errorHandler = new XmlErrorHandler();
		validator.setErrorHandler(errorHandler);
		try {
			validator.validate(new StreamSource(new ByteArrayInputStream(xml.getNav().getXML().getBytes())));
		} catch (Exception e) {
			throw new WrapperRuntimeException(e);
		}

		AyaHelper.pushValue(blockEvaluator, errorHandler.getExceptions());
	}
}
