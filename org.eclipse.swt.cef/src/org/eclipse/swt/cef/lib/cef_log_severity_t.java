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

public enum cef_log_severity_t {

	///
	// Default logging (currently INFO logging).
	///
	LOGSEVERITY_DEFAULT(0),
	///
	// Verbose logging.
	///
	LOGSEVERITY_VERBOSE(1),

	///
	// DEBUG logging.
	///
	LOGSEVERITY_DEBUG(LOGSEVERITY_VERBOSE),

	///
	// INFO logging.
	///
	LOGSEVERITY_INFO(2),

	///
	// WARNING logging.
	///
	LOGSEVERITY_WARNING(3),

	///
	// ERROR logging.
	///
	LOGSEVERITY_ERROR(4),

	///
	// Completely disable logging.
	///
	LOGSEVERITY_DISABLE(99);

	private int value;

	private cef_log_severity_t(int value) {
		this.value = value;
	}

	private cef_log_severity_t(cef_log_severity_t other) {
		this.value = other.value;
	}

	public int getValue() {
		return value;
	}

}
