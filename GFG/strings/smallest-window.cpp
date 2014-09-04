#include<iostream>
#include<string.h>

using namespace std;

int main()
{
	string input,match;
	cout<<"Str: "; getline(cin,input);
	cout<<"matcher: "; getline(cin,match);
	
	int m_len = match.length();
	int i_len = input.length();
	int min_len = i_len; 
	string final;

	int check[256] = {0};
	for(int i=0;i<m_len;i++)
		check[(int)match[i]]++;
	
	for(int s=0;s<i_len;s++)
	{
		int copy[256]={0};
		for(int i=0;i<256;i++)
			copy[i] = check[i];

		int w_len = 0;
		int matches = 0;
		string temp = "";

		for(int j=s;j<i_len;j++)
		{
			int index = (int)input[j];
			temp += (input[j]);
		
			if(copy[index] > 0)	
			{
				copy[index]--;
				matches++;

				if(matches == m_len)
				{
					int w_len = j - s+1;
					if(w_len < min_len)
					{
						min_len = w_len;
						final = temp;

					break;
					}				
				}
			}			
		}
	}
	cout<<"Min len: "<<min_len<<endl;
	cout<<"String: "<<final<<endl;
return 0;
}
