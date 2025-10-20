package io.blazingtwist.xml.compat;

import aya.obj.symbol.Symbol;
import aya.obj.symbol.SymbolTable;
import com.ximpleware.VTDNav;

import java.nio.charset.Charset;

public enum VtdEncoding {
	unknown(Charset.defaultCharset()),
	us_ascii(),
	iso_8859_1(),
	utf_8(),
	iso_8859_2(),
	iso_8859_3(),
	iso_8859_4(),
	iso_8859_5(),
	iso_8859_6(),
	iso_8859_7(),
	iso_8859_8(),
	iso_8859_9(),
	iso_8859_10(),
	iso_8859_11(),
	iso_8859_12(),
	iso_8859_13(),
	iso_8859_14(),
	iso_8859_15(),
	iso_8859_16(),
	windows_1250(),
	windows_1251(),
	windows_1252(),
	windows_1253(),
	windows_1254(),
	windows_1255(),
	windows_1256(),
	windows_1257(),
	windows_1258(),
	utf_16be(),
	utf_16le(),
	;

	public static VtdEncoding fromVtdInt(int encoding) {
		switch (encoding) {
			case VTDNav.FORMAT_ASCII:
				return us_ascii;
			case VTDNav.FORMAT_ISO_8859_1:
				return iso_8859_1;
			case VTDNav.FORMAT_UTF8:
				return utf_8;
			case VTDNav.FORMAT_ISO_8859_2:
				return iso_8859_2;
			case VTDNav.FORMAT_ISO_8859_3:
				return iso_8859_3;
			case VTDNav.FORMAT_ISO_8859_4:
				return iso_8859_4;
			case VTDNav.FORMAT_ISO_8859_5:
				return iso_8859_5;
			case VTDNav.FORMAT_ISO_8859_6:
				return iso_8859_6;
			case VTDNav.FORMAT_ISO_8859_7:
				return iso_8859_7;
			case VTDNav.FORMAT_ISO_8859_8:
				return iso_8859_8;
			case VTDNav.FORMAT_ISO_8859_9:
				return iso_8859_9;
			case VTDNav.FORMAT_ISO_8859_10:
				return iso_8859_10;
			case VTDNav.FORMAT_ISO_8859_11:
				return iso_8859_11;
			case VTDNav.FORMAT_ISO_8859_12:
				return iso_8859_12;
			case VTDNav.FORMAT_ISO_8859_13:
				return iso_8859_13;
			case VTDNav.FORMAT_ISO_8859_14:
				return iso_8859_14;
			case VTDNav.FORMAT_ISO_8859_15:
				return iso_8859_15;
			case VTDNav.FORMAT_ISO_8859_16:
				return iso_8859_16;
			case VTDNav.FORMAT_WIN_1250:
				return windows_1250;
			case VTDNav.FORMAT_WIN_1251:
				return windows_1251;
			case VTDNav.FORMAT_WIN_1252:
				return windows_1252;
			case VTDNav.FORMAT_WIN_1253:
				return windows_1253;
			case VTDNav.FORMAT_WIN_1254:
				return windows_1254;
			case VTDNav.FORMAT_WIN_1255:
				return windows_1255;
			case VTDNav.FORMAT_WIN_1256:
				return windows_1256;
			case VTDNav.FORMAT_WIN_1257:
				return windows_1257;
			case VTDNav.FORMAT_WIN_1258:
				return windows_1258;
			case VTDNav.FORMAT_UTF_16BE:
				return utf_16be;
			case VTDNav.FORMAT_UTF_16LE:
				return utf_16le;
			default:
				return unknown;
		}
	}

	public final Symbol symbol;
	public final Charset charset;

	VtdEncoding() {
		String charsetName = this.name().replace("_", "-");
		this.symbol = SymbolTable.getSymbol(charsetName);
		Charset charset;
		try {
			charset = Charset.forName(charsetName);
		} catch (Exception e) {
			charset = Charset.defaultCharset();
		}
		this.charset = charset;
	}

	VtdEncoding(Charset charset) {
		this.symbol = SymbolTable.getSymbol(charset.name());
		this.charset = charset;
	}
}
