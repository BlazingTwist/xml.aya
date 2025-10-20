package io.blazingtwist.xml.instructions.xmlns;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;

public class XmlnsExtract extends NamedOperator
{
    public XmlnsExtract( )
    {
		super("xmlns.extract", "xml_id::num ns::ns_dict query_dict::dict<str,query> -> output_dict::dict<str,list>\n"
				+ " typedef ns_dict = dict<prefix::str,uri::str>\n"
				+ " - define the namespace prefixes used in your xpath expressions.\n"
				+ " typedef query = xpath::str\n"
				+ "               | [xpath::str]\n"
				+ "               | [xpath::str type::sym]\n"
				+ "               | [xpath::str query_dict::dict<str,query>]\n"
				+ " - xpath = an XPath 1.0 expression\n"
				+ " - type = one of (::num, ::str, ::char), asserts the element-type of values matched by the xpath\n"
				+ "\n"
				+ "The output_dict will receive the same keys as the query_dict.\n"
				+ "query_dict Examples:\n"
				+ "  ex1. :{\"//@attr\":x} -> :{[\"a\" \"b\"]:x}\n"
				+ "  ex2. :{[\"//node/text()\" ::num]:y} -> :{[1 2]:y}\n"
				+ "  ex3. :{\n"
				+ "    [\"//node\" :{\n"
				+ "      \"/text()\":inner\n"
				+ "    }]:outer\n"
				+ "  } -> :{ [\n"
				+ "    :{ \"node 1 text\":inner }\n"
				+ "    :{ \"node 2 text\":inner }\n"
				+ "  ]:outer }");
    }

    @Override
    public void execute( BlockEvaluator blockEvaluator )
    {
        // TODO execute
    }
}
