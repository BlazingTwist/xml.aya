package io.blazingtwist.xml;

import aya.eval.BlockEvaluator;
import aya.exceptions.runtime.TypeError;
import aya.instruction.named.NamedOperator;
import aya.obj.Obj;
import aya.obj.dict.Dict;
import aya.obj.list.List;
import aya.obj.number.Num;
import aya.util.Casting;

public class AyaHelper {
	public static int popInt(NamedOperator inst, BlockEvaluator blockEvaluator) {
		Obj obj = blockEvaluator.pop();
		if (obj.isa(Obj.NUM)) {
			return Casting.asNumber(obj).toInt();
		} else {
			throw new TypeError(inst, "N", obj);
		}
	}

	public static boolean popBool(NamedOperator inst, BlockEvaluator blockEvaluator) {
		return popInt(inst, blockEvaluator) > 0;
	}

	public static String popString(BlockEvaluator blockEvaluator) {
		return blockEvaluator.pop().str();
	}

	public static Dict popDict(NamedOperator inst, BlockEvaluator blockEvaluator) {
		Obj obj = blockEvaluator.pop();
		if (obj.isa(Obj.DICT)) {
			return Casting.asDict(obj);
		} else {
			throw new TypeError(inst, "D", obj);
		}
	}

	public static void pushValue(BlockEvaluator blockEvaluator, Obj value) {
		blockEvaluator.push(value);
	}

	public static void pushValue(BlockEvaluator blockEvaluator, int value) {
		blockEvaluator.push(new Num(value));
	}

	public static void pushValue(BlockEvaluator blockEvaluator, double value) {
		blockEvaluator.push(new Num(value));
	}

	public static void pushValue(BlockEvaluator blockEvaluator, String value) {
		blockEvaluator.push(List.fromString(value));
	}

	public static void pushValue(BlockEvaluator blockEvaluator, boolean value) {
		blockEvaluator.push(new Num(value ? 1 : 0));
	}
}
