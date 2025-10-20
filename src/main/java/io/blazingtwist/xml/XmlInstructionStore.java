package io.blazingtwist.xml;

import aya.instruction.named.NamedInstructionStore;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.instructions.xml.XmlClose;
import io.blazingtwist.xml.instructions.xml.XmlDumpS;
import io.blazingtwist.xml.instructions.xml.XmlExtract;
import io.blazingtwist.xml.instructions.xml.XmlLoadF;
import io.blazingtwist.xml.instructions.xml.XmlLoadS;
import io.blazingtwist.xml.instructions.xml.XmlReplace;

import java.util.Collection;
import java.util.List;

public class XmlInstructionStore implements NamedInstructionStore {
    @Override
    public Collection<NamedOperator> getNamedInstructions() {
        return List.of(
                new XmlClose(),
                new XmlDumpS(),
                new XmlExtract(),
                new XmlLoadF(),
                new XmlLoadS(),
                new XmlReplace()
        );
    }
}
