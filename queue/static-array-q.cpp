#include<iostream>
#include<stdlib.h>

using namespace std;

#define INVAL -3000

struct queue
{
	int rear,front;
	int* array;
	int size;
};

struct queue* create(int s)
{
	struct queue* q = (struct queue*) malloc(sizeof(struct queue));
	if(!q)
		return NULL;
	
	q->rear = q->front = -1;
	q->size = s;
	q->array = (int *) malloc(sizeof(int)*s);

	if(!q->array)
		return NULL;
	else
		return q; 
} 

int isEmpty(struct queue* q)
{
	return (q->front == -1);
}

int isFull(struct queue* q)
{
	return ((q->rear+1)%q->size == q->front);
}

void EnQueue(struct queue* q, int data)
{
	if(isFull(q))
		cout<<"Queue Full."<<endl;
	else
	{
		q->rear = (q->rear+1)%q->size;
		q->array[q->rear] = data;
		
		if(q->front == -1)
			q->front = q->rear;
	}
}

int DeQueue(struct queue* q)
{
	if(isEmpty(q))
		return INVAL;
	else
	{
		int data = q->array[q->front];

		if(q->front == q->rear)
			q->front = q->rear = -1;
		else
			q->front = (q->front+1)%q->size;
	return data;	
	}
}
int main()
{
	int s;
	cout<<"Enter capacity of queue:";
	cin>>s;
	struct queue* q = create(s);
	
	int t=1;
	while(t)
	{
		int in;
		cout<<"\n\t1-EnQ\n\t2-DeQ\n\t3-Rear Element\n\t4-Front Element\n\t5-Exit."<<endl;
		cin>>in;
		switch(in)
		{
		case 1:
			int x;
			cout<<"Enter element to EnQ:";
			cin>>x;
			EnQueue(q,x);
		break;
		case 2:
			x = DeQueue(q);	
			cout<<"Removed element:"<<x<<endl;	
		break;
		case 3:
			cout<<"Element at rear:"<<q->array[q->rear]<<endl;
		break;
		case 4:
			cout<<"Element at front:"<<q->array[q->front]<<endl;
		break;
		case 5:
			t = 0;
		break;
		}
	}
return 0;
}
