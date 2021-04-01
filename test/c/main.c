#include <stdio.h>
#include "randomData.h"

int main(void) {
	FILE* f = fopen("../cOutFile", "wb");
	if (f == NULL)
		return -1;

	fwrite(randomData, sizeof(unsigned char), randomData_length, f);
	fclose(f);
	return 0;
}

