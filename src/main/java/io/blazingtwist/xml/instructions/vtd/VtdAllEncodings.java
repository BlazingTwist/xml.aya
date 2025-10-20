package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import aya.obj.Obj;
import aya.obj.list.ListCollector;
import aya.obj.symbol.SymbolTable;
import io.blazingtwist.xml.compat.VtdEncoding;

import java.util.Arrays;

public class VtdAllEncodings extends NamedOperator {
	public VtdAllEncodings() {
		super("vtd.all_encodings", "void -> charset_names::list<sym> :"
				+ " Returns a list of all supported encodings."
				+ " The result represents all possible values returned by :(vtd.get_encoding).");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		Obj encodingList = Arrays.stream(VtdEncoding.values())
				.map(x -> SymbolTable.getName(x.symbol))
				.distinct() // filter by distinct symbol-name
				.map(SymbolTable::getSymbol) // then convert back to Obj.
				.collect(new ListCollector());
		blockEvaluator.push(encodingList);
	}
}
