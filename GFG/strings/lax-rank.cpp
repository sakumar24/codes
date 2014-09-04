#include<iostream>
#include<string>
#include<map>

using namespace std;

int factorial(int x)
{
	if( x <= 1)
		return 1;
	else
		return ( x * factorial(x-1) );
}	


int main()
{
	string str;
	cout<<"Str: "; getline(cin,str);

	int len = str.length();
	int rank = 1;
	
	//	string temp = in;
	//	temp.sort();

	char temp[len+1];
	temp[0] = 'A';

	for(int i=0;i<len;i++)
	{	
		char cur = str[i];
		temp[i+1] = cur;
		int j = i+1;
	
		while(temp[j] < temp[j-1])
		{
			cur = temp[j-1];
			temp[j-1] = temp[j];
			temp[j] = cur;
			j--;
		}
	}	
	map<char,int> alpRank;
	map<char,int>::iterator it; 

	for(int i=1;i<len+1;i++)
		alpRank[temp[i]] = i-1;

//	for(it=alpRank.begin();it!=alpRank.end();it++)
//		cout<<it->first<<" rank: "<<it->second<<endl;

	for(int i=0;i<len-1;i++)
	{
		//	cout<<len-1-i<<" fact: "<<factorial(len-1-i)<<endl;
		//	cout<<"rank: "<<rank<<endl;		
		rank = rank + (alpRank[str[i]] * factorial(len-1-i));
	
		for(it=alpRank.begin();it!=alpRank.end();it++)
		{
			if(it->second > alpRank[str[i]])
				it->second--;
		}
	}

	cout<<"Rank: "<<rank<<endl;
return 0;
}
