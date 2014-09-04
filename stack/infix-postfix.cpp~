#include<iostream>
#include<stack>

using namespace std;

int main()
{
	stack<char> s;
	int i=0;
	string exp;	
	cout<<"Expression:";
	cin>>exp;
	
	char c = exp[0];
	while(c)
	{
		if( c == '+' || c == '-' || c == '/' || c == '*' || c == '(' || c == ')')
		{
			if(!s.empty())
			{
				char x = s.top();
				if(c == '+' || c == '-')
				{
					while(!s.empty() && s.top()!= '(')
					{	
						cout<<s.top();
						s.pop();
					}
				}
				else if(c == '/' || c == '*')
				{
					while( (x == '/' || x == '*') && x != '(')
					{
						cout<<x;
						s.pop();	
						x = s.top();
					} 
				}
				else if(c == ')')
				{
					while(x != '(')
					{
						cout<<x;
						s.pop();	
						x = s.top();
					}
					s.pop();
				} 
			}
			if(c != ')')
				s.push(c); //cout<<"pushed:"<<s.top()<<endl;}
		}
		else
			cout<<c;

		c = exp[++i];
	} 
	while(!s.empty())
	{
		cout<<s.top();
		s.pop();
	}
	cout<<endl;
return 0;
}
