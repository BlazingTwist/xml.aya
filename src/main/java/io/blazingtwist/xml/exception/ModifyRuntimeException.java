package io.blazingtwist.xml.exception;

import aya.exceptions.runtime.AyaRuntimeException;
import aya.obj.symbol.Symbol;
import aya.obj.symbol.SymbolTable;
import com.ximpleware.ModifyException;

public class ModifyRuntimeException extends AyaRuntimeException {
	private static final Symbol sym = SymbolTable.getSymbol("ModifyException");

	public ModifyRuntimeException(ModifyException e) {
		super(e.getMessage());
	}

	@Override
	public Symbol typeSymbol() {
		return sym;
	}
}
