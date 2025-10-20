package io.blazingtwist.xml.compat;

import aya.obj.symbol.Symbol;
import aya.obj.symbol.SymbolTable;
import com.ximpleware.VTDNav;

public enum VtdTokenType {
	unknown,
	starting_tag,
	ending_tag,
	attr_name,
	attr_ns,
	attr_val,
	character_data,
	comment,
	pi_name,
	pi_val,
	dec_attr_name,
	dec_attr_val,
	cdata_val,
	dtd_val,
	document,
	;

	public static VtdTokenType fromVtdInt(int tokenType) {
		switch (tokenType) {
			case VTDNav.TOKEN_STARTING_TAG:
				return starting_tag;
			case VTDNav.TOKEN_ENDING_TAG:
				return ending_tag;
			case VTDNav.TOKEN_ATTR_NAME:
				return attr_name;
			case VTDNav.TOKEN_ATTR_NS:
				return attr_ns;
			case VTDNav.TOKEN_ATTR_VAL:
				return attr_val;
			case VTDNav.TOKEN_CHARACTER_DATA:
				return character_data;
			case VTDNav.TOKEN_COMMENT:
				return comment;
			case VTDNav.TOKEN_PI_NAME:
				return pi_name;
			case VTDNav.TOKEN_PI_VAL:
				return pi_val;
			case VTDNav.TOKEN_DEC_ATTR_NAME:
				return dec_attr_name;
			case VTDNav.TOKEN_DEC_ATTR_VAL:
				return dec_attr_val;
			case VTDNav.TOKEN_CDATA_VAL:
				return cdata_val;
			case VTDNav.TOKEN_DTD_VAL:
				return dtd_val;
			case VTDNav.TOKEN_DOCUMENT:
				return document;
			default:
				return unknown;
		}
	}

	public final Symbol symbol;

	VtdTokenType() {
		symbol = SymbolTable.getSymbol(this.name());
	}

}
