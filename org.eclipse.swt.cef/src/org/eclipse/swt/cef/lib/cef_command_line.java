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
// Structure used to create and/or parse command line arguments. Arguments with
// '--', '-' and, on Windows, '/' prefixes are considered switches. Switches
// will always precede any arguments without switch prefixes. Switches can
// optionally have a value specified using the '=' delimiter (e.g.
// "-switch=value"). An argument of "--" will terminate switch parsing with all
// subsequent tokens, regardless of prefix, being interpreted as non-switch
// arguments. Switch names are considered case-insensitive. This structure can
// be used before cef_initialize() is called.
///
@FieldOrder({ "base", "is_valid", "is_read_only", "copy", "init_from_argv", "init_from_string", "reset", "get_argv",
		"get_command_line_string", "get_program", "set_program", "has_switches", "has_switch", "get_switch_value",
		"get_switches", "append_switch", "append_switch_with_value", "has_arguments", "get_arguments",
		"append_argument", "prepend_wrapper" })
public class cef_command_line extends Structure {

	public cef_command_line() {
		ensureAllocated();
		base.size = new NativeLong(size());
	}

	///
	// Base structure.
	///
	public cef_base_ref_counted_t base;

	///
	// Returns true (1) if this object is valid. Do not call any other functions
	// if this function returns false (0).
	///
	public interface is_valid_t extends Callback {
		int invoke(cef_command_line self);
	}

	public is_valid_t is_valid;

	///
	// Returns true (1) if the values of this object are read-only. Some APIs may
	// expose read-only objects.
	///
	public interface is_read_only_t extends Callback {
		int invoke(cef_command_line self);
	}

	public is_read_only_t is_read_only;

	///
	// Returns a writable copy of this object.
	///
	public interface copy_t extends Callback {
		cef_command_line invoke(cef_command_line self);
	}

	public copy_t copy;

	///
	// Initialize the command line with the specified |argc| and |argv| values.
	// The first argument must be the name of the program. This function is only
	// supported on non-Windows platforms.
	///
	public interface init_from_argv_t extends Callback {
		void invoke(cef_command_line self, int argc, String[] argv);
	}

	public init_from_argv_t init_from_argv;

	///
	// Initialize the command line with the string returned by calling
	// GetCommandLineW(). This function is only supported on Windows.
	///
	public interface init_from_string_t extends Callback {
		void invoke(cef_command_line self, cef_string_t command_line);
	}

	public init_from_string_t init_from_string;

	///
	// Reset the command-line switches and arguments but leave the program
	// component unchanged.
	///
	public interface reset_t extends Callback {
		void invoke(cef_command_line self);
	}

	public reset_t reset;

	///
	// Retrieve the original command line string as a vector of strings. The argv
	// array: { program, [(--|-|/)switch[=value]]*, [--], [argument]* }
	///
	public interface get_argv_t extends Callback {
		void invoke(cef_command_line self, cef_string_list_t argv);
	}

	public get_argv_t get_argv;

	///
	// Constructs and returns the represented command line string. Use this
	// function cautiously because quoting behavior is unclear.
	///
	// The resulting string must be freed by calling cef_string_userfree_free().
	public interface get_command_line_string_t extends Callback {
		cef_string_userfree_t invoke(cef_command_line self);
	}

	public get_command_line_string_t get_command_line_string;

	///
	// Get the program part of the command line string (the first item).
	///
	// The resulting string must be freed by calling cef_string_userfree_free().
	public interface get_program_t extends Callback {
		cef_string_userfree_t invoke(cef_command_line self);
	}

	public get_program_t get_program;

	///
	// Set the program part of the command line string (the first item).
	///
	public interface set_program_t extends Callback {
		void invoke(cef_command_line self, cef_string_t program);
	}

	public set_program_t set_program;

	///
	// Returns true (1) if the command line has switches.
	///
	public interface has_switches_t extends Callback {
		int invoke(cef_command_line self);
	}

	public has_switches_t has_switches;

	///
	// Returns true (1) if the command line contains the given switch.
	///
	public interface has_switch_t extends Callback {
		int invoke(cef_command_line self, cef_string_t name);
	}

	public has_switch_t has_switch;

	///
	// Returns the value associated with the given switch. If the switch has no
	// value or isn't present this function returns the NULL string.
	///
	// The resulting string must be freed by calling cef_string_userfree_free().
	public interface get_switch_value_t extends Callback {
		cef_string_userfree_t invoke(cef_command_line self, cef_string_t name);
	}

	public get_switch_value_t get_switch_value;

	///
	// Returns the map of switch names and values. If a switch has no value an
	// NULL string is returned.
	///
	public interface get_switches_t extends Callback {
		void invoke(cef_command_line self, cef_string_map_t switches);
	}

	public get_switches_t get_switches;

	///
	// Add a switch to the end of the command line. If the switch has no value
	// pass an NULL value string.
	///
	public interface append_switch_t extends Callback {
		void invoke(cef_command_line self, cef_string_t name);
	}

	public append_switch_t append_switch;

	///
	// Add a switch with the specified value to the end of the command line.
	///
	public interface append_switch_with_value_t extends Callback {
		void invoke(cef_command_line self, cef_string_t name, cef_string_t value);
	}

	public append_switch_with_value_t append_switch_with_value;

	///
	// True if there are remaining command line arguments.
	///
	public interface has_arguments_t extends Callback {
		int invoke(cef_command_line self);
	}

	public has_arguments_t has_arguments;

	///
	// Get the remaining command line arguments.
	///
	public interface get_arguments_t extends Callback {
		void invoke(cef_command_line self, cef_string_list_t arguments);
	}

	public get_arguments_t get_arguments;

	///
	// Add an argument to the end of the command line.
	///
	public interface append_argument_t extends Callback {
		void invoke(cef_command_line self, cef_string_t argument);
	}

	public append_argument_t append_argument;

	///
	// Insert a command before the current command. Common for debuggers, like
	// "valgrind" or "gdb --args".
	///
	public interface prepend_wrapper_t extends Callback {
		void invoke(cef_command_line self, cef_string_t wrapper);
	}

	public prepend_wrapper_t prepend_wrapper;

}
