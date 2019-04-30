#include <iostream>
using namespace std;

class Graph
{
	private:
		int nVertices,choiceOfEdge,startVertex,endVertex;
		int **graph;
		int *visited;

	public :
		Graph(int nVertices)
		{
			this ->nVertices = nVertices;
			graph = new int*[nVertices];
			for(int i=0;i<nVertices;i++)
			{
				graph[i] = new int [nVertices];
			}

			//initialize graph matrix with 0
			for(int i=0;i<nVertices;i++)
			{
				for(int j=0;j<nVertices;j++)
				{
					*(*(graph+i)+j)=0;
				}
			}

			visited = new int[nVertices];
		}
		int** createGraph(int nVertices)
		{
			bool hasNextEdge = true;
			//input edge weights
			while(hasNextEdge)
			{
				cout << "\t1.Enter a Edge in graph" << endl;
				cout << "\t2.Exit" << endl;
				cin >> choiceOfEdge;
				switch(choiceOfEdge)
				{
					case 1:
						cout << "\tEnter first vertex" << endl;
						cin >> startVertex;
						cout << "\tEnter End vertex" << endl;
						cin >> endVertex;
						cout << "\tEnter edge Weight" << endl;
						cin >> *(*(graph+startVertex)+endVertex);
						*(*(graph+endVertex)+startVertex) = *(*(graph+startVertex)+endVertex);
						hasNextEdge = true;
						break;

					case 2:
						hasNextEdge = false;
				}
			}

			//assign 999 to not present edges
			for(int i=0;i<nVertices;i++)
			{
				for(int j=0;j<nVertices;j++)
				{
					if(*(*(graph+i)+j) == 0)
						*(*(graph+i)+j) = 999;
				}
			}
			return graph;
		}


		void display()
		{
			for(int i=0;i<nVertices;i++)
			{
				for(int j=0;j<nVertices;j++)
				{
					cout << *(*(graph+i)+j) << " " ;
				}
				cout << endl;
			}
		}

		int minKey(int bucket[],int *visited)
		{
			int min=999,minIndex;
			for(int i=0;i<nVertices;i++)
			{
				if(*(visited+i)==0 && bucket[i]<min)
				{
					min = bucket[i];
					minIndex = i;
				}
			}
			return minIndex;
		}

		void primAlgo()
		{
			int mst[nVertices];
			int bucket[nVertices];

			for(int i=0;i<nVertices;i++)
			{
				bucket[i]=999;
				*(visited+i)=0;
			}

			bucket[0]=0;
			mst[0]=-1;

			for(int j=0;j<nVertices-1;j++)
			{
				int min = minKey(bucket,visited);
				visited[min]=1;
				for(int k=0;k<nVertices;k++)
				{
					if(*(visited+k)==0 && *(*(graph+min)+k) < bucket[k])
					{
						mst[k]=min;
						bucket[k]=*(*(graph+min)+k);
					}
				}
			}

			int cost=0;
			cout << endl;
			cout << "\t EDGE" << "\tWEIGHT" << endl;
			for(int i=1;i<nVertices;i++)
			{
				cout << "\t" <<mst[i] << " - " << i << "\t  " << *(*(graph+i)+mst[i]) << endl;
				cost += *(*(graph+i)+mst[i]);
			}
			cout << endl;
			cout << "\tThe cost of minimun spanning tree is " << cost << endl;

		}
};



int main()
{
	int nVertices,choice;
	char ch='y';
	cout << "\tEnter the number of vertices" << endl;
	cin >> nVertices;
	Graph graph(nVertices);
	while(ch == 'y')
	{
		cout << "\tMENU"<< endl;
		cout << "\t1.Create a graph" << endl;
		cout << "\t2.Prims Algorithm on created graph" << endl;
		cout << "\t3.Display" << endl;
		cin >> choice;
		switch(choice)
		{
			case 1:
				graph.createGraph(nVertices);
				break;
			case 2:
				graph.primAlgo();
				break;
			case 3:
				graph.display();
				break;
			default:
				cout << "\tINVALID CHOICE" << endl;
		}
		cout << "\tDo you wish to continue" << endl;
		cout << "\tenter y if yes" << endl;
		cin >> ch;
	}
}
