#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include<stdlib.h>
#define SERVER_PORT 5432
#define MAX_PENDING 5
#define MAX_LINE 256
int main()
{
	struct sockaddr_in sin;
	char buf[MAX_LINE];
	int len;
	int s;
	
	/* build address data structure */
	bzero((char *)&sin, sizeof(sin));
	sin.sin_family = AF_INET;
	sin.sin_addr.s_addr = INADDR_ANY;
	sin.sin_port = htons(SERVER_PORT);
	
	/* setup passive open */
	if ((s = socket(PF_INET, SOCK_DGRAM, 0)) < 0) //  SOCK_STREAM to SOCK_DGRAM
	{
		perror("simplex-talk: socket");
		exit(1);
	}
	if ((bind(s, (struct sockaddr *)&sin, sizeof(sin))) < 0)
	{
		perror("simplex-talk: bind");
		exit(1);
	}
	listen(s, MAX_PENDING);
	
	/* we don't have to establish connection in udp.
	while(1)
	{
		
		if ((new_s = accept(s, (struct sockaddr *)&sin, &len)) < 0)
		{
			perror("simplex-talk: accept");
			exit(1);
		}
		*/
	/* receive and print text */	
	while (len = recv(s, buf, sizeof(buf), 0))
		fputs(buf, stdout);
	
	//}
}
