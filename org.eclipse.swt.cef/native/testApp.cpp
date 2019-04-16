#include <include/cef_app.h>
#include <include/cef_client.h>
#include <include/wrapper/cef_helpers.h>
#include <string>

class MyClient : public CefClient {
private:
	IMPLEMENT_REFCOUNTING(MyClient);
};

class MyApp: public CefApp, public CefBrowserProcessHandler {
public:
	virtual CefRefPtr<CefBrowserProcessHandler> GetBrowserProcessHandler() override {
		return this;
	}

	virtual void OnContextInitialized() override {
		CEF_REQUIRE_UI_THREAD();

		CefRefPtr<MyClient> client(new MyClient);

		CefBrowserSettings browser_settings;
		std::string url = "https://webglsamples.org/aquarium/aquarium.html";

		CefWindowInfo window_info;
		CefBrowserHost::CreateBrowser(window_info, client, url, browser_settings, NULL);
	}

private:
	IMPLEMENT_REFCOUNTING(MyApp);
};

int main(int argc, char *argv[])
{
	CefMainArgs main_args(argc, argv);
	CefRefPtr<MyApp> app(new MyApp);

	int exit_code = CefExecuteProcess(main_args, NULL, NULL);
	if (exit_code >= 0) {
		// We were a subprocess, exit now
		return exit_code;
	}

	CefSettings settings;
	settings.no_sandbox = true;
	CefString(&settings.locales_dir_path).FromASCII("/home/dschaefer/eclipse/workspaces/ceftest/org.eclipse.swt.cef/cef/lib/locales");

	CefInitialize(main_args, settings, app.get(), NULL);

	CefRunMessageLoop();

	CefShutdown();

	return 0;
}
