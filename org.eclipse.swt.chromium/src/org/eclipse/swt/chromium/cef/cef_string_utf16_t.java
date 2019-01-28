package org.eclipse.swt.chromium.cef;

import com.sun.jna.Callback;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

@FieldOrder({ "str", "length", "dtor" })
public class cef_string_utf16_t extends Structure {

	public Pointer str;
	public NativeLong length;

	public static interface dtor extends Callback {
		void invoke(short[] str);
	}

	public dtor dtor;

	public cef_string_utf16_t() {
	}

	public cef_string_utf16_t(LibCef lib, String string) {
		ensureAllocated();
		lib.cef_string_utf8_to_utf16(string, new NativeLong(string.length()), this);
	}

}
