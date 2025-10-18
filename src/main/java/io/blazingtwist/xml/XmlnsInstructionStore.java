package io.blazingtwist.xml;

import aya.instruction.named.NamedInstructionStore;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.instructions.XmlnsClose;
import io.blazingtwist.xml.instructions.XmlnsDumpF;
import io.blazingtwist.xml.instructions.XmlnsDumpS;
import io.blazingtwist.xml.instructions.XmlnsExtract;
import io.blazingtwist.xml.instructions.XmlnsLoadF;
import io.blazingtwist.xml.instructions.XmlnsLoadS;
import io.blazingtwist.xml.instructions.XmlnsReplace;

import java.util.Collection;
import java.util.List;

public class XmlnsInstructionStore implements NamedInstructionStore {
    @Override
    public Collection<NamedOperator> getNamedInstructions() {
        return List.of(
                new XmlnsLoadF(),
                new XmlnsLoadS(),
                new XmlnsExtract(),
                new XmlnsReplace(),
                new XmlnsDumpS(),
                new XmlnsDumpF(),
                new XmlnsClose()
        );
    }
}
