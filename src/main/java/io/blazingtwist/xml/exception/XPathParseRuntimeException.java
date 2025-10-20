package io.blazingtwist.xml.exception;

import aya.exceptions.runtime.AyaRuntimeException;
import aya.obj.symbol.Symbol;
import aya.obj.symbol.SymbolTable;
import com.ximpleware.XPathParseException;

public class XPathParseRuntimeException extends AyaRuntimeException {
	private static final Symbol sym = SymbolTable.getSymbol("XPathParseException");

	public XPathParseRuntimeException(XPathParseException e) {
		super(e.getMessage());
	}

	@Override
	public Symbol typeSymbol() {
		return sym;
	}
}
