#include <stddef.h>

#define BASE_SZ 10
#define EOS '\0'

void r_strcpy(char* addr1,char* addr2)
{
	int i;
	for(i=0;addr2[i] != EOS;i++)
		addr1[i] = addr2[i];
}