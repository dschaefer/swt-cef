package org.eclipse.swt.chromium.cef;

public class cef_string_t extends cef_string_utf16_t {

	public cef_string_t() {
	}

	public cef_string_t(LibCef lib, String string) {
		super(lib, string);
	}

}
