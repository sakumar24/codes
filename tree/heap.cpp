#include<iostream>
#include<stdlib.h>

using namespace std;

// Implementation of maxHeap only.
struct heap
{
	int count;
	int capacity;
	int* array;
	//int heat_type; // only maxHeap.
};

struct heap* newHeap(int size)
{
	struct heap* root = (struct heap*)malloc(sizeof(struct heap));
	if(root == NULL)
	{
		cout<<"Can't allocate memory."<<endl;
		return NULL;
	}
	root->capacity = size*2;	// keep capacity twise the intial size of array.
	root->count = 0;
	root->array = (int*)malloc(sizeof(int)*size*2);
	if(root->array == NULL)
	{
		cout<<"Can't allocate memory."<<endl;
		return NULL;
	}
	return root;
}

void heapyfy(struct heap* h,int i)
{
	if(i >= h->count)
	{
		cout<<"Heapyfy::Index greater than the count.";
		return;
	}
	int maxVal = h->array[i];
	int max = i;
	int lChild = 2*i+1;
	int rChild = 2*i+2;
	if(lChild < h->count && maxVal < h->array[lChild])
	{
		maxVal = h->array[lChild];
		max = lChild;
	}
	if(rChild < h->count && maxVal < h->array[rChild])
	{
		maxVal = h->array[rChild];
		max = rChild;
	}
	if(max != i)
	{
		int temp = h->array[max];
		h->array[max] = h->array[i];
		h->array[i] = temp;
		
		heapyfy(h,max);
	}
}

void insert(struct heap* h,int data)
{
	if(h->count == h->capacity)
	{
		cout<<"Heap full"<<endl;
		return;
	}
	if(h->count == 0)
	{
		h->array[0] = data;
		h->count++;
	}
	else
	{
		int curIndex = h->count++;	
		int parent = (curIndex - 1)/2;
		while(curIndex > 0 && h->array[parent] < data)
		{
			h->array[curIndex] = h->array[parent];
			curIndex = parent;
			parent = (curIndex-1)/2;
		}
		h->array[curIndex] = data;
	}
}

struct heap* buildHeap(int a[],int size)	// convert array to heap.. O(n), but if we insert one by one, it is O(nlogn).
{ 
	struct heap* h = newHeap(size);
	for(int i=0;i<size;i++)
		h->array[i] = a[i];
	h->count = size;
	for(int i=(size-1)/2;i>=0;i--)
		heapyfy(h,i);
		
	return h;
}

void deleteMax(struct heap* h)
{
	if(h->count == 0)
		cout<<"Heap empty."<<endl;
	else
	{
		h->array[0] = h->array[h->count-1];
		h->count--;
		if(h->count > 0)
			heapyfy(h,0);
	}
}

void heapSort(int a[],int n)
{
	int b[n];
	struct heap* h = buildHeap(a,n);
	
	for(int i=0;i<n;i++)
	{
		b[i] = h->array[0];
		deleteMax(h);
	}
	for(int i=0;i<n;i++)
		a[i] = b[i];
}

void getMedian(struct heap **maxHeap,struct heap **minHeap,int in,int* m)
{
	if(*maxHeap == NULL)	// first element
	{
			*maxHeap = newHeap(in);
			*m = in;
	}
}

int main()
{
	int in[5];
	cout<<"Enter 5 elements to build heap:";
	for(int i=0;i<5;i++)
		cin>>in[i];
	
	struct heap* h = buildHeap(in,5);
	
	int t=1;
	while(t)
	{
		cout<<"\n\t 0 -> Exit \n\t 1 -> Insert \n\t 2 -> deleteMax \n\t 3 -> getMax \n\t 4 -> HeapSort \n\t 5 -> Dynamic median"<<endl;
		cin>>t;
		switch(t)
		{
			case 1:
			{
				int data;
				cout<<"Enter data to insert:";
				cin>>data;
				insert(h,data);
			}
			break;			
			case 2:
			{
				cout<<"Current Max:"<<h->array[0]<<endl;
				deleteMax(h);
				cout<<"New Max:"<<h->array[0]<<endl;
			}
			break;			
			case 3:
				cout<<"Max="<<h->array[0]<<endl;
			break;			
			case 4:
			{
				int n,i;
				cout<<"Enter size of array:";
				cin>>n;
				int a[n];
				cout<<"Enter array elemets:";
				for(i=0;i<n;i++)
					cin>>a[i];
				heapSort(a,n);
				cout<<"Sorted array:";
				for(i=0;i<n;i++)
					cout<<a[i]<<" ";
				cout<<endl;
			}
			break;
			case 5:
			{
				int curMedian;
				int in;
				struct heap* maxHeap = NULL;
				struct heap* minHeap = NULL;
				cout<<"Enter numbers(-500 to terminate):";
				while(1)
				{
					cin>>in;
					if(n == -500)
						break;
						
					getMedian(&maxHeap,&minHeap,in,&curMedian);
					cout<<"Median:"<<curMedian<<endl;		
				}
			}
		}
	}
return 0;
}