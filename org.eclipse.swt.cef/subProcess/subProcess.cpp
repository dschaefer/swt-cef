#include <include/capi/cef_app_capi.h>
#include <stdio.h>

void CEF_CALLBACK on_before_command_line_processing(
        struct _cef_app_t* self, const cef_string_t* process_type,
        struct _cef_command_line_t* command_line) {
	const char *disable_gpu_compositing_str = "disable-cpu-compositing";
	cef_string_t disable_gpu_compositing;
	cef_string_utf8_to_utf16(disable_gpu_compositing_str, strlen(disable_gpu_compositing_str), &disable_gpu_compositing);
	command_line->append_switch(command_line, &disable_gpu_compositing);

	const char *disableAccelerated2dCanvas_str = "disable-accelerated-2d-canvas";
	cef_string_t disableAccelerated2dCanvas;
	cef_string_utf8_to_utf16(disableAccelerated2dCanvas_str, strlen(disableAccelerated2dCanvas_str), &disableAccelerated2dCanvas);
	command_line->append_switch(command_line, &disableAccelerated2dCanvas);

	//cef_string_userfree_t args = command_line->get_command_line_string(command_line);
	//cef_string_utf8_t argsStr;
	//cef_string_utf16_to_utf8(args->str, args->length, &argsStr);

	//fprintf(stderr, "Subprocess args: %s\n", argsStr.str);

	//free(args);
}

int main(int argc, char **argv) {
	fprintf(stderr, "Subprocess starting: %s\n", argv[1]);
	cef_main_args_t main_args = {};
	main_args.argc = argc;
	main_args.argv = argv;

	cef_app_t app = {};
	app.base.size = sizeof(app);
	app.on_before_command_line_processing = on_before_command_line_processing;

	cef_execute_process(&main_args, &app, NULL);
}
