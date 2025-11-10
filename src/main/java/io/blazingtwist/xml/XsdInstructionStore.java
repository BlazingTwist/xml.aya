package io.blazingtwist.xml;

import aya.instruction.named.NamedInstructionStore;
import aya.instruction.named.NamedOperator;
import io.blazingtwist.xml.instructions.xsd.XsdAddF;
import io.blazingtwist.xml.instructions.xsd.XsdAddS;
import io.blazingtwist.xml.instructions.xsd.XsdClose;
import io.blazingtwist.xml.instructions.xsd.XsdNew;
import io.blazingtwist.xml.instructions.xsd.XsdValidate;

import java.util.Collection;
import java.util.List;

public class XsdInstructionStore implements NamedInstructionStore {
	@Override
	public Collection<NamedOperator> getNamedInstructions() {
		return List.of(
				new XsdClose(),
				new XsdAddF(),
				new XsdAddS(),
				new XsdNew(),
				new XsdValidate()
		);
	}
}
