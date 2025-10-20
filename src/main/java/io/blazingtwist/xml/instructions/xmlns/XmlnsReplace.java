package io.blazingtwist.xml.instructions.xmlns;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;

public class XmlnsReplace extends NamedOperator {
	public XmlnsReplace() {
		super("xmlns.replace", "xmlns_id::num ns::ns_dict ops::list<replace_op> -> void\n"
				+ " typedef ns_dict = dict<prefix::str,uri::str>\n"
				+ " - define the namespace prefixes used in your xpath expressions.\n"
				+ " typedef replace_op = [xpath::str replace_mapper]\n"
				+ " - xpath = an XPath 1.0 expression\n"
				+ " typedef replace_mapper = value::str\n"
				+ "                        | supplier::block(void -> str)\n"
				+ "                        | mapper::block(str -> str)\n"
				+ " - value = replace the matches with a fixed value\n"
				+ " - supplier = replace the matches with the value returned by a block\n"
				+ " - mapper = replace the matches by executing the block with the current value\n"
				+ "\n"
				+ "replace_op Examples:\n"
				+ "  [\"//@attr\" \"1\"] -> replace all attribute values with the literal \"1\"\n"
				+ "  [\"//text()\" {\"\\s\" \"\" .&}] -> remove all whitespace in text nodes");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		// TODO execute
	}
}
