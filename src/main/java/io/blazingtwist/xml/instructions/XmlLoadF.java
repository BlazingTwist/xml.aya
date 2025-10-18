package io.blazingtwist.xml.instructions;

import aya.eval.BlockEvaluator;
import aya.exceptions.runtime.IOError;
import aya.instruction.named.NamedOperator;
import aya.obj.number.Num;
import aya.util.FileUtils;
import io.blazingtwist.xml.InstanceManager;
import io.blazingtwist.xml.XmlInstance;

public class XmlLoadF extends NamedOperator {
    public XmlLoadF() {
        super("xml.loadf", "file::str -> xml_id::num : load an XML document from a file");
    }

    @Override
    public void execute(BlockEvaluator blockEvaluator) {
        String fileName = blockEvaluator.pop().str();
        final byte[] fileBytes;
        try {
            fileBytes = FileUtils.readAllBytes(FileUtils.resolveFile(fileName));
        } catch (Exception e) {
            throw new IOError(this.opName(), fileName, e.getMessage());
        }

        final XmlInstance instance;
        try {
            instance = new XmlInstance(fileBytes);
        } catch (Exception e) {
            throw new IOError(this.opName(), fileName, "Malformed XML document. " + e.getMessage());
        }
        int instanceId = InstanceManager.createInstance(InstanceManager.InstanceType.Xml, instance);
        blockEvaluator.push(Num.fromInt(instanceId));
    }
}
