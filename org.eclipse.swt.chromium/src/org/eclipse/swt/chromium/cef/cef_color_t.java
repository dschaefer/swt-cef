package org.eclipse.swt.chromium.cef;

import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

@FieldOrder({ "value" })
public class cef_color_t extends Structure {
	public int value;
}
