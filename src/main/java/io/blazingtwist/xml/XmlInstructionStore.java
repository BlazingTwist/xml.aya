package io.blazingtwist.xml;

import aya.instruction.named.NamedInstructionStore;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.instructions.XmlClose;
import io.blazingtwist.xml.instructions.XmlDumpF;
import io.blazingtwist.xml.instructions.XmlDumpS;
import io.blazingtwist.xml.instructions.XmlExtract;
import io.blazingtwist.xml.instructions.XmlLoadF;
import io.blazingtwist.xml.instructions.XmlLoadS;
import io.blazingtwist.xml.instructions.XmlReplace;

import java.util.Collection;
import java.util.List;

public class XmlInstructionStore implements NamedInstructionStore {
    @Override
    public Collection<NamedOperator> getNamedInstructions() {
        return List.of(
                new XmlLoadF(),
                new XmlLoadS(),
                new XmlExtract(),
                new XmlReplace(),
                new XmlDumpS(),
                new XmlDumpF(),
                new XmlClose()
        );
    }
}
