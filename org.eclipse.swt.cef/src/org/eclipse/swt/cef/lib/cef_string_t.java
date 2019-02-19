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

public class cef_string_t extends cef_string_utf16_t {

	public cef_string_t() {
	}

	public cef_string_t(LibCef lib, String string) {
		super(lib, string);
	}

}
