#include<iostream>
#include<string>

using namespace std;

void recur (string str, char* data, int last, int index)
{
    int i, len = str.length();
 
    // One by one fix all characters at the given index and recur for the
    // subsequent indexes
    for ( i=0; i<len; i++ )
    {
        // Fix the ith character at index and if this is not the last index
        // then recursively call for higher indexes
        data[index] = str[i] ;
 
        // If this is the last index then print the string stored in data[]
        if (index == last)
           cout<<data<<endl;
        else // Recur for higher indexes
            recur (str, data, last, index+1);
    }
}

int main()
{
	string str = "AB";
	int len = str.length();
	char data[len+1];
	data[len] = '\0';

	recur(str,data,len-1,0);
return 0;
}
	
	
