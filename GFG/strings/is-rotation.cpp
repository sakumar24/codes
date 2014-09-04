#include<iostream>
#include<string>
using namespace std;

int main()
{
	string str1,str2;

	cout<<"First string: "; cin>>str1;
	cout<<"Second string: "; cin>>str2;

	string temp = str1.append(str1);

	if(temp.find(str2) == string::npos)
		cout<<"NO"<<endl;
	else
		cout<<"YES"<<endl;
 
return 0;
}
