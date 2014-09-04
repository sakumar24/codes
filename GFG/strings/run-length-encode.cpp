#include<iostream>

using namespace std;

int main()
{
	string str;
	cout<<"str: ";cin>>str;

	int c=1;
	char cur = str[0];
	for(int i=1;i<=str.length();i++)
	{
		if(str[i] == cur)
			c++;
		else
		{
			cout<<cur<<c;
			c=1;
			cur = str[i];
		}
	}
	cout<<endl;
return 0;
}
