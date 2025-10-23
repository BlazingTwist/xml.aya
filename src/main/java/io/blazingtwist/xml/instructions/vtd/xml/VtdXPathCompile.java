package io.blazingtwist.xml.instructions.vtd.xml;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import com.ximpleware.XPathParseException;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.exception.XPathParseRuntimeException;
import io.blazingtwist.xml.instances.AutoPilotInstance;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

public class VtdXPathCompile extends NamedOperator {
	public VtdXPathCompile() {
		super("vtd.xpath_compile", "xml_id::num xpath::str -> xpath_id::num :"
				+ " Compiles the given xpath and returns an ID for use with the other vtx.xpath instructions.\n"
				+ " The XPath will modify the Navigator of the given XML instance. Thus it can be used in combination with the vdx.nav instructions.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		String xPath = AyaHelper.popString(blockEvaluator);
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml);
		try {
			AutoPilotInstance api = new AutoPilotInstance(xml);
			api.getAutoPilot().selectXPath(xPath);
			int xPathId = InstanceManager.createInstance(InstanceType.XPath, api);
			AyaHelper.pushValue(blockEvaluator, xPathId);
		} catch (XPathParseException e) {
			throw new XPathParseRuntimeException(e);
		}
	}
}