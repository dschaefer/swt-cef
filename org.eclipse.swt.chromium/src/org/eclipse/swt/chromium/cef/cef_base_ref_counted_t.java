package org.eclipse.swt.chromium.cef;

import com.sun.jna.Callback;
import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

///
// All ref-counted framework structures must include this structure first.
///
@FieldOrder({ "size", "add_ref", "release", "has_one_ref", "has_at_least_one_ref" })
public class cef_base_ref_counted_t extends Structure {

	///
	// Size of the data structure.
	///
	public NativeLong size;

	///
	// Called to increment the reference count for the object. Should be called
	// for every new copy of a pointer to a given object.
	///
	interface add_ref_t extends Callback {
		void invoke(cef_base_ref_counted_t self);
	}

	public add_ref_t add_ref;

	///
	// Called to decrement the reference count for the object. If the reference
	// count falls to 0 the object should self-delete. Returns true (1) if the
	// resulting reference count is 0.
	///
	interface release_t extends Callback {
		int invoke(cef_base_ref_counted_t self);
	}

	public release_t release;

	///
	// Returns true (1) if the current reference count is 1.
	///
	interface has_one_ref_t extends Callback {
		int invoke(cef_base_ref_counted_t self);
	}

	public has_one_ref_t has_one_ref;

	///
	// Returns true (1) if the current reference count is at least 1.
	///
	interface has_at_least_one_ref_t extends Callback {
		int invoke(cef_base_ref_counted_t self);
	}

	public has_at_least_one_ref_t has_at_least_one_ref;

}
