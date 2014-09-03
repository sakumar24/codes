#include<stdio.h>

int main()
{


}


void bufCopy ( char * Dest ,char* Src)
{
	char *p = Dest ;
	while (*Src ! = '\0' )
		*p++ = *Src++;
	*p = '\0' ;
}

