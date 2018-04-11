/*  
* Authors: Ethan Mitchell, Partner: Ryan Pallman
* Date: 4/10/2018
* Overview: Somewhat of a disjoint set for Kruskal’s Algoritm
*/ 

//package graphAlgorithms;


public class Set
{
    int parent[] = new int[100];
    
    public Set()
    {
        //inistalize the parent array
        for(int i=0; i<100; i++)
        {
            parent[i]=i;
        }
    }
    
    int find(int find)
    {
        if(parent[find] == find)
        {
            return find;
        }
        return find(parent[find]);
    }
 
    void join(int x, int y)
    {
        int fx = find(x);
        int fy = find(y);
        parent[fx] = fy;
    }
}
