#include <include/cef_app.h>

class SubProcessApp : public CefApp {
public:
	virtual void OnBeforeCommandLineProcessing(
			const CefString& process_type,
			CefRefPtr<CefCommandLine> command_line)
	{
		command_line->AppendSwitch(CefString("disable-cpu-compositing"));
		command_line->AppendSwitch(CefString("disable-accelerated-2d-canvas"));

		printf("starting subProcess: %s\n", command_line->GetCommandLineString().ToString().c_str());
		fflush(stdout);
	}

private:
	IMPLEMENT_REFCOUNTING(SubProcessApp);
};

int main(int argc, char *argv[])
{
	CefMainArgs main_args(argc, argv);
	CefRefPtr<SubProcessApp> app(new SubProcessApp);

	int exit_code = CefExecuteProcess(main_args, app.get(), NULL);
}
