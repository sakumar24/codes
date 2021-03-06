#include<iostream>
#include<algorithm>
#include<string>

using namespace std;

int fact(int x)
{
	if(x <= 1)
		return 1;
	else
		return (x*fact(x-1));
}

int main()
{
	string in;
	cout<<"str: "; getline(cin,in);

	int len = in.length();
	char str[len+1];
	str[0] = 'A';

	for(int i=1;i<=len;i++)  // get the smallest string in array. Insertion sort.
	{
		str[i] = in[i-1];
		int j=i;
		while(str[j] < str[j-1])
		{
			char temp = str[j];
			str[j] = str[j-1];
			str[j-1] = temp;
		j--;
		}
	}

	int p = fact(len);

	for(int i=0;i<len;i++)
		str[i] = str[i+1];
	str[len]='\0';

	cout<<str<<endl;
	for(int i=1;i<p;i++) 		// print one string in each iteration.
	{
	// find 1st(->Right most char such that str[first+1] > str[first]) and 2nd..
		int first,sec=-1;
		char min='z';
		for(int j=len-1;j>0;j--)
		{
			if(str[j] > str[j-1])
			{
				first = j-1;
				if(str[j] < min)
				{
					min = str[j];
					sec = j;
				}
				break;			
			}
		}
		//2nd(->smallest to the right of 1st and greater than 1st)
		for(int j=first+1;j<len;j++)
		{
			if(str[j] > str[first])
			{
				if(str[j] < min)
				{
					sec = j;
					min = str[j];
				}
			}
		}
		// swap 1st and 2nd  
	
	//	cout<<"1st:"<<first<<" 2nd:"<<sec<<endl;
	//	cout<<"1st:"<<str[first]<<" 2nd:"<<str[sec]<<endl;
		
		char temp = str[first];
		str[first] = str[sec];
		str[sec] = temp;
/*	//	sort the string after first.
		sort(&str[first+1],&str[len]);
*/
	// OR reverse the sub array after first.(this one is fater.)	

		int st=first+1;
		int end=len-1;
		while(st < end)
		{
			temp = str[st];
			str[st] = str[end];
			str[end] = temp;
			st++;
			end--;
		}

	cout<<str<<endl;
	}
return 0;
}
