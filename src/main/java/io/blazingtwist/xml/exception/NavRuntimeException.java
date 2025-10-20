package io.blazingtwist.xml.exception;

import aya.exceptions.runtime.AyaRuntimeException;
import aya.obj.symbol.Symbol;
import aya.obj.symbol.SymbolTable;

public class NavRuntimeException extends AyaRuntimeException {
	private static final Symbol symNavException = SymbolTable.getSymbol("NavRuntimeException");

	public NavRuntimeException(com.ximpleware.NavException e) {
		super(e.getMessage());
	}

	@Override
	public Symbol typeSymbol() {
		return symNavException;
	}
}
