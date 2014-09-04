#include<iostream>
#include<stack>
#include<stdlib.h>

using namespace std;

struct node
{
	char data;
	struct node* left;
	struct node* right;
};

void postOrder(struct node* root)
{
	if(root)
	{
		postOrder(root->left);
		postOrder(root->right);
		cout<<root->data<<" ";
	}
}

struct node* new_node(char c)
{
	struct node* n = (struct node*) malloc(sizeof (struct node));
	if(!n)
	{
		cout<<"Can't create new node."<<endl;
		exit(1);
	}
	n->data = c;
	n->right = NULL;
	n->left = NULL;
	
	return n;
}

struct node* exprTree(char postFix[],int size)
{
	stack<char> s;
	struct node* pre_root = NULL;
	
	for(int i=0;i<size;i++)
	{
		char c = postFix[i];
		if(c == '+' || c == '-' || c == '*' || c == '/')
		{
			if(s.empty())
			{
				cout<<"Invalid expression."<<endl;
				exit(0);
			}
			struct node* root = new_node(c);
			root->right = new_node(s.top());
			s.pop();
			
			if(pre_root == NULL)
			{
				if(s.empty())
				{	
					cout<<"Invalid expression."<<endl;
					exit(0);
				}
				pre_root = new_node(s.top());
				s.pop();
			}
			root->left = pre_root;
			pre_root = root;
		}
		else
			s.push(c);
	}
	return pre_root;
}

int main()
{
	int size = 4;
	char postFix[] = {'a','+'};
	struct node* root = exprTree(postFix,size);
	
	cout<<"Post Order traversal of tree:"<<endl;
	postOrder(root);
	cout<<endl;
	
return 0;
}