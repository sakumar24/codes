#include<iostream>
#include<string>
#include<stdlib.h>

using namespace std;

int main()
{
	string str,pat;
	
	cout<<"String: " ; getline(cin,str);
	cout<<"Pattern: " ; getline(cin,pat);
	int p_len = pat.length();
	int s_len = str.length();

// Make table for pattern:
	int p_table[p_len];

	int i=0, m_flag=0, j=1,cur = 0;
	p_table[i] = -1;
	p_table[j] = 0;

	while(j < p_len-1)
	{		
		if(pat[i] == pat[j])
		{
			p_table[++j] = ++cur;
			m_flag = 1;
			i++;
		}
		else
		{
			if(m_flag == 0)
				p_table[++j] = 0;			
			else
			{
				cur = 0;
				i = 0;
				m_flag = 0;
			}
		}
	}
//	for(int i=0;i<p_len;i++)
//		cout<<p_table[i]<<" ";
// Matching..
	int st = 0;
	i = 0;  
	while(st <= s_len-p_len)
	{
		while(i<p_len)
		{
			if(pat[i] == str[st+i])
			{
				
				if(i == p_len-1)
				{
					cout<<"Pattern match at index: "<<st<<endl;					
					st = st + i - p_table[i];
					i = (p_table[i] > -1) ? p_table[i] : 0; 
				break;		
				}
				i++;	
			}
			else
			{
				st = st + i - p_table[i];
				i = (p_table[i] > -1) ? p_table[i] : 0; 
				break;
			}
		}	
	}	

return 0;
}
