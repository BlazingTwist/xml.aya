package io.blazingtwist.xml.compat;

import aya.obj.dict.Dict;
import aya.obj.number.Num;
import aya.obj.symbol.Symbol;
import aya.obj.symbol.SymbolTable;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

import java.util.ArrayList;
import java.util.List;

public class XmlErrorHandler implements ErrorHandler {
	private final Symbol symLineNumber = SymbolTable.getSymbol("line_number");
	private final Symbol symColumnNumber = SymbolTable.getSymbol("column_number");
	private final Symbol symMessage = SymbolTable.getSymbol("message");
	private final Symbol symSeverity = SymbolTable.getSymbol("severity");
	private final Symbol symWarning = SymbolTable.getSymbol("warning");
	private final Symbol symError = SymbolTable.getSymbol("error");
	private final Symbol symFatal = SymbolTable.getSymbol("fatal");

	private final List<Dict> exceptions = new ArrayList<>();

	public List<Dict> getExceptions() {
		return exceptions;
	}

	private void addException(SAXParseException exception, Symbol severity) {
		Dict d = new Dict();
		d.set(symLineNumber, Num.fromInt(exception.getLineNumber()));
		d.set(symColumnNumber, Num.fromInt(exception.getColumnNumber()));
		d.set(symMessage, aya.obj.list.List.fromString(exception.getMessage()));
		d.set(symSeverity, severity);
		exceptions.add(d);
	}

	@Override
	public void warning(SAXParseException exception) {
		addException(exception, symWarning);
	}

	@Override
	public void error(SAXParseException exception) {
		addException(exception, symError);
	}

	@Override
	public void fatalError(SAXParseException exception) {
		addException(exception, symFatal);
	}
}
