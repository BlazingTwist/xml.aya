package io.blazingtwist.xml.compat;

public class VtdFragment {
	public static long toVtd(int offset, int length) {
		return ((long) offset) | (((long) length) << 32);
	}

	public static int getOffset(long fragment) {
		return (int) fragment;
	}

	public static int getLength(long fragment) {
		return (int) (fragment >> 32);
	}
}
