package io.blazingtwist.xml;

import aya.instruction.named.NamedInstructionStore;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.instructions.xmlns.XmlnsExtract;
import io.blazingtwist.xml.instructions.xmlns.XmlnsLoadF;
import io.blazingtwist.xml.instructions.xmlns.XmlnsLoadS;
import io.blazingtwist.xml.instructions.xmlns.XmlnsReplace;

import java.util.Collection;
import java.util.List;

public class XmlnsInstructionStore implements NamedInstructionStore {
    @Override
    public Collection<NamedOperator> getNamedInstructions() {
        return List.of(
                new XmlnsExtract(),
                new XmlnsLoadF(),
                new XmlnsLoadS(),
                new XmlnsReplace()
        );
    }
}
