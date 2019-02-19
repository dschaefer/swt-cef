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

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.core.runtime.Path;
import org.eclipse.swt.cef.Activator;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;

public interface LibCef extends Library {

	public static class Loader {
		public static LibCef load() {
			try {
				File cefDir = Activator.getBundleLocation(new Path("cef"));
				System.setProperty("jna.library.path", cefDir.getCanonicalPath());
				return Native.load("cef", LibCef.class);
			} catch (URISyntaxException | IOException e) {
				Activator.log("Error loading cef library", e);
				return null;
			}
		}
	}

	LibCef INSTANCE = Loader.load();

	///
	// This function should be called on the main application thread to initialize
	// the CEF browser process. The |application| parameter may be NULL. A return
	// value of true (1) indicates that it succeeded and false (0) indicates that it
	// failed. The |windows_sandbox_info| parameter is only used on Windows and may
	// be NULL (see cef_sandbox_win.h for details).
	///
	int cef_initialize(cef_main_args_t args, cef_settings_t settings, cef_app_t application,
			Pointer windows_sandbox_info);

	///
	// Perform a single iteration of CEF message loop processing. This function is
	// provided for cases where the CEF message loop must be integrated into an
	// existing application message loop. Use of this function is not recommended
	// for most users; use either the cef_run_message_loop() function or
	// CefSettings.multi_threaded_message_loop if possible. When using this function
	// care must be taken to balance performance against excessive CPU usage. It is
	// recommended to enable the CefSettings.external_message_pump option when using
	// this function so that
	// cef_browser_process_handler_t::on_schedule_message_pump_work() callbacks can
	// facilitate the scheduling process. This function should only be called on the
	// main application thread and only if cef_initialize() is called with a
	// CefSettings.multi_threaded_message_loop value of false (0). This function
	// will not block.
	///
	void cef_do_message_loop_work();

	///
	// Run the CEF message loop. Use this function instead of an application-
	// provided message loop to get the best balance between performance and CPU
	// usage. This function should only be called on the main application thread and
	// only if cef_initialize() is called with a
	// CefSettings.multi_threaded_message_loop value of false (0). This function
	// will block until a quit message is received by the system.
	///
	void cef_run_message_loop();

	///
	// This function should be called on the main application thread to shut down
	// the CEF browser process before the application exits.
	///
	void cef_shutdown();

	int cef_string_utf8_to_utf16(String src, NativeLong src_len, cef_string_utf16_t output);

}
