#include<iostream>
#include<queue>
#include<stack>
#include<stdlib.h>

using namespace std;

struct tree 
{
	int data;
	struct tree* left;
	struct tree* right;
};

struct tree* create()
{
	struct tree* root = NULL;
	int in;
	
	queue<struct tree*> Q;
	
	cout<<"Enter elements folloed by -1 for tree"<<endl;
	cin>>in;
	
	while(in != -1)
	{
		struct tree* temp = (struct tree*) malloc(sizeof(struct tree));
		temp->data = in;
		temp->left = NULL;
		temp->right = NULL;
		
		if(root == NULL)
		{
			root = temp;
		    Q.push(root);
		}
		else
		{
			if(Q.front()->left == NULL)
				Q.front()->left = temp;
			else if(Q.front()->right == NULL)
				Q.front()->right = temp;
			else
			{
				Q.pop();
				Q.front()->left = temp;
			}
			Q.push(temp);	
		}
		cin>>in;
	}
	return root;
}

void preOrder(struct tree* root)
{
	if(root == NULL)
		return;
	else
	{
		cout<<root->data<<" ";
		preOrder(root->left);
		preOrder(root->right);
	}
}

void postOrder(struct tree* root)
{
	if(root == NULL)
		cout<<"Tree empty.."<<endl;
	else
	{
		stack<struct tree*> s;
		while(1)
		{
			if(root != NULL)
			{
				s.push(root);
				root = root->left;
			}
			else
			{
				if(s.empty())
					break;
				else
				{
					if(s.top()->right == NULL)
					{
						root = s.top();
						cout<<root->data<<" ";
						s.pop();
					}
					if(root == s.top()->right)
					{
						cout<<s.top()->data<<" ";
						s.pop();
					}
				}
				if(s.empty())
					root = NULL;
				else
					root = s.top()->right;
			}
		}
	}
}

void insert(struct tree* root,int e)
{
	if(root==NULL)
		cout<<"Tree empty."<<endl;
	else
	{
		struct tree* temp = (struct tree*) malloc(sizeof(struct tree));
		temp->data = e;
		temp->left = NULL;
		temp->right = NULL;
 
		queue<struct tree*> Q;
		Q.push(root);
		while(!Q.empty())
		{
			if(Q.front()->left == NULL)
			{
				Q.front()->left = temp;
				break;
			}
			else
				Q.push(Q.front()->left);
			
			if(Q.front()->right == NULL)
			{
				Q.front()->right = temp;
				break;
			}
			else
				Q.push(Q.front()->right);

			Q.pop();
		}
	}
}

void deleteT(struct tree* root)		// Non-recursive delete
{
	if(root)
	{
		stack<struct tree*> s;
		while(1)
		{
			while(root)
			{
				s.push(root);	
				root = root->left;
			}
			if(s.empty())
				break;
			else
			{
				root = s.top();
				if(root->right == NULL)	// Delete the root..
				{
					struct tree* temp = root;
					free(temp);
					s.pop();
					temp = NULL;
					if(s.top()->right == root) // Delete it too..
					{
						temp = s.top();
						free(temp);
						s.pop();
						temp = NULL;
					}
				}	
			}
			if(s.empty())
				root = NULL;
			else
				root = s.top()->right;	
		}
	}
}

int level(struct tree* root)
{
	if(!root)
		return 0;
	else
	{
		int lh = level(root->left);
		int rh = level(root->right);

		return (max(lh,rh)+1);		
	}
}

int diameter(struct tree* root)
{
	if(!root)
		return 0;
	if(root->left == NULL && root->right == NULL)
		return 0;
	else
	{
		int lh = level(root->left);
		int rh = level(root->right);

		int ld = diameter(root->left);
		int rd = diameter(root->right);

		int d = max((lh+rh),max(rd,ld));
		return d;
	}
}

int main()
{
	struct tree* root = create();
	
	int t = 1;
	while(t)
	{
		int in;
		cout<<"\n\t1-preOrder\n\t2-postOrder\n\t3-insert\n\t4-Delete tree\n\t5-Diameter\n\t6-level\n\t7-Exit"<<endl;
		cin>>in;
		switch(in)
		{	
		case 1:	
			if(root == NULL)
				cout<<"Tree empty.."<<endl;
			else	
				preOrder(root);
			cout<<endl;
		break;
		case 2:
			postOrder(root);
			cout<<endl;
		break;
		case 3:
			int e;
			cout<<"Enter element to enter:";
			cin>>e;
			insert(root,e);
			cout<<"DONE"<<endl;
		break;
		case 4:
			deleteT(root);
			cout<<"DONE"<<endl;
		break;
		case 5:
			cout<<"Diameter:"<<diameter(root)<<endl;
		break;
		case 6:
			cout<<"Level:"<<level(root)<<endl;
		break;
		case 7:
			t = 0;
		break;
		}
	}	
return 0;
}
