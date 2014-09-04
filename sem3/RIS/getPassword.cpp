#include<iostream>
#include<stdlib.h>

using namespace std;

int main()
{
	char* pass = (char*)malloc(sizeof(char)*8);	
	cout<<"Pass string:"<<pass<<" Address:"<<&pass<<endl;
return 0;
}