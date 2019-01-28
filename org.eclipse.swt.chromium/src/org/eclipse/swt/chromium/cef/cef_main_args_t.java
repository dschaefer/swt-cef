package org.eclipse.swt.chromium.cef;

import java.nio.charset.StandardCharsets;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

@FieldOrder({ "argc", "argv" })
public class cef_main_args_t extends Structure {

	public int argc;
	public Pointer argv;

	public cef_main_args_t(String[] args) {
		this.argc = args.length;
		this.argv = new Memory(this.argc * Native.POINTER_SIZE);

		for (int i = 0; i < this.argc; i++) {
			String arg = args[i];
			Pointer str = new Memory(arg.length() * 2 + 2);
			str.setString(0, arg, StandardCharsets.UTF_8.name());
			this.argv.setPointer(i * Native.POINTER_SIZE, str);
		}
	}

}
