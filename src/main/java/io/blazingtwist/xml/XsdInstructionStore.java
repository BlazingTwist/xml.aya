package io.blazingtwist.xml;

import aya.instruction.named.NamedInstructionStore;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.instructions.xsd.XsdClose;
import io.blazingtwist.xml.instructions.xsd.XsdLoadF;
import io.blazingtwist.xml.instructions.xsd.XsdLoadS;
import io.blazingtwist.xml.instructions.xsd.XsdValidate;

import java.util.Collection;
import java.util.List;

public class XsdInstructionStore implements NamedInstructionStore {
    @Override
    public Collection<NamedOperator> getNamedInstructions() {
        return List.of(
                new XsdClose(),
                new XsdLoadF(),
                new XsdLoadS(),
                new XsdValidate()
        );
    }
}
