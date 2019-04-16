#include "SWTApp.h"
#include <jni.h>

extern "C"
JNIEXPORT int Java_org_eclipse_swt_cef_Chromium_start()
{
	int argc = 1;
	char *argv[1];
	argv[0] = strdup("/home/dschaefer/eclipse/workspaces/ceftest/org.eclipse.swt.cef/cef/jdk/jre/bin/java");

	CefMainArgs main_args(argc, argv);
	CefRefPtr<SWTApp> app(new SWTApp);

	CefSettings settings;
	settings.no_sandbox = true;
	CefString(&settings.locales_dir_path).FromASCII("/home/dschaefer/eclipse/workspaces/ceftest/org.eclipse.swt.cef/cef/lib/locales");
	CefString(&settings.browser_subprocess_path).FromASCII("/home/dschaefer/eclipse/workspaces/ceftest/org.eclipse.swt.cef/cef/lib/subProcess");

	CefInitialize(main_args, settings, app.get(), NULL);

	return 0;
}

extern "C"
JNIEXPORT int Java_org_eclipse_swt_cef_Chromium_work()
{
	CefDoMessageLoopWork();
	return 0;
}
