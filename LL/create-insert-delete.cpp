#include<stdlib.h>
#include<iostream>

using namespace std;

struct node
{
	int data;
	struct node* next;
};

struct node* createList()
{
	struct node* head = NULL;
	struct node* cur = NULL;
	int x;
	cout<<"Enter integers followed by -1"<<endl;
	cin>>x;
	
	while(x != -1)
	{
		struct node* temp = (struct node*)malloc(sizeof(struct node));
		temp->data = x;
		temp->next = NULL;
	// get head..
		if(head == NULL)
			head = temp;
	// fix pointers if list is not empty
		if(cur != NULL)
			cur->next = temp;
	
		cur = temp;	
		cin>>x;
	}

	return head;
}

void accessList(struct node* head)
{
	struct node* cur = head;
	cout<<"List:"<<endl;
	while(cur)
	{
		cout<<cur->data<<" ";
		cur = cur->next;
	}	
	cout<<endl;
}

void reverse(struct node** head)
{
	struct node* p;
	p = *head;
	if(p == NULL || p->next == NULL)
		return;
	struct node* r;
	struct node* q = p->next;
	p->next = NULL;
	while(q)
	{
		r = q->next;
		q->next = p;
		p = q;
		q = r;
	}
	*head = p;

// Recursive..

}

void insert(struct node** head,int x,int pos)
{
	struct node* p = *head;
	if(p == NULL)	// Empty list
	{
		p = (struct node*)malloc(sizeof(struct node));
		p->data = x;
		p->next = NULL;
	}
	else
	{
		struct node* temp = (struct node*)malloc(sizeof(struct node));
		temp->data = x;
		temp->next = NULL;
		
		if(pos == 1)
		{
			temp->next = p;
			*head = temp;		// changing value at the real head address.
		}
		else
		{
			struct node* q = p;
			int c = 2;
			while(c < pos && q->next!= NULL)
			{
				q = q->next;
				c++;
			}
			if(q->next == NULL)
				cout<<"Inserting at the end of the list."<<endl;
			temp->next = q->next;
			q->next = temp;
		}
	}
}

void delet(struct node** head,int pos)
{
	struct node* p = *head;
	if(p == NULL)
		cout<<"Empty list."<<endl;
	else
	{
		if(pos == 1)
		{
			*head = p->next;
			p = NULL;		
			free(p);
		}
		else
		{
			struct node* q = NULL;
			int count = 1;
			while(count < pos && p->next != NULL)
			{
				count++;
				q = p;
				p = p->next;
			}
			if(p->next == NULL)
				cout<<"Deleting last node."<<endl;
			q->next = p->next;
			p = NULL;
			free(p); 
		}
	}
}
int main()
{
	struct node* head  = createList();
	accessList(head);

	int ans;
	while(1)
	{
		cout<<"\n\t1-Insert.\n\t2-Delete.\n\t3-Reverse.\n\t4-Exit."<<endl;
		cin>>ans;
		switch(ans)
		{
		case 1:
			int x,pos;
			cout<<"Enter element and position:";
			cin>>x>>pos;
		
			insert(&head,x,pos);	// call by address (head); so that it can be changed if needed.
			accessList(head);
		break;
		case 2:
			cout<<"Enter position to delete element:";
			cin>>pos;
			delet(&head,pos);
			accessList(head);
		break;
		case 3:
			reverse(&head);
			cout<<"Reverse ";
			accessList(head);
		break;
		case 4:
			exit(0);
		}
	}
return 0;
}
