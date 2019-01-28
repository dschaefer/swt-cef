package org.eclipse.swt.chromium.cef;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.core.runtime.Path;
import org.eclipse.swt.chromium.Activator;

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
