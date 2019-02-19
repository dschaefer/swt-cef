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
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

///
// Implement this structure to provide handler implementations. Methods will be
// called by the process and/or thread indicated.
///
@FieldOrder({ "base", "on_before_command_line_processing", "on_register_custom_schemes", "get_resource_bundle_handler",
		"get_browser_process_handler", "get_render_process_handler" })
public class cef_app_t extends Structure {
	public cef_app_t() {
		ensureAllocated();
		base.size = new NativeLong(size());
	}

	///
	// Base structure.
	///
	public cef_base_ref_counted_t base;

	///
	// Provides an opportunity to view and/or modify command-line arguments before
	// processing by CEF and Chromium. The |process_type| value will be NULL for
	// the browser process. Do not keep a reference to the cef_command_line_t
	// object passed to this function. The CefSettings.command_line_args_disabled
	// value can be used to start with an NULL command-line object. Any values
	// specified in CefSettings that equate to command-line arguments will be set
	// before this function is called. Be cautious when using this function to
	// modify command-line arguments for non-browser processes as this may result
	// in undefined behavior including crashes.
	///
	public interface on_before_command_line_processing_t extends Callback {
		void invoke(cef_app_t self, cef_string_t process_type, cef_command_line command_line);
	}

	public on_before_command_line_processing_t on_before_command_line_processing;

	///
	// Provides an opportunity to register custom schemes. Do not keep a reference
	// to the |registrar| object. This function is called on the main thread for
	// each process and the registered schemes should be the same across all
	// processes.
	///
	public interface on_register_custom_schemes_t extends Callback {
		void invoke(cef_app_t self, cef_scheme_registrar_t registrar);
	}

	public on_register_custom_schemes_t on_register_custom_schemes;

	///
	// Return the handler for resource bundle events. If
	// CefSettings.pack_loading_disabled is true (1) a handler must be returned.
	// If no handler is returned resources will be loaded from pack files. This
	// function is called by the browser and render processes on multiple threads.
	///
	public interface get_resource_bundle_handler_t extends Callback {
		cef_resource_bundle_handler_t invoke(cef_app_t self);
	}

	public get_resource_bundle_handler_t get_resource_bundle_handler;

	///
	// Return the handler for functionality specific to the browser process. This
	// function is called on multiple threads in the browser process.
	///
	public interface get_browser_process_handler_t extends Callback {
		cef_browser_process_handler_t invoke(cef_app_t self);
	}

	public get_browser_process_handler_t get_browser_process_handler;

	///
	// Return the handler for functionality specific to the render process. This
	// function is called on the render process main thread.
	///
	public interface get_render_process_handler_t extends Callback {
		cef_render_process_handler_t invoke(cef_app_t self);
	}

	public get_render_process_handler_t get_render_process_handler;

}
