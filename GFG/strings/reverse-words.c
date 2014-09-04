#include<stdio.h>
#include<string.h>

//using namespace std;

int main()
{
	char str[100];
	printf("str: "); gets(str);//="This is an example";
	char out[20][20];
	int c=0,i;
	
	char* tok = strtok(str," ");
	
	while(tok != NULL)
	{
		strcpy(out[c++],tok);
		tok = strtok(NULL," ");
	}
	
	for(i=c-1;i>=0;i--)
		printf("%s ",out[i]);
	printf("\n");
return 0;
}
