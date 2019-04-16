#include "SWTApp.h"
#include <include/cef_client.h>
#include <include/wrapper/cef_helpers.h>

class SWTClient : public CefClient {
private:
	IMPLEMENT_REFCOUNTING(SWTClient);
};

SWTApp::SWTApp(long parentId)
	: parentId(parentId)
{

}

SWTApp::~SWTApp()
{
}

void SWTApp::OnBeforeCommandLineProcessing(
	const CefString& process_type,
	CefRefPtr<CefCommandLine> command_line)
{
	command_line->AppendSwitch(CefString("disable-gpu"));
	command_line->AppendSwitch(CefString("disable-software-rasterizer"));

	printf("starting main: %s\n", command_line->GetCommandLineString().ToString().c_str());
	fflush(stdout);
}

void SWTApp::OnContextInitialized() {
	CefRefPtr<SWTClient> client(new SWTClient);

	CefBrowserSettings browser_settings;
	std::string url = "https://cdtdoug.ca"; // "https://webglsamples.org/aquarium/aquarium.html";

	CefWindowInfo window_info;
	window_info.SetAsChild(parentId, CefRect(0, 0, 300, 200));

	CefBrowserHost::CreateBrowser(window_info, client, url, browser_settings, NULL);
}
