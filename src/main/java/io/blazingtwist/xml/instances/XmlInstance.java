package io.blazingtwist.xml.instances;

import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XMLModifier;

public class XmlInstance extends InstanceData {
	private VTDGen gen;
	private VTDNav nav;
	private XMLModifier mod;

	public XmlInstance(byte[] xmlBytes, boolean ignoreWhitespace) throws Exception {
		gen = new VTDGen();
		gen.enableIgnoredWhiteSpace(!ignoreWhitespace);
		gen.setDoc(xmlBytes);
		gen.parse(false);
		nav = gen.getNav();
		mod = new XMLModifier(nav);
	}

	public VTDGen getGen() {
		return gen;
	}

	public VTDNav getNav() {
		return nav;
	}

	public XMLModifier getMod() {
		return mod;
	}

	@Override
	public void close() {
		super.close();
		if (gen != null) {
			gen.clear();
			gen = null;
		}
		nav = null;
		mod = null;
	}
}
