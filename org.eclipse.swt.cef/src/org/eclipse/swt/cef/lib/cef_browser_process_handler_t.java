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
// Structure used to implement browser process callbacks. The functions of this
// structure will be called on the browser process main thread unless otherwise
// indicated.
///
@FieldOrder({ "base", "on_context_initialized", "on_before_child_process_launch", "on_render_process_thread_created",
		"get_print_handler", "on_schedule_message_pump_work" })
public class cef_browser_process_handler_t extends Structure {
	public cef_browser_process_handler_t() {
		ensureAllocated();
		base.size = new NativeLong(size());
	}

	///
	// Base structure.
	///
	public cef_base_ref_counted_t base;

	///
	// Called on the browser process UI thread immediately after the CEF context
	// has been initialized.
	///
	public interface on_context_initialized_t extends Callback {
		void invoke(cef_browser_process_handler_t self);
	}

	public on_context_initialized_t on_context_initialized;

	///
	// Called before a child process is launched. Will be called on the browser
	// process UI thread when launching a render process and on the browser
	// process IO thread when launching a GPU or plugin process. Provides an
	// opportunity to modify the child process command line. Do not keep a
	// reference to |command_line| outside of this function.
	///
	public interface on_before_child_process_launch_t extends Callback {
		void invoke(cef_browser_process_handler_t self, cef_command_line command_line);
	}

	public on_before_child_process_launch_t on_before_child_process_launch;

	///
	// Called on the browser process IO thread after the main thread has been
	// created for a new render process. Provides an opportunity to specify extra
	// information that will be passed to
	// cef_render_process_handler_t::on_render_thread_created() in the render
	// process. Do not keep a reference to |extra_info| outside of this function.
	///
	public interface on_render_process_thread_created_t extends Callback {
		void invoke(cef_browser_process_handler_t self, cef_list_value_t extra_info);
	}

	public on_render_process_thread_created_t on_render_process_thread_created;

	///
	// Return the handler for printing on Linux. If a print handler is not
	// provided then printing will not be supported on the Linux platform.
	///
	public interface get_print_handler_t extends Callback {
		cef_print_handler_t invoke(cef_browser_process_handler_t self);
	}

	public get_print_handler_t get_print_handler;

	///
	// Called from any thread when work has been scheduled for the browser process
	// main (UI) thread. This callback is used in combination with CefSettings.
	// external_message_pump and cef_do_message_loop_work() in cases where the CEF
	// message loop must be integrated into an existing application message loop
	// (see additional comments and warnings on CefDoMessageLoopWork). This
	// callback should schedule a cef_do_message_loop_work() call to happen on the
	// main (UI) thread. |delay_ms| is the requested delay in milliseconds. If
	// |delay_ms| is <= 0 then the call should happen reasonably soon. If
	// |delay_ms| is > 0 then the call should be scheduled to happen after the
	// specified delay and any currently pending scheduled call should be
	// cancelled.
	///
	public interface on_schedule_message_pump_work_t extends Callback {
		void invoke(cef_browser_process_handler_t self, long delay_ms);
	}

	public on_schedule_message_pump_work_t on_schedule_message_pump_work;

}
