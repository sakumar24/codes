// Remove characters from string that are in matcher string..(could be done using arrays with same complexity.)

#include<iostream>
#include<map>
using namespace std;

int main()
{
	string match;
	string in;

	map<char,int> mmap;
	map<char,int>::iterator it;

	cout<<"Matcher: "; cin>>match;
	cout<<"Input: "; cin>>in;

	for(int i=0;i<match.length();i++)
		mmap[match[i]] = 1;

	cout<<"output: ";
	for(int i=0;i<in.length();i++)
	{
		it = mmap.find(in[i]);

		if(it == mmap.end())
			cout<<in[i];
	}	
	cout<<endl;
return 0;
}
