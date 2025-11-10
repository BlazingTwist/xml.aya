package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import com.ximpleware.ModifyException;
import com.ximpleware.VTDNav;
import com.ximpleware.XMLModifier;
import io.blazingtwist.xml.AyaHelper;
import io.blazingtwist.xml.compat.VtdEncoding;
import io.blazingtwist.xml.compat.VtdTokenType;
import io.blazingtwist.xml.exception.WrapperRuntimeException;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

public class VtdNavInsertAttribute extends NamedOperator {
	public VtdNavInsertAttribute() {
		super("vtd.nav_insert_attribute", "(xml_id|xmlns_id)::num attr::str -> void :"
				+ " Insert an attribute after the cursor.\n"
				+ " The current token may be one of [::starting_tag, ::attr_name, ::attr_ns, ::attr_val]\n"
				+ " - attr : e. g. \" attrName=\"attrVal\"\", notice the starting white space.\n"
				+ "   if you omit the starting whitespace, a single space will be used.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		String text = AyaHelper.popString(blockEvaluator);
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);
		VTDNav nav = xml.getNav();

		if (text.isEmpty() || !Character.isWhitespace(text.charAt(0))) {
			text = " " + text;
		}

		try {
			int curTokenIdx = nav.getCurrentIndex();
			VtdTokenType tokenType = VtdTokenType.fromVtdInt(nav.getTokenType(curTokenIdx));
			switch (tokenType) {
				case starting_tag:
					insertAfter(xml, curTokenIdx, 0, text);
					break;
				case attr_ns:
				case attr_name:
					insertAfter(xml, curTokenIdx + 1, 1, text);
					break;
				case attr_val:
					insertAfter(xml, curTokenIdx, 1, text);
					break;
				default:
					throw new ModifyException("The current token type (" + tokenType.name() + ")is not valid for " + this._name);
			}
		} catch (ModifyException e) {
			throw new WrapperRuntimeException(e);
		}
	}

	private void insertAfter(XmlInstance xml, int tokenIdx, int addOffset, String text) throws ModifyException {
		VTDNav nav = xml.getNav();
		XMLModifier mod = xml.getMod();
		VtdEncoding encoding = VtdEncoding.fromVtdInt(xml.getNav().getEncoding());
		byte[] textBytes = text.getBytes(encoding.charset);

		// why '& 0xffff'? no clue, wasn't documented, don't care.
		int offset = nav.getTokenOffset(tokenIdx) + (nav.getTokenLength(tokenIdx) & 0xffff) + addOffset;
		offset *= encoding.avgBytesPerChar;

		mod.insertBytesAt(offset, textBytes);
	}
}
