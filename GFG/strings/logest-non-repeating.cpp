#include<iostream>
#include<string>

using namespace std;

int main()
{
	string str;
	cout<<"Str: " ; getline(cin,str);

	int m_len = 0, length = str.length();
	string result[length];
	int count = 0;
	
	for(int start = 0;start < length;)
	{
		int i=start,len=0;
		int alp[256] = {0};
		string temp = "";
						//	cout<<"Start: "<<start<<endl;
		while(i <= length)
		{
						//	cout<<"i: "<<i<<endl;
			int index = (int)str[i];
			
			if( alp[index] == 1 || i == length)
			{
						//	cout<<temp<<endl;
				if(len > m_len)
				{
					m_len = len;
					result[0] = temp;
					count = 1;
				}
				else if(len == m_len)
				{
					result[count++] = temp;
				}
				break;
			}
			else
			{
				temp += str[i];	//cout<<str[i];
				alp[index] = 1;
				i++;
				len++;
			}
		}
		if( i== length)
			break;
	// If we want to find only one longest(or only the length of same ), then "start + len", other wise we will have to go start++
	//				 start++;
 		start = start + len; 
	}
	cout<<"longest string: "<<endl;
	for(int j=0;j<count;j++)
		cout<<result[j]<<endl;
return 0;
}

