#include <iostream>

using namespace std;

class Edge{
private:
    int startNode,endNode,weight;
public:
    Edge(){};
    Edge(int sn,int en,int w){
        startNode = sn;
        endNode = en;
        weight =w;
    }

    Edge(const Edge& e){
        startNode = e.startNode;
        endNode = e.endNode;
        weight = e.weight;
    }
    void setParameters(int sn,int en,int w){
        startNode = sn;
        endNode = en;
        weight =w;
    }
    int getStartNode(){return startNode;}
    int getEndNode(){return endNode;}
    int getWeight(){return weight;}
};

class Graph{
private:
    Edge* edgeArr;
    bool* vertices;
    int noOfVertices,noOfEdges;
public:
    Graph(){};
    Graph(int noOfVertices,int noOfEdges){
        edgeArr = new Edge[noOfEdges];
        vertices = new bool[noOfVertices];
        this->noOfEdges =noOfEdges;
        this->noOfVertices = noOfVertices;
        for(int i=0;i<noOfVertices;i++){
            vertices[i] = false;
        }
    }

    void initiateGraph(int noOfVertices,int noOfEdges){
        edgeArr = new Edge[noOfEdges];
        vertices = new bool[noOfVertices];
        this->noOfEdges =noOfEdges;
        this->noOfVertices = noOfVertices;
        for(int i=0;i<noOfVertices;i++){
            vertices[i] = false;
        }
    }

    Edge* getEdgeArr(){return edgeArr;}
    bool* getVertices(){return vertices;}
    int getNoOfVertices(){return noOfVertices;}
    int getNoOfEdges(){return noOfEdges;}
};


Graph inGraph;
Graph MST;
int totCost = 0;

void createGraph(){
    cout<<"Enter no of vertices in a graph : ";
    int noOfVertices;
    cin>>noOfVertices;
    cout<<"Enter no of edges in a graph : ";
    int noOfEdges;
    cin>>noOfEdges;
    inGraph.initiateGraph(noOfVertices,noOfEdges);
    for(int i =0; i<noOfEdges;i++){
        cout<<"Enter the start node, end node and weight of the Edge : ";
        int sN,eN,w;
        cin>>sN>>eN>>w;
        inGraph.getEdgeArr()[i].setParameters(sN, eN, w);
    }
}

void displayGraph(Graph graph){
    for(int i=0;i<graph.getNoOfEdges();i++){
        cout<<"{"<<graph.getEdgeArr()[i].getStartNode()<<","<<graph.getEdgeArr()[i].getEndNode()<<","<<graph.getEdgeArr()[i].getWeight()<<"}"<<endl;
    }
}

void graphSort(Graph graph){
    for(int i=1; i< graph.getNoOfEdges(); i++){
        Edge key = graph.getEdgeArr()[i];
        int j;
        for(j =i-1; j>=0; j--){
            if(key.getWeight() < graph.getEdgeArr()[j].getWeight()){
                graph.getEdgeArr()[j+1] = graph.getEdgeArr()[j];
            }
        }
        graph.getEdgeArr()[j+1] = key;
    }
}

void createMST(){
    MST.initiateGraph(inGraph.getNoOfVertices(), inGraph.getNoOfVertices()-1);
    int j = 0;
    for(int i=0;i< inGraph.getNoOfEdges(); i++){
        if(inGraph.getVertices()[inGraph.getEdgeArr()[i].getStartNode()] == true && inGraph.getVertices()[inGraph.getEdgeArr()[i].getEndNode()] == true){}
        else{
            MST.getEdgeArr()[j] = inGraph.getEdgeArr()[i];
            totCost += MST.getEdgeArr()[j].getWeight();
            inGraph.getVertices()[inGraph.getEdgeArr()[i].getStartNode()] = true;
            inGraph.getVertices()[inGraph.getEdgeArr()[i].getEndNode()] = true;
            j++;
        }
    }
}

int main(){
    createGraph();
    graphSort(inGraph);
    displayGraph(inGraph);
    createMST();
    cout<<"MST is :"<<endl;
    displayGraph(MST);
    cout<<"Total cost : "<<totCost<<endl;
}
