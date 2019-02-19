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

import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

///
// Initialization settings. Specify NULL or 0 to get the recommended default
// values. Many of these and other settings can also configured using command-
// line switches.
///
@FieldOrder({ "size", "no_sandbox", "browser_subprocess_path", "framework_dir_path", "multi_threaded_message_loop",
		"external_message_pump", "windowless_rendering_enabled", "command_line_args_disabled", "cache_path",
		"user_data_path", "persist_session_cookies", "persist_user_preferences", "user_agent", "product_version",
		"locale", "log_file", "log_severity", "javascript_flags", "resources_dir_path", "locales_dir_path",
		"pack_loading_disabled", "remote_debugging_port", "uncaught_exception_stack_size", "ignore_certificate_errors",
		"enable_net_security_expiration", "background_color", "accept_language_list" })
public class cef_settings_t extends Structure {

	///
	// Size of this structure.
	///
	public NativeLong size;

	///
	// Set to true (1) to disable the sandbox for sub-processes. See
	// cef_sandbox_win.h for requirements to enable the sandbox on Windows. Also
	// configurable using the "no-sandbox" command-line switch.
	///
	public int no_sandbox;

	///
	// The path to a separate executable that will be launched for sub-processes.
	// If this value is empty on Windows or Linux then the main process executable
	// will be used. If this value is empty on macOS then a helper executable must
	// exist at "Contents/Frameworks/<app> Helper.app/Contents/MacOS/<app> Helper"
	// in the top-level app bundle. See the comments on CefExecuteProcess() for
	// details. Also configurable using the "browser-subprocess-path" command-line
	// switch.
	///
	public cef_string_t browser_subprocess_path;

	///
	// The path to the CEF framework directory on macOS. If this value is empty
	// then the framework must exist at "Contents/Frameworks/Chromium Embedded
	// Framework.framework" in the top-level app bundle. Also configurable using
	// the "framework-dir-path" command-line switch.
	///
	public cef_string_t framework_dir_path;

	///
	// Set to true (1) to have the browser process message loop run in a separate
	// thread. If false (0) than the CefDoMessageLoopWork() function must be
	// called from your application message loop. This option is only supported on
	// Windows and Linux.
	///
	public int multi_threaded_message_loop;

	///
	// Set to true (1) to control browser process main (UI) thread message pump
	// scheduling via the CefBrowserProcessHandler::OnScheduleMessagePumpWork()
	// callback. This option is recommended for use in combination with the
	// CefDoMessageLoopWork() function in cases where the CEF message loop must be
	// integrated into an existing application message loop (see additional
	// comments and warnings on CefDoMessageLoopWork). Enabling this option is not
	// recommended for most users; leave this option disabled and use either the
	// CefRunMessageLoop() function or multi_threaded_message_loop if possible.
	///
	public int external_message_pump;

	///
	// Set to true (1) to enable windowless (off-screen) rendering support. Do not
	// enable this value if the application does not use windowless rendering as
	// it may reduce rendering performance on some systems.
	///
	public int windowless_rendering_enabled;

	///
	// Set to true (1) to disable configuration of browser process features using
	// standard CEF and Chromium command-line arguments. Configuration can still
	// be specified using CEF data structures or via the
	// CefApp::OnBeforeCommandLineProcessing() method.
	///
	public int command_line_args_disabled;

	///
	// The location where cache data will be stored on disk. If empty then
	// browsers will be created in "incognito mode" where in-memory caches are
	// used for storage and no data is persisted to disk. HTML5 databases such as
	// localStorage will only persist across sessions if a cache path is
	// specified. Can be overridden for individual CefRequestContext instances via
	// the CefRequestContextSettings.cache_path value.
	///
	public cef_string_t cache_path;

	///
	// The location where user data such as spell checking dictionary files will
	// be stored on disk. If empty then the default platform-specific user data
	// directory will be used ("~/.cef_user_data" directory on Linux,
	// "~/Library/Application Support/CEF/User Data" directory on Mac OS X,
	// "Local Settings\Application Data\CEF\User Data" directory under the user
	// profile directory on Windows).
	///
	public cef_string_t user_data_path;

	///
	// To persist session cookies (cookies without an expiry date or validity
	// interval) by default when using the global cookie manager set this value to
	// true (1). Session cookies are generally intended to be transient and most
	// Web browsers do not persist them. A |cache_path| value must also be
	// specified to enable this feature. Also configurable using the
	// "persist-session-cookies" command-line switch. Can be overridden for
	// individual CefRequestContext instances via the
	// CefRequestContextSettings.persist_session_cookies value.
	///
	public int persist_session_cookies;

	///
	// To persist user preferences as a JSON file in the cache path directory set
	// this value to true (1). A |cache_path| value must also be specified
	// to enable this feature. Also configurable using the
	// "persist-user-preferences" command-line switch. Can be overridden for
	// individual CefRequestContext instances via the
	// CefRequestContextSettings.persist_user_preferences value.
	///
	public int persist_user_preferences;

	///
	// Value that will be returned as the User-Agent HTTP header. If empty the
	// default User-Agent string will be used. Also configurable using the
	// "user-agent" command-line switch.
	///
	public cef_string_t user_agent;

	///
	// Value that will be inserted as the product portion of the default
	// User-Agent string. If empty the Chromium product version will be used. If
	// |userAgent| is specified this value will be ignored. Also configurable
	// using the "product-version" command-line switch.
	///
	public cef_string_t product_version;

	///
	// The locale string that will be passed to WebKit. If empty the default
	// locale of "en-US" will be used. This value is ignored on Linux where locale
	// is determined using environment variable parsing with the precedence order:
	// LANGUAGE, LC_ALL, LC_MESSAGES and LANG. Also configurable using the "lang"
	// command-line switch.
	///
	public cef_string_t locale;

	///
	// The directory and file name to use for the debug log. If empty a default
	// log file name and location will be used. On Windows and Linux a "debug.log"
	// file will be written in the main executable directory. On Mac OS X a
	// "~/Library/Logs/<app name>_debug.log" file will be written where <app name>
	// is the name of the main app executable. Also configurable using the
	// "log-file" command-line switch.
	///
	public cef_string_t log_file;

	///
	// The log severity. Only messages of this severity level or higher will be
	// logged. Also configurable using the "log-severity" command-line switch with
	// a value of "verbose", "info", "warning", "error", "error-report" or
	// "disable".
	///
	public int log_severity;

	///
	// Custom flags that will be used when initializing the V8 JavaScript engine.
	// The consequences of using custom flags may not be well tested. Also
	// configurable using the "js-flags" command-line switch.
	///
	public cef_string_t javascript_flags;

	///
	// The fully qualified path for the resources directory. If this value is
	// empty the cef.pak and/or devtools_resources.pak files must be located in
	// the module directory on Windows/Linux or the app bundle Resources directory
	// on Mac OS X. Also configurable using the "resources-dir-path" command-line
	// switch.
	///
	public cef_string_t resources_dir_path;

	///
	// The fully qualified path for the locales directory. If this value is empty
	// the locales directory must be located in the module directory. This value
	// is ignored on Mac OS X where pack files are always loaded from the app
	// bundle Resources directory. Also configurable using the "locales-dir-path"
	// command-line switch.
	///
	public cef_string_t locales_dir_path;

	///
	// Set to true (1) to disable loading of pack files for resources and locales.
	// A resource bundle handler must be provided for the browser and render
	// processes via CefApp::GetResourceBundleHandler() if loading of pack files
	// is disabled. Also configurable using the "disable-pack-loading" command-
	// line switch.
	///
	public int pack_loading_disabled;

	///
	// Set to a value between 1024 and 65535 to enable remote debugging on the
	// specified port. For example, if 8080 is specified the remote debugging URL
	// will be http://localhost:8080. CEF can be remotely debugged from any CEF or
	// Chrome browser window. Also configurable using the "remote-debugging-port"
	// command-line switch.
	///
	public int remote_debugging_port;

	///
	// The number of stack trace frames to capture for uncaught exceptions.
	// Specify a positive value to enable the CefRenderProcessHandler::
	// OnUncaughtException() callback. Specify 0 (default value) and
	// OnUncaughtException() will not be called. Also configurable using the
	// "uncaught-exception-stack-size" command-line switch.
	///
	public int uncaught_exception_stack_size;

	///
	// Set to true (1) to ignore errors related to invalid SSL certificates.
	// Enabling this setting can lead to potential security vulnerabilities like
	// "man in the middle" attacks. Applications that load content from the
	// internet should not enable this setting. Also configurable using the
	// "ignore-certificate-errors" command-line switch. Can be overridden for
	// individual CefRequestContext instances via the
	// CefRequestContextSettings.ignore_certificate_errors value.
	///
	public int ignore_certificate_errors;

	///
	// Set to true (1) to enable date-based expiration of built in network
	// security information (i.e. certificate transparency logs, HSTS preloading
	// and pinning information). Enabling this option improves network security
	// but may cause HTTPS load failures when using CEF binaries built more than
	// 10 weeks in the past. See https://www.certificate-transparency.org/ and
	// https://www.chromium.org/hsts for details. Also configurable using the
	// "enable-net-security-expiration" command-line switch. Can be overridden for
	// individual CefRequestContext instances via the
	// CefRequestContextSettings.enable_net_security_expiration value.
	///
	public int enable_net_security_expiration;

	///
	// Background color used for the browser before a document is loaded and when
	// no document color is specified. The alpha component must be either fully
	// opaque (0xFF) or fully transparent (0x00). If the alpha component is fully
	// opaque then the RGB components will be used as the background color. If the
	// alpha component is fully transparent for a windowed browser then the
	// default value of opaque white be used. If the alpha component is fully
	// transparent for a windowless (off-screen) browser then transparent painting
	// will be enabled.
	///
	public cef_color_t background_color;

	///
	// Comma delimited ordered list of language codes without any whitespace that
	// will be used in the "Accept-Language" HTTP header. May be overridden on a
	// per-browser basis using the CefBrowserSettings.accept_language_list value.
	// If both values are empty then "en-US,en" will be used. Can be overridden
	// for individual CefRequestContext instances via the
	// CefRequestContextSettings.accept_language_list value.
	///
	public cef_string_t accept_language_list;

	public cef_settings_t() {
		this.size = new NativeLong(size());
	}

}
