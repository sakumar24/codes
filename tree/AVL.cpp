#include<iostream>
#include<algorithm>

using namespace std;

struct AVLTreeNode
{
	int data;
	struct AVLTreeNode* left;
	struct AVLTreeNode* right;
	int height;
};

struct AVLTreeNode* newNode(int data)
{
	struct AVLTreeNode* root = (struct AVLTreeNode*)malloc(sizeof(struct AVLTreeNode));
		if(root)
		{
			root->data = data;
			root->left = root->right = NULL;
			root->height = 0;
			
			return root;
		}
		else
			cout<<"Can't allocate memory"<<endl;
}
int Height(struct AVLTreeNode* root)
{	
	if(root == NULL)
		return 0;
	return max(Height(root->left),Height(root->right))+1;
}

struct AVLTreeNode* rightRotate(struct AVLTreeNode* X)
{
	struct AVLTreeNode* curRight = X->right;
	X->right = curRight->left;
	curRight->left = X;
	
	X->height = max(Height(X->right),Height(X->left)) + 1;
	curRight->height = max(Height(curRight->right),Height(curRight->left)) + 1;
	
	return curRight;
}

struct AVLTreeNode* leftRotate(struct AVLTreeNode* X)
{
	struct AVLTreeNode* curLeft = X->left;
	X->left = curLeft->right;
	curLeft->right = X;
	
	X->height = max(Height(X->left),Height(X->right))+1;
	curLeft->height = max(Height(curLeft->left),Height(curLeft->right))+1;
	
	return curLeft;
}

struct AVLTreeNode* RLRotate(struct AVLTreeNode* X)
{
	X->right = leftRotate(X->right);
	return rightRotate(X);
}

struct AVLTreeNode* LRRotate(struct AVLTreeNode* X)
{
	X->left = rightRotate(X->left);
	return leftRotate(X);
}

struct AVLTreeNode*  insert(struct AVLTreeNode* root,int data)
{
	if(root == NULL)
	{
		root = newNode(data);
	}
	else
	{
		if(root->data > data)
		{
			root->left = insert(root->left,data);
				if((Height(root->left) - Height(root->right)) > 1)
				{
					if(data > root->left->data)
						root = LRRotate(root);
					else
						root = leftRotate(root);
				}
		}
		else if(root->data < data)
		{
			root->right = insert(root->right,data);
				if((Height(root->right) - Height(root->left)) > 1)
				{
					if(data < root->right->data)
						root = RLRotate(root);
					else
						root = rightRotate(root);
				}
		}		
		root->height = max(Height(root->left),Height(root->right)) +1;
	}
	return root;
}
void printInorder(struct AVLTreeNode* root)
{
	if(root == NULL)
		return ;
	printInorder(root->left);
	cout<<"Data:"<<root->data<<" Height:"<<root->height<<endl;
	printInorder(root->right);
}
int main()
{

	int t=1;
	int in;

 	cout<<"Enter node elements followed by -1 to create AVL-Tree:";
	cin>>in;
	struct AVLTreeNode* root = NULL;
	while(in != -1)
	{
		root = insert(root,in);
		cin>>in;
	}
	cout<<"Inorder traversal:"<<endl;
	printInorder(root);
return 0;
}