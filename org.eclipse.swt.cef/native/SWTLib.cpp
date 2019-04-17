#include "SWTApp.h"
#include <jni.h>
#include <gdk/gdkx.h>
#include <X11/Xlib.h>

extern "C"
JNIEXPORT int Java_org_eclipse_swt_cef_Chromium_start(JNIEnv *env, jclass cls, jlong parentId)
{
	int argc = 1;
	char *argv[1];
	argv[0] = strdup("/home/dschaefer/eclipse/workspaces/ceftest/org.eclipse.swt.cef/cef/jdk/jre/bin/java");

	CefMainArgs main_args(argc, argv);
	CefRefPtr<SWTApp> app(new SWTApp(parentId));

	CefSettings settings;
	settings.no_sandbox = true;
	CefString(&settings.locales_dir_path).FromASCII("/home/dschaefer/eclipse/workspaces/ceftest/org.eclipse.swt.cef/cef/lib/locales");
	CefString(&settings.browser_subprocess_path).FromASCII("/home/dschaefer/eclipse/workspaces/ceftest/org.eclipse.swt.cef/cef/lib/subProcess");

	GdkScreen* screen = gdk_screen_get_default();
	GdkVisual *rgba_visual = gdk_screen_get_rgba_visual( screen );
	VisualID xvisualid = gdk_x11_visual_get_xvisual(
	        GDK_X11_VISUAL(rgba_visual))->visualid;
	cef_override_system_visual(xvisualid);
	cef_override_rgba_visual(xvisualid);

	CefInitialize(main_args, settings, app.get(), NULL);

	return 0;
}

extern "C"
JNIEXPORT int Java_org_eclipse_swt_cef_Chromium_work()
{
	CefDoMessageLoopWork();
	return 0;
}
