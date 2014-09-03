#include<iostream>
#include<math.h>
#include<stdlib.h>

using namespace std;

int hasDuplicate(int* a,int n)
{
	cout<<"Array:";
	for(int i=0;i<n;i++)
		cout<<a[i]<<" ";
	cout<<endl;
	for(int i=0;i<n;i++)
	{
		if(a[abs(a[i])] < 0)
			return 1;		
		else
			a[abs(a[i])] = -a[abs(a[i])] ;	
	}
	return 0;
}

int main()
{
	int n = 5;
	int a[] = {1,2,3,4,3};

	if(hasDuplicate(a,n))
		cout<<"Array have duplicate"<<endl;
	else
		cout<<"Array does't have duplicate"<<endl;
return 0;
}
