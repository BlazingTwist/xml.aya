package io.blazingtwist.xml.exception;

import aya.exceptions.runtime.AyaRuntimeException;
import aya.obj.symbol.Symbol;
import aya.obj.symbol.SymbolTable;

public class InvalidInstanceException extends AyaRuntimeException {
	private static final Symbol symInvalidInstance = SymbolTable.getSymbol("InvalidInstanceException");

	public InvalidInstanceException(String message) {
		super(message);
	}

	@Override
	public Symbol typeSymbol() {
		return symInvalidInstance;
	}
}
