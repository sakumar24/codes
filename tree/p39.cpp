#include<iostream>

using namespace std;

int getNodeHeight(int* P,int* heights,int i)
{
	int parentIndex = P[i];
	
	if(P[i] == -1)
		heights[i] = 0;
	else if(heights[i] == -1)
		heights[i] = getNodeHeight(P,heights,parentIndex)+ 1;
		
	return heights[i];
}

int main()
{
	int n;
	cout<<"Enter number of nodes:";
	cin>>n;
	
	int P[n],heights[n];
	cout<<"Enter parents array values:"
	for(int i=0;i<n;i++)
	{
		cin>>P[i];
		heights[i] = -1;
	}
	int maxHeight = 0;
	int curHeight = 0;
	for(int i=0;i<n;i++)
	{
		curHeight = getNodeHeight(P,heights,i);
		if(curHeight > maxHeight)
			maxHeight = curHeight;
	}
	cout<<"Height of Tree:"<<maxHeight<<endl;
return 0;
}