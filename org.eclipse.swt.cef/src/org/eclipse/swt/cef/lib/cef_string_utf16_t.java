/*********************************************************************
 * Copyright (c) 2019 QNX Software Systems and others
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *********************************************************************/
package org.eclipse.swt.cef.lib;

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

	@Override
	public String toString() {
		int n = length.intValue();
		char[] chars = new char[n];
		for (int i = 0; i < n; i++) {
			chars[i] = (char) str.getShort(i * 2);
		}
		return new String(chars);
	}

}
