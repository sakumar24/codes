#include<stdio.h>
#include<stdlib.h>
int main()
{
	char* buf = (char*) malloc(10);
	buf = "sanjeev";
	printf("Before free buf:%s",buf);
	free(buf);
	printf("\nAfter free buf:%s",buf);
return 0;
}