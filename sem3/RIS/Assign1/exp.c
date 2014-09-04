#include<stdio.h>

int main()
{
	char buf[3];
	char login[] = {'a','b','b','b','b','a','b','b','b','b','a','b','b','b','b','a','b','b','b','b'};
	int i;
	for(i=0;i<20;i++)
		buf[i] = login[i];
	printf("%s",buf);
return 0;
}