#include <include/capi/cef_app_capi.h>

int main(int argc, char **argv) {
	cef_main_args_t main_args = {};
	main_args.argc = argc;
	main_args.argv = argv;

	cef_execute_process(&main_args, NULL, NULL);
}
