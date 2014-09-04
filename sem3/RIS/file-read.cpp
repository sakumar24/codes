#include<iostream>
#include<fstream>
#include<string>

using namespace std;

int main()
{
	string line;
	char key;
	cout<<"Press any key to continue.."<<endl;
	cin>>key;
	ifstream file;
	file.open("create.txt");
	
	while(! file.eof())
	{	
		getline(file,line);
		cout<<line<<endl;
	}
	file.close();
			
	cout<<"Press any key to exit.."<<endl;
	cin>>key;
return 0;
}