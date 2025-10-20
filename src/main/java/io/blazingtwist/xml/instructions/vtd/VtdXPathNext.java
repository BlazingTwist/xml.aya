package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import com.ximpleware.NavException;
import com.ximpleware.XPathEvalException;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.exception.NavRuntimeException;
import io.blazingtwist.xml.exception.XPathEvalRuntimeException;
import io.blazingtwist.xml.instances.AutoPilotInstance;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;

public class VtdXPathNext extends NamedOperator {
	public VtdXPathNext() {
		super("vtd.xpath_next", "xpath_id::num -> num :"
				+ " This method returns the next node in the nodeset or -1 if there is are no more nodes.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		AutoPilotInstance api = InstanceManager.popInstance(this, blockEvaluator, InstanceType.XPath);
		try {
			AyaHelper.pushValue(blockEvaluator, api.getAutoPilot().evalXPath());
		} catch (XPathEvalException e) {
			throw new XPathEvalRuntimeException(e);
		} catch (NavException e) {
			throw new NavRuntimeException(e);
		}
	}
}