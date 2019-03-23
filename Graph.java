// Java program to implement graph and various operations on graphs
import java.io.*; 
import java.util.*;

import Bipartite.Bipartite;
import ShortestPath.ShortestPath;

import java.lang.*;
  
// Here I've created a graph using adjacency list representation as well as adjacency matrix representation
class Graph 
{ 
    private int V;   
  
    // To represent a graph an array list is created 
    private LinkedList<Integer> adj[]; 
  
   
    Graph(int v)//constructor 
    { 
        V = v; 
        adj = new LinkedList[v]; 
        for (int i=0; i<v; ++i) 
            adj[i] = new LinkedList(); 
    } 
    
   
  
    
    
    //Function to add an edge into the graph.
    //Since it is directed graph the edge is added only from v to w
    void addEdge(int v, int w) 
    { 
        adj[v].add(w);  // Add w to v's list or add an edge from v to w. 
    } 
    
    
 
    
    //CYCLE
    // This function checks whether a cycle is present in a graph or not 
    private boolean isCyclicHelper(int i, boolean[] visited, 
                                      boolean[] recStack)  
    { 
          
         
         
        if (recStack[i]) 
            return true; 
  
        if (visited[i]) 
            return false; 
              
        visited[i] = true; // Mark the current node as visited and a part of the recursion stack
  
        recStack[i] = true; 
        List<Integer> children = adj[i]; 
          
        for (Integer c: children) 
            if (isCyclicHelper(c, visited, recStack)) //function calls itself recursively
                return true; 
                  
        recStack[i] = false; 
  
        return false; 
    } 

	//CYCLE 2ND PART
 // The function returns true if graph contains cycle, else false. 
    private boolean isCyclic()  
    { 
          
        // Mark all the vertices as not visited and not a part of recursion stack 
         
        boolean[] visited = new boolean[V]; 
        boolean[] recStack = new boolean[V]; 
          
          
       
        for (int i = 0; i < V; i++) 
            if (isCyclicHelper(i, visited, recStack))  //Call isCyclicHelper to detect cycle
                return true; 
  
        return false;
    }
    
    
 // This function returns true if the graph is a tree, else false. 
    Boolean isTree() 
    { 
        
 
        Boolean visited[] = new Boolean[V]; // Mark all the vertices as not visited 
        for (int i = 0; i < V; i++) 
            visited[i] = false; 
  
        
        /* The function isTreeHelper returns true if graph reachable from vertex 0 
        is cyclic. It also marks all vertices reachable from 0. */
        if (isTreeHelper(0, visited, -1)) 
            return false; 
  
        /* If a vertex is not reachable from 0 then return false*/ 
        for (int u = 0; u < V; u++) 
            if (!visited[u]) 
                return false; 
  
        return true; 
    } 
    
    
 // A recursive function that detects a cycle in a subgraph
    //This function is called by istree() to check whether there is cycle in a graph or not
    Boolean isTreeHelper(int v, Boolean visited[], int parent) 
    { 
        // Mark the current node as visited 
        visited[v] = true; 
        Integer i; 
  
        // Recur for all the vertices adjacent to this vertex 
        Iterator<Integer> it = adj[v].iterator(); 
        while (it.hasNext()) 
        { 
            i = it.next(); 
  
            // If an adjacent is not visited, then recur for adjacent 
            if (!visited[i]) 
            { 
                if (isTreeHelper(i, visited, v)) 
                    return true; 
            } 
  
            // If an adjacent is visited and not parent of current vertex, then there is a cycle. 
            else if (i != parent) 
               return true; 
        } 
        return false; 
    }//EO isTreeHelper()
    
    
  
    // A helper function used by DFS 
    void DFSHelper(int v,boolean visited[]) 
    { 
        // Mark the current node as visited and print it 
        visited[v] = true; 
        System.out.print(v+" "); 
  
        // Recur for all the vertices adjacent to this vertex 
        Iterator<Integer> i = adj[v].listIterator(); 
        while (i.hasNext()) 
        { 
            int n = i.next(); 
            if (!visited[n]) 
                DFSHelper(n, visited); 
        } 
    } 
  
    // The function to do DFS traversal 
    void DFS(int v) 
    { 
        
        boolean visited[] = new boolean[V];//used to declare all vertices as unvisited 
  
        // Call the DFSHelper function to print DFS traversal 
        DFSHelper(v, visited); 
    } 
    
 // prints BFS traversal from the given start node  
    void BFS(int start) 
    { 
       
        boolean visited[] = new boolean[V]; //used to declare all vertices as unvisited 
  
        // Creates a queue for BFS 
        LinkedList<Integer> queue = new LinkedList<Integer>(); 
  
        // Mark the current node as visited and enqueue it 
        visited[start]=true; 
        queue.add(start); 
  
        while (queue.size() != 0) 
        { 
            // Dequeue a vertex from queue and print it 
            start = queue.poll(); 
            System.out.print(start+" "); 
  
            /* Get all adjacent vertices of the dequeued vertex s. 
     If a adjacent is not visited, then mark it visited and enqueue it*/ 
            Iterator<Integer> i = adj[start].listIterator(); 
            while (i.hasNext()) 
            { 
                int n = i.next(); 
                if (!visited[n]) 
                { 
                    visited[n] = true; 
                    queue.add(n); 
                } 
            } 
        } 
    } 
    
    
  
     
  
    public static void main(String args[]) 
    { 
    	Scanner sc=new Scanner(System.in);
        Graph g = new Graph(5);
        int graph[][] = new int[5][5];
        
        int x,v1,v2,weight;
        
        int option;
		boolean exit=true;
		while(exit) { 
		System.out.println("\nOperations: \n1) Create a directed graph\n2) DFS Traversal\n3) BFS Traversal\n"
				+ "4) Dijkstra's shortest path \n5) Detect cycle \n6)Check if graph is bipartite \n7) Check if graph is tree \n8) Exit\nSelect option:");
		option = sc.nextInt();
		//switch statement is used to choose one of the eight operations
		switch (option) {
		case 1:
			System.out.println("Enter the number of edges you want in graph");
			x=sc.nextInt();
			for(int i=0;i<x;i++) {
			System.out.println("Enter the starting vertex"); 
			v1=sc.nextInt();
			System.out.println("Enter the ending vertex");
			v2=sc.nextInt();
			g.addEdge(v1, v2);
			System.out.println("Enter the weight");
			weight=sc.nextInt();
			graph[v1][v2]=weight;//The weight is added in adjacency matrix; which is used for Dijkstra's and Bipartite
			}
			break;
		case 2:
			System.out.println("Enter the starting vertex");
			int n=sc.nextInt();
			System.out.println("Following is DFS traversal of the graph"); 
			  
	        g.DFS(n);
			break;
		case 3:
			System.out.println(" ");
			System.out.println("Enter the starting vertex");
			int m=sc.nextInt();
	        System.out.println("Following is BFS traversal of the graph");
	        
	        g.BFS(m);
			break;
		case 4:
			System.out.println("Dijkstra's algorithm");
			System.out.println("Enter the vertex");
			int s=sc.nextInt();
			ShortestPath t = new ShortestPath();
	        t.dijkstra(graph, s);
			break;
		case 5:
			if(g.isCyclic()) 
	            System.out.println("The graph contains cycle"); 
	        else
	            System.out.println("The graph does not contain cycle");
			break;
		case 6:
			Bipartite b = new Bipartite(); 
	        if (b.isBipartite(graph, 0)) 
	        System.out.println("Yes, it is biparite"); 
	        else
	        System.out.println("No, its not biparite"); 
			break;
		case 7:
			if (g.isTree()) 
	            System.out.println("The graph is Tree"); 
	        else
	            System.out.println("The graph is not Tree");
			break;
		case 8:
			exit = false;
			break;
		default:
			break;
			
		}//EO SWITCH
		} //EO WHILE
		
    }//EO PSVM 
} //EO CLASS
