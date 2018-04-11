/*  
* Authors: Ethan Mitchell, Partner: Ryan Pallman
* Date: 4/10/2018
* Overview: Holds values for a Priority queue used Prim's and 
* Kruskal's algorithms
*/ 

//package graphAlgorithms;

public class Vertice implements Comparable<Vertice>
{
    int edge;       //Edge weight
    int key;
    int connect;    //Where does this vertice connect

    public Vertice(int edge, int key, int connect)
    {
        this.edge = edge;
        this.key  = key;
        this.connect = connect;
    }
    
    //Compare the edge values for the priority queue
    @Override
    public int compareTo(Vertice t) 
    {
        if(this.edge == t.edge)
            return 0;
        else if(this.edge > t.edge)
            return 1;
        else
            return -1;  
    }
    
    
}
