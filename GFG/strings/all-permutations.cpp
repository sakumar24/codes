#include<iostream>
using namespace std;

void permute(string str,int i,int n)
{
	if(i == n)
		cout<<str<<endl;
	else
	{
	for(int j=i;j<=n;j++)
	{
		char temp = str[i];
		str[i] = str[j];
		str[j] = temp;

		permute(str,i+1,n);

		temp = str[i];
		str[i] = str[j];
		str[j] = temp;
	}
	}
}
int main()
{
	string str;
	cout<<"str: "; cin>>str;
	
	permute(str,0,str.length()-1);
return 0;
}
