#include<iostream>
#include<stdlib.h>
#define inval -3000

using namespace std;

struct node
{
	int data;
	struct node* next;
};

struct node* createStack()
{
	struct node* top = NULL;
	return top;
}

void push(struct node** s,int data)
{
	struct node* temp = (struct node*)malloc(sizeof(struct node));
	if(temp == NULL)
	{
		cout<<"Can't allocate space for new node."<<endl;
		return;
	}
	temp->data = data;
	temp->next = *s;
	*s = temp;
}

int pop(struct node** s)
{
	if(*s == NULL)
	{
		cout<<"Empty stack"<<endl;
		return inval;
	}
	struct node* temp = *s;
	int x = temp->data;
	*s = temp->next;
	return x;
}
int top(struct node* s)
{
	if(s == NULL)
	{
		cout<<"Empty stack"<<endl;
		return inval;
	}
	return s->data;	
}
int main()
{
	struct node* s = createStack();
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
				push(&s,x);
			break;
			case 2:
				x = pop(&s);
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

