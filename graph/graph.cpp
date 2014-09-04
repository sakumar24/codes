#include<iostream>
#include<stdlib.h>
#include<stack>
#include<queue>
#include<limits.h>

using namespace std;

struct Graph
{
	int v;
	int e;
	int **adj;
};

struct Graph* create()
{
	char undir;
	cout<<"Press 'y' for Undirected graph.";
	cin>>undir;
	struct Graph* g = (struct Graph*)malloc(sizeof(struct Graph));
	cout<<"\nEnter number of vertices:";
	cin>>g->v;
	cout<<"\nEnter number of edges:";
	cin>>g->e;

	g->adj = (int**)malloc(g->v*sizeof(int));		//
	for(int i=0;i<g->v;i++)					//	Dynamic 2-D matrix memory allocation		
		g->adj[i] = (int*)calloc(g->v,sizeof(int)); 	//
	
	for(int i=0;i<g->e;i++)
	{	
		int v1,v2,len;
		cout<<"\nEnter edge(vertex1 vertex2 length):";		
		cin>>v1>>v2>>len;
		if(0 > v1 || v1 >= g->v || 0 > v2 || v2 >= g->v )
		{
			cout<<"Invalid edge, vertex not in graph( 0 <= vertex < g->v)."<<endl;
			i--;
		}
		else
		{
			g->adj[v1][v2] = len;		
			if(undir == 'y')
				g->adj[v2][v1] = len;
		}	
		for(int j=0;j<g->v;j++)
		{
			for(int k=0;k<g->v;k++)
				cout<<g->adj[j][k]<<" ";
			cout<<endl;
		}	
	}
	return g;
}

void BFS(struct Graph* g,int u,int* visited)
{
	queue<int> Q;
	Q.push(u);

	while(!Q.empty())
	{
		int cur = Q.front();
		visited[cur] = 1;cout<<"Visited:"<<cur<<endl;
		for(int i=0;i<g->v;i++)
		{
			if(g->adj[u][i] > 0 && visited[i] == 0)
				Q.push(i);
		}
		Q.pop();
	}
}

void DFS(struct Graph* g,int u,int* visited)
{
	stack<int>s;
	s.push(u);
	while(!s.empty())
	{
		int cur = s.top();
		visited[cur] = 1;cout<<"Visited:"<<cur<<endl;
		int hasAdj = 0;
		for(int i=0;i<g->v;i++)
		{
			if(g->adj[u][i] > 0 && visited[i] == 0)
			{
				s.push(i);
				hasAdj = 1;
				break;
			}
		}	
		if(hasAdj == 0)
			s.pop();
	}
}

void allPairShortestPath(struct Graph* g,int u,int* path)
{
	int visited[g->v];
	for(int i=0;i<g->v;i++)
	{
		path[i] = INT_MAX;
		visited[i] = 0;
	}	
	path[u] = 0;

	queue<int> Q;
	Q.push(u);

	while(!Q.empty())
	{
		int cur = Q.front();
		visited[cur] = 1;
		for(int i=0;i<g->v;i++)
		{
			if(g->adj[cur][i] > 0)
			{	
				if(path[i] > path[cur] + g->adj[cur][i])
					path[i] = path[cur] + g->adj[cur][i];
				if(visited[i] != 1)
					Q.push(i);
			}
		}
		
		Q.pop();
	}
}

int main()
{
	struct Graph* g = create();
	int t=1;
	while(t)
	{
		cout<<"\n\t 0 -> Exit \n\t 1 -> Create Graph \n\t 2 -> BFS \n\t 3 -> DFS \n\t 4 -> All pair shortest path."<<endl;
		cout<<"\n\t\t Input:";
		cin>>t;

		switch(t)
		{
			case 1:
				g = create();
			break;
			case 2:
			{
				int* visited = (int*)calloc(g->v,sizeof(int));
						
				for(int i=0;i<g->v;i++)
					if(visited[i] == 0)	
						BFS(g,i,visited);
			}
			break;
			case 3:
			{
				int* visited = (int*)calloc(g->v,sizeof(int));
						
				for(int i=0;i<g->v;i++)
					if(visited[i] == 0)	
						DFS(g,i,visited);
			}			
			break;
			case 4:
			{
				int path[g->v];
				int u;

				cout<<"Enter start node:";
				cin>>u;
				if(0 <= u < g->v)
				{
					allPairShortestPath(g,u,path);
					cout<<"Shortest path array:";
					for(int i=0;i<g->v;i++)
						cout<<path[i]<<" ";
					cout<<endl;	
				}
				else
					cout<<"Start node not in graph"<<endl;
			}
			break;		
		}		
	}
return 0;
}
