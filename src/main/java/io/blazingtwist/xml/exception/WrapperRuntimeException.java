package io.blazingtwist.xml.exception;

import aya.exceptions.runtime.AyaRuntimeException;
import aya.obj.symbol.Symbol;
import aya.obj.symbol.SymbolTable;

public class WrapperRuntimeException extends AyaRuntimeException {

	private final Symbol sym;

	public <T extends Throwable> WrapperRuntimeException(String message, T e) {
		super(message);
		this.initCause(e);
		sym = SymbolTable.getSymbol(e.getClass().getSimpleName());
	}

	public <T extends Throwable> WrapperRuntimeException(T e) {
		super(e.getMessage());
		this.initCause(e);
		sym = SymbolTable.getSymbol(e.getClass().getSimpleName());
	}

	@Override
	public Symbol typeSymbol() {
		return sym;
	}
}
