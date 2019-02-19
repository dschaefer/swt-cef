/*********************************************************************
 * Copyright (c) 2019 QNX Software Systems and others
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *********************************************************************/
package org.eclipse.swt.cef;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.core.runtime.Path;
import org.eclipse.swt.cef.lib.LibCef;
import org.eclipse.swt.cef.lib.cef_app_t;
import org.eclipse.swt.cef.lib.cef_browser_process_handler_t;
import org.eclipse.swt.cef.lib.cef_log_severity_t;
import org.eclipse.swt.cef.lib.cef_main_args_t;
import org.eclipse.swt.cef.lib.cef_settings_t;
import org.eclipse.swt.cef.lib.cef_string_t;
import org.eclipse.swt.widgets.Composite;

public class Chromium extends Composite {

	LibCef lib = LibCef.INSTANCE;

	cef_main_args_t args;
	cef_settings_t settings = new cef_settings_t();
	cef_app_t app = new cef_app_t();
	cef_browser_process_handler_t handler = new cef_browser_process_handler_t();

	public Chromium(Composite parent, int style) {
		super(parent, style);

		try {
			File cefDir = Activator.getBundleLocation(new Path("cef"));

			args = new cef_main_args_t(new String[] { new File(cefDir, "libcef.so").getCanonicalPath() });

			settings.log_severity = cef_log_severity_t.LOGSEVERITY_WARNING.getValue();
			settings.no_sandbox = 1;
			settings.external_message_pump = 1;
			settings.browser_subprocess_path = new cef_string_t(lib, new File(cefDir, "subProcess").getCanonicalPath());
			settings.resources_dir_path = new cef_string_t(lib, cefDir.getCanonicalPath());
			settings.locales_dir_path = new cef_string_t(lib, new File(cefDir, "locales").getCanonicalPath());

			handler.on_context_initialized = (self) -> {
				System.err.println("Context initialized");
			};
			handler.on_schedule_message_pump_work = (self, delay_ms) -> {
				System.err.println("on schedule messae pump work");
				org.eclipse.swt.widgets.Display.getCurrent().syncExec(() -> {
					lib.cef_do_message_loop_work();
				});
			};

			app.get_browser_process_handler = (self) -> {
				System.err.println("get browser process handler " + self.getClass().getName());
				return handler;
			};

			int rc = lib.cef_initialize(args, settings, app, null);
			System.err.println("init complete rc = " + rc);
		} catch (URISyntaxException | IOException e) {
			Activator.log("starting up chromium event loop", e);
		}
	}

	public void setUrl(String url) {

	}
}
