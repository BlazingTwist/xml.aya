package io.blazingtwist.xml.instructions;

import aya.eval.BlockEvaluator;
import aya.exceptions.runtime.IOError;
import aya.instruction.named.NamedOperator;
import aya.obj.number.Num;
import io.blazingtwist.xml.InstanceManager;
import io.blazingtwist.xml.XmlInstance;

import java.nio.charset.StandardCharsets;

public class XmlLoadS extends NamedOperator {
    public XmlLoadS() {
        super("xml.loads", "xml::str -> xml_id::num : load an XML-literal from a string");
    }

    @Override
    public void execute(BlockEvaluator blockEvaluator) {
        String xmlStr = blockEvaluator.pop().str();

        final XmlInstance instance;
        try {
            instance = new XmlInstance(xmlStr.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new IOError(this.opName(), "xml::str", "Malformed XML document. " + e.getMessage());
        }
        int instanceId = InstanceManager.createInstance(InstanceManager.InstanceType.Xml, instance);
        blockEvaluator.push(Num.fromInt(instanceId));
    }
}
