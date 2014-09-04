#include<iostream>
#include<stdlib.h>

using namespace std;

int main()
{
	char* pass = (char*)malloc(sizeof(char)*9);
	pass[0] = 'P';
	pass[1] = 'a';
	pass[2] = 's';
	pass[3] = 's';
	pass[4] = 'w';
	pass[5] = 'o';
	pass[6] = 'r';
	pass[7] = 'd';
	pass[8] = '\0';
	
	cout<<"Pass string:"<<pass<<" Address:"<<&pass<<endl;
	free(pass);
return 0;
}