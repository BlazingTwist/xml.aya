package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import aya.obj.Obj;
import aya.obj.list.ListCollector;
import io.blazingtwist.xml.compat.VtdTokenType;

import java.util.Arrays;

public class VtdAllTokenTypes extends NamedOperator {
	public VtdAllTokenTypes() {
		super("vtd.all_token_types", "void -> token_types::list<sym> :"
				+ " Returns a list of all token types."
				+ " The result represents all possible values returned in the 'type' field of :(vtd.get_token).");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		Obj typeList = Arrays.stream(VtdTokenType.values())
				.map(x -> x.symbol)
				.collect(new ListCollector());
		blockEvaluator.push(typeList);
	}
}
