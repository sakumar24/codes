#include<iostream>
#include<stdlib.h>
using namespace std;

void merge(int* a,int low,int mid,int high)
{
	int n = high-low + 1;
	int* b = (int*)malloc(sizeof(int)*n);
	
	int it_1 = low;
	int it_2 = mid+1;
	int it_b = 0;
	
	while(it_1 <= mid && it_2 <= high)
	{
		if(a[it_1] < a[it_2])
			b[it_b++] = a[it_1++]; 
		else
			b[it_b++] = a[it_2++];
	}
	if(it_b < n)
	{
		while(it_1 <= mid)
			b[it_b++] = a[it_1++];
		while(it_2 <= high)
			b[it_b++] = a[it_2++];
	}
	it_b = 0;
	for(int i=low;i<=high;i++)
		a[i] = b[it_b++];
}

void mergeSort(int* a,int low,int high)
{
	if(low < high)
	{
		int mid = low + (high-low)/2;
		mergeSort(a,low,mid);
		mergeSort(a,mid+1,high);
		
		merge(a,low,mid,high);	
	}
}

int partition(int* a,int low,int high)
{
	int pivotValue = a[low];
	
	int l = low;
	int h = high;
	
	while(l < h)
	{
		while(a[l] <= pivotValue)
			l++;
		while(a[h] > pivotValue)
			h--;
		if(l < h)
		{
			int temp = a[l];
			a[l] = a[h];
			a[h] = temp;
		}
	}
	a[low] = a[h];
	a[h] = pivotValue;
	
	return h;
}

void quickSort(int* a,int low,int high)
{
	if(low < high)
	{
		int p = partition(a,low,high);
		quickSort(a,low,p);
		quickSort(a,p+1,high);
	}
}

int main()
{
	int n;
	cout<<"Enter number of elements:";
	cin>>n;
	int* a = (int*)malloc(sizeof(int)*n);
	cout<<"Enter array:";
	for(int i=0;i<n;i++)
		cin>>a[i];
			
	//mergeSort(a,0,n-1);
	quickSort(a,0,n-1);
	
	cout<<"Sorted array:";
	for(int i=0;i<n;i++)
		cout<<a[i]<<" ";
	cout<<endl;
return 0;
}