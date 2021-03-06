#include<iostream>
#include<string>

using namespace std;

int main()
{
	string str1,str2;
	cout<<"str1: "; getline(cin,str1);
	cout<<"str2: "; getline(cin,str2);

	int m = str1.length();
	int n = str2.length();

	int alp1[256]={0};
	for(int i=0;i<m;i++)
		alp1[(int) str1[i]]++;

	int match = 0;

	for(int i=0;i<n;i++)
	{
		int index = (int)str2[i] ;
		if(alp1[index] > 0)	
		{
			match++;
			alp1[index]--;
		}
	}
	if(match == n && m == n)
		cout<<"YES"<<endl;
	else
		cout<<"NO"<<endl;
return 0;
}
