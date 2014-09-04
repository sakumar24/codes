#include<iostream>
#include<stack>

using namespace std;
#define L 3000

int main()
{
	stack<int> s;
	stack<int> MinStack;

	int t = 1;
	int m = L;

	while(t)
	{
		int in;
		cout<<"\t1-push\n\t2-pop\n\t3-top\n\t4-Minimum\n\t5-exit."<<endl;
		cin>>in;

		switch(in)
		{
			int x;
			case 1:
				cout<<"Enter element:";
				cin>>x;
				s.push(x);
				if(x <= m)
				{
					m = x;
					MinStack.push(x);
				}	
			break;
			case 2:
				x = s.top();
				s.pop();
				cout<<"Poped elemnet:"<<x<<endl;
				if(x == MinStack.top())
					MinStack.pop();	
			break;
			case 3:
				if(!s.empty())
					cout<<"Top element:"<<s.top()<<endl;
				else
					cout<<"Stack empty"<<endl;
			break;
			case 4:
				if(!s.empty())
					cout<<"Minimum element:"<<MinStack.top()<<endl;
				else
					cout<<"Stack Empty."<<endl;			
			break;
			case 5:
				t = 0;
			break;
		}
	}
return 0;
}
