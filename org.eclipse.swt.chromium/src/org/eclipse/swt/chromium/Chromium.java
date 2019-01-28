package org.eclipse.swt.chromium;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.core.runtime.Path;
import org.eclipse.swt.chromium.cef.LibCef;
import org.eclipse.swt.chromium.cef.cef_log_severity_t;
import org.eclipse.swt.chromium.cef.cef_main_args_t;
import org.eclipse.swt.chromium.cef.cef_settings_t;
import org.eclipse.swt.chromium.cef.cef_string_t;
import org.eclipse.swt.widgets.Composite;

public class Chromium extends Composite {

	public Chromium(Composite parent, int style) {
		super(parent, style);

		synchronized (eventLoop) {
			if (!eventLoop.isAlive()) {
				eventLoop.start();
			}
		}
	}

	private static Thread eventLoop = new Thread() {
		@Override
		public void run() {
			try {
				LibCef lib = LibCef.INSTANCE;
				File cefDir = Activator.getBundleLocation(new Path("cef"));

				cef_main_args_t args = new cef_main_args_t(
						new String[] { new File(cefDir, "libcef.so").getCanonicalPath() });

				cef_settings_t settings = new cef_settings_t();
				settings.log_severity = cef_log_severity_t.LOGSEVERITY_DEFAULT.getValue();
				settings.no_sandbox = 1;
				settings.browser_subprocess_path = new cef_string_t(lib,
						new File(cefDir, "subProcess").getCanonicalPath());
				settings.resources_dir_path = new cef_string_t(lib, cefDir.getCanonicalPath());
				settings.locales_dir_path = new cef_string_t(lib, new File(cefDir, "locales").getCanonicalPath());

				int rc = lib.cef_initialize(args, settings, null, null);
				System.err.println("init complete rc = " + rc);
				lib.cef_run_message_loop();
				System.err.println("Message loop exited");
				lib.cef_shutdown();
			} catch (URISyntaxException | IOException e) {
				Activator.log("starting up chromium event loop", e);
			}
		}
	};

	public void setUrl(String url) {

	}
}
