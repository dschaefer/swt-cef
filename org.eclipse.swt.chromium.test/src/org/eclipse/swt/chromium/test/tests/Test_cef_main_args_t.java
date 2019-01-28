package org.eclipse.swt.chromium.test.tests;

import org.eclipse.swt.chromium.cef.cef_main_args_t;
import org.junit.jupiter.api.Test;

import com.sun.jna.Library;
import com.sun.jna.Native;

class Test_cef_main_args_t {

	interface TestNative extends Library {
		public static class Loader {
			public static TestNative load() {
				System.setProperty("jna.library.path",
						"/home/dschaefer/eclipse/workspaces/ceftest/testNative/build/default");
				return Native.load("testNative", TestNative.class);
			}
		}

		TestNative INSTANCE = Loader.load();

		void testArgcArgv(cef_main_args_t args);
	}

	@Test
	void test() {
		TestNative lib = TestNative.INSTANCE;

		cef_main_args_t args = new cef_main_args_t(new String[] { "a", "banana", "c" });
		lib.testArgcArgv(args);
	}

}
