package io.blazingtwist.xml;

import aya.instruction.named.NamedInstructionStore;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.instructions.XsdClose;
import io.blazingtwist.xml.instructions.XsdLoadF;
import io.blazingtwist.xml.instructions.XsdLoadS;
import io.blazingtwist.xml.instructions.XsdValidate;

import java.util.Collection;
import java.util.List;

public class XsdInstructionStore implements NamedInstructionStore {
    @Override
    public Collection<NamedOperator> getNamedInstructions() {
        return List.of(
                new XsdLoadF(),
                new XsdLoadS(),
                new XsdValidate(),
                new XsdClose()
        );
    }
}
