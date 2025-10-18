package io.blazingtwist.xml;

import com.ximpleware.VTDGen;

public class XmlInstance extends InstanceManager.InstanceData {
    private VTDGen gen;

    public XmlInstance(byte[] xmlBytes) throws Exception {
        gen = new VTDGen();
        gen.setDoc(xmlBytes);
        gen.parse(false);
    }

    public VTDGen getGen() {
        return gen;
    }

    @Override
    public void close() {
        super.close();
        if (gen != null) {
            gen.clear();
            gen = null;
        }
    }
}
