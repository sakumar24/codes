#include<iostream>
#include<stack>

using namespace std;

int main()
{
	stack<char> s;
	string exp;
	
	cout<<"Enter expression:";
	cin>>exp;
	
	char c = exp[0];
	int i = 0;
	int valid = 1;
	while(c)
	{
		if(c == '[' || c == '{' || c == '(')
			s.push(c);
		else if(c == ']' || c == '}' || c == ')')
		{
		if(s.empty())
			valid = 0;
		else	
		{			
			char x = s.top();
			s.pop();
			if(c == ']' && x != '[')
					valid = 0;			
			else if(c == '}' && x != '{')
					valid = 0; 
			else if(c == ')' && x != '(')
					valid = 0;
		}}
		if(valid == 0)
			break;
		c = exp[++i];
	}
	if(valid == 1 && s.empty())
		cout<<"Expression is valid."<<endl;
	else
		cout<<"Expression is not valid"<<endl;
return 0;
}
