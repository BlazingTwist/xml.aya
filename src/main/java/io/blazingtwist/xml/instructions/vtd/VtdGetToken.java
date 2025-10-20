package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import aya.obj.dict.Dict;
import aya.obj.number.Num;
import aya.obj.symbol.Symbol;
import aya.obj.symbol.SymbolTable;
import com.ximpleware.VTDNav;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.compat.VtdTokenType;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

import static io.blazingtwist.xml.AyaHelper.pushValue;

public class VtdGetToken extends NamedOperator {
	public VtdGetToken() {
		super("vtd.get_token", "(xml_id|xmlns_id)::num token_idx::num -> :{\n"
				+ "    type::sym : the token type of the token\n"
				+ "    depth::num : the depth value of a token (>=0).\n"
				+ "    offset::num : the starting offset (unit in native char) of the token at the given index.\n"
				+ "    length::num : the token length at the given index value please refer to VTD spec for more details. Length is in terms of the UTF char unit. For prefixed tokens, it is the qualified name length. When ns is not enabled, return the full name length for attribute name and element name When ns is enabled, return an int with upper 16 bit for prefix length, lower 16 bit for qname length\n"
				+ "}");
	}

	private final Symbol symType = SymbolTable.getSymbol("type");
	private final Symbol symDepth = SymbolTable.getSymbol("depth");
	private final Symbol symOffset = SymbolTable.getSymbol("offset");
	private final Symbol symLength = SymbolTable.getSymbol("length");

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		int tokenIdx = AyaHelper.popInt(this, blockEvaluator);
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);

		VTDNav nav = xml.getNav();
		VtdTokenType type = VtdTokenType.fromVtdInt(nav.getTokenType(tokenIdx));
		int depth = nav.getTokenDepth(tokenIdx);
		int offset = nav.getTokenOffset(tokenIdx);
		int length = nav.getTokenLength(tokenIdx);

		switch (type) {
			case character_data:
			case comment:
			case pi_name:
			case pi_val:
			case cdata_val:
				depth += 1; // for some reason, vtd-xml does not increment the depth for these nodes...
		}

		Dict tokenD = new Dict();
		tokenD.set(symType, type.symbol);
		tokenD.set(symDepth, new Num(depth));
		tokenD.set(symOffset, new Num(offset));
		tokenD.set(symLength, new Num(length));
		pushValue(blockEvaluator, tokenD);
	}
}
