// Print input string without any repetation, and print count of duplicates..

#include<iostream>
#include<string>

using namespace std;

int main()
{
	string in;
	int alp[256] = {0};
	int index;

	cin>>in;
	for(int i=0;i<in.length();i++)
	{
		index = in[i];
		if(alp[index] == 0)
		{
			cout<<in[i];
			alp[index] = 1;
		}		
		else
			alp[index]++;
	}
	cout<<endl;
	cout<<"Duplicates:"<<endl;
		for(int i=0;i<256;i++)
		{
			if(alp[i] > 1)
				cout<<"count of "<<char(i)<<":"<<alp[i]<<endl; 
		}
return 0;
}
