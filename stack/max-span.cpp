#include<iostream>

using namespace std;

int main()
{
	int n;
	cout<<"Enter number of elements:";
	cin>>n;

	int s[n];
	cout<<"Enter elements:";
	for(int i=0;i<n;i++)
		cin>>s[i];
	
	int curMax=0,tempSpan=0;
	int Span=0;
	for(int i=n-1;i>=0;i--)
	{
		if(curMax <= s[i])
		{
			// store and reset.
			if(tempSpan > Span)
				Span = tempSpan;
			tempSpan = 1;
			curMax = s[i];
		}
		else
			tempSpan++;
	}
	if(tempSpan > Span)
		Span = tempSpan;

	cout<<"Maximum span="<<Span<<endl;
return 0;
}
