package io.blazingtwist.xml.exception;

import aya.exceptions.runtime.AyaRuntimeException;
import aya.obj.symbol.Symbol;
import aya.obj.symbol.SymbolTable;

import java.io.UnsupportedEncodingException;

public class UnsupportedEncodingRuntimeException extends AyaRuntimeException {
	private static final Symbol sym = SymbolTable.getSymbol("UnsupportedEncodingException");

	public UnsupportedEncodingRuntimeException(UnsupportedEncodingException e) {
		super(e.getMessage());
	}

	@Override
	public Symbol typeSymbol() {
		return sym;
	}
}
