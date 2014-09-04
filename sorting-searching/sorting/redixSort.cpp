#include<iostream>
#include<stdlib.h>
#include<queue>

using namespace std;

void redixSort(int* a,int n)
{
	queue<int> Q[10];
	int div = 1;
	
	for(int i=0;i<3;i++)		// for each digit sort the array.
	{
		for(int j=0;j<n;j++)	// put each element into respective queue(bucket) based on current digit.
		{	
			int d;
			int done = 0;
			int cur = a[j];
			while(done < div)
			{
				d = cur%10;
				cur=cur/10;
				done++;
			}
			Q[d].push(a[j]); 
		}
		int k=0;
		for(int j=0;j<10;j++)	// put back the elements to array from the queues.
		{
			while(!Q[j].empty())
			{	
				a[k++] = Q[j].front(); 
				Q[j].pop();
			}
		}
		div++;
	}
}

int main()
{
	int n;	
	cout<<"Number of elements:";
	cin>>n;
	int * a = (int*)malloc(sizeof(int)*n);
	cout<<"Enter elements(3 digit numbers):";
	for(int i=0;i<n;i++)	
		cin>>a[i];

	redixSort(a,n);

	cout<<"Sorted array:";
	for(int i=0;i<n;i++)	
		cout<<a[i]<<" ";
	cout<<endl;
return 0;
}
