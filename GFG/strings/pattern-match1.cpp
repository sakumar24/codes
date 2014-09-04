#include<iostream>
#include<string>
using namespace std;

int main()
{
	string str,pat;
	cout<<"Pattern: " ; cin>>pat;
	cout<<"String: " ; cin>>str;

	int p_len = pat.length();
	int s_len = str.length();

	for(int s=0;s<=(s_len-p_len);s++) // s => start of match.
	{
		//cout<<"s:"<<s<<endl;
		int i=0,e=s;
		while(i < p_len)
		{
			//cout<<pat[i]<<str[e]<<endl;
			if(pat[i] == str[e])
			{
				i++;
				e++;
			}
			else
				break;
		}
		if(i == p_len)		
			cout<<"Pattern found at index: "<<s<<endl;	// here e is end of match.
	}
return 0;
}
