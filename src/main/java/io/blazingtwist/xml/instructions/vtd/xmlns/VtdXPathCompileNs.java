package io.blazingtwist.xml.instructions.vtd.xmlns;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import aya.obj.Obj;
import aya.obj.dict.Dict;
import aya.obj.symbol.Symbol;
import aya.util.Pair;
import com.ximpleware.AutoPilot;
import com.ximpleware.XPathParseException;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.exception.XPathParseRuntimeException;
import io.blazingtwist.xml.instances.AutoPilotInstance;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlnsInstance;

public class VtdXPathCompileNs extends NamedOperator {
	public VtdXPathCompileNs() {
		super("vtd.xpath_compile_ns", "xmlns_id::num ns_dict::dict xpath::str -> xpath_id::num :"
				+ " Compiles the given xpath and returns an ID for use with the other vtx.xpath instructions.\n"
				+ " The XPath will modify the Navigator of the given XML instance. Thus it can be used in combination with the vdx.nav instructions.\n"
				+ " - ns_dict::dict<str,str> : namespace URIs keyed by their prefix, e.g. :{\"my/ns/uri\":mnu} can then be used in an xpath like this: '//mnu:node'");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		String xPath = AyaHelper.popString(blockEvaluator);
		Dict nsDict = AyaHelper.popDict(this, blockEvaluator);
		XmlnsInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xmlns);
		try {
			AutoPilotInstance api = new AutoPilotInstance(xml);
			AutoPilot ap = api.getAutoPilot();
			for (Pair<Symbol, Obj> kvp : nsDict.items()) {
				ap.declareXPathNameSpace(kvp.first().name(), kvp.second().str());
			}
			ap.selectXPath(xPath);
			int xPathId = InstanceManager.createInstance(InstanceType.XPath, api);
			AyaHelper.pushValue(blockEvaluator, xPathId);
		} catch (XPathParseException e) {
			throw new XPathParseRuntimeException(e);
		}
	}
}