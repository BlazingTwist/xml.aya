package io.blazingtwist.xml.exception;

import aya.exceptions.runtime.AyaRuntimeException;
import aya.obj.symbol.Symbol;
import aya.obj.symbol.SymbolTable;
import com.ximpleware.XPathEvalException;

public class XPathEvalRuntimeException extends AyaRuntimeException {
	private static final Symbol sym = SymbolTable.getSymbol("XPathEvalException");

	public XPathEvalRuntimeException(XPathEvalException e) {
		super(e.getMessage());
	}

	@Override
	public Symbol typeSymbol() {
		return sym;
	}
}
