#include<iostream>
#include<stdlib.h>

using namespace std;

#define inval -3000		// invalid value (to return invalid status)

struct stack
{
	int size;
	int top;
	int* array;
};

struct stack* create(int n)			// n = intial capacity of stack.
{
	struct stack* s = (struct stack*)malloc(sizeof(struct stack));
	s->top = -1;
	s->size = n;
	int* a = (int*)malloc(sizeof(int)* n);
	s->array = a;
	if(s->array)
		return s;
	else
	{
		cout<<"Can't intialize stack with size: "<<n<<endl;
		return NULL;
	}	
}

int isEmpty(struct stack* s)
{
	if(s == NULL)
	{
		cout<<"Invalid stack address."<<endl;
		exit(0);
	}		
	else
		return( s->top == -1);
}

int isFull(struct stack* s)
{
	if(s == NULL)
	{
		cout<<"Invalid stack address."<<endl;
		exit(0);
	}		
	else
		return (s->top == s->size-1);	
}

void push(struct stack* s,int data)
{
	if(isFull(s))
	{
		s->size*= 2;
		s->array = (int*)realloc(s->array,s->size);	
	}
	s->array[++s->top] = data;
}

int pop(struct stack* s)
{
	if(isEmpty(s))
	{
		cout<<"Stack empty."<<endl;
		return inval;
	}
	return (s->array[s->top--]);
}

int top(struct stack* s)
{	
	if(isEmpty(s))
	{
		cout<<"Stack empty."<<endl;
		return inval;
	}
	return (s->array[s->top]);
}

int main()
{
	struct stack* s = create(2);
	int t =	1;
	int in;
	while(t)
	{
		cout<<"\t1-push\n\t2-pop\n\t3-top\n\t4-exit"<<endl;
		cin>>in;
		switch(in)
		{	
			case 1:
				int x;
				cout<<"Input data to push:";
				cin>>x;
				push(s,x);
			break;
			case 2:
				x = pop(s);
				if(x != inval)
					cout<<"poped data:"<<x<<endl;
			break;
			case 3:
				x = top(s);
				if(x != inval)
					cout<<"Element at top:"<<x<<endl; 
			break;
			case 4:
				t = 0;
			break;
			default:
				cout<<"Invalid input."<<endl;
		}
	}
return 0;
}
