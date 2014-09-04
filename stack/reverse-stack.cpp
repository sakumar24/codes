#include<iostream>
#include<stack>

using namespace std;

void insertAtBottom(stack<int> *s,int x)
{
	if(s->empty())
	{
		s->push(x);
		return;
	}
	int d = s->top();
	s->pop();
	insertAtBottom(s,x);
	s->push(d);
}

void reverse(stack<int> *s)
{
	if(s->empty())
		return;
	int x = s->top();
	s->pop();
	reverse(s);
	insertAtBottom(s,x);
}

int main()
{
	stack<int> s;
	int t = 1;

	while(t)
	{
		int in;
		cout<<"\t1-push\n\t2-pop\n\t3-top\n\t4-reverse\n\t5-exit."<<endl;
		cin>>in;

		switch(in)
		{
			int x;
			case 1:
				cout<<"Enter element:";
				cin>>x;
				s.push(x);
			break;
			case 2:
				x = s.top();
				s.pop();
				cout<<"Poped elemnet:"<<x<<endl;
			break;
			case 3:
				if(!s.empty())
					cout<<"Top element:"<<s.top()<<endl;
				else
					cout<<"Stack empty"<<endl;
			break;
			case 4:
				if(!s.empty())
				{
					reverse(&s);
					cout<<"Stack reversed."<<endl;
				}
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
