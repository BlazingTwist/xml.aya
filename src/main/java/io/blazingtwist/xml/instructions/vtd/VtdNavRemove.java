package io.blazingtwist.xml.instructions.vtd;

import aya.eval.BlockEvaluator;
import aya.instruction.named.NamedOperator;
import com.ximpleware.ModifyException;
import com.ximpleware.NavException;
import com.ximpleware.VTDNav;
import com.ximpleware.XMLModifier;
import io.blazingtwist.xml.compat.VtdEncoding;
import io.blazingtwist.xml.compat.VtdFragment;
import io.blazingtwist.xml.exception.WrapperRuntimeException;
import io.blazingtwist.xml.instances.InstanceManager;
import io.blazingtwist.xml.instances.InstanceType;
import io.blazingtwist.xml.instances.XmlInstance;

public class VtdNavRemove extends NamedOperator {
	public VtdNavRemove() {
		super("vtd.nav_remove", "(xml_id|xmlns_id)::num -> void :"
				+ " Removes content from the master XML document.\n"
				+ " If the cursor is on an element, then the entire element referred is removed.\n"
				+ " If the cursor is on an attribute, then the corresponding attribute name/value pair is removed.\n"
				+ " If the token type is one of text, CDATA or comment, then the entire node, including the starting and ending delimiting text surrounding the content, is removed.");
	}

	@Override
	public void execute(BlockEvaluator blockEvaluator) {
		XmlInstance xml = InstanceManager.popInstance(this, blockEvaluator, InstanceType.Xml, InstanceType.Xmlns);
		try {
			VTDNav nav = xml.getNav();
			int curIdx = nav.getCurrentIndex();
			int tokenType = nav.getTokenType(curIdx);
			if (tokenType == VTDNav.TOKEN_ATTR_NAME || tokenType == VTDNav.TOKEN_ATTR_NS || tokenType == VTDNav.TOKEN_ATTR_VAL) {
				if (tokenType == VTDNav.TOKEN_ATTR_VAL) {
					curIdx--;
				}
				int attrStart = nav.getTokenOffset(curIdx);
				int attrEnd = nav.getTokenOffset(curIdx + 1) + nav.getTokenLength(curIdx + 1) + 1; // I *think* +1 is for the closing quote...
				VtdEncoding enc = VtdEncoding.fromVtdInt(nav.getEncoding());
				long attrFragment = VtdFragment.toVtd(attrStart * enc.avgBytesPerChar, (attrEnd - attrStart) * enc.avgBytesPerChar);
				removeFragmentAndWhitespace(xml, attrFragment);
			} else if (tokenType == VTDNav.TOKEN_STARTING_TAG) {
				removeFragmentAndWhitespace(xml, nav.getElementFragment());
			} else {
				xml.getMod().remove();
			}
		} catch (Exception e) {
			throw new WrapperRuntimeException(e);
		}
	}

	private void removeFragmentAndWhitespace(XmlInstance xml, long fragment) throws NavException, ModifyException {
		/* removing leading whitespace is preferable to trailing whitespace:
			consider:
				<root>
					<a/>
				</root>
			remove 'a' with leading whitespace:
				<root>
				</root>
			remove 'a' with trailing whitespace:
				<root>
					</root>
		*/
		VTDNav nav = xml.getNav();
		XMLModifier mod = xml.getMod();
		fragment = nav.expandWhiteSpaces(fragment, VTDNav.WS_LEADING);
		mod.remove(fragment);
	}
}