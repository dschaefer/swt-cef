#include <stdio.h>

struct args {
	int argc;
	char **argv;
};

extern "C"
void testArgcArgv(struct args *args)
{
	fprintf(stderr, "sizeof(size_t) = %ld\n", sizeof(size_t));
	fprintf(stderr, "argc = %d\n", args->argc);
	fprintf(stderr, "argv = 0x%p\n", args->argv);
	for (int i = 0; i < args->argc; i++) {
		fprintf(stderr, "argv[%d] = '%s'\n", i, args->argv[i]);
	}
}
