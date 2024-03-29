package Graphs;

import java.util.*;

public class Graphs {
  static class Edge {
    int src;
    int nbr;
    int wt;

    Edge(int src, int nbr, int wt) {
      this.src = src;
      this.nbr = nbr;
      this.wt = wt;
    }
  }
  
//       ArrayList<Edge>[] graph = new ArrayList[vtces];  // what is graph here- an array of arrayLists of edges. also called adjacency matrix
//       for(int i = 0; i < vtces; i++){
//          graph[i] = new ArrayList<>();
//       }

//       int edges = Integer.parseInt(br.readLine());
//       for(int i = 0; i < edges; i++){
//          String[] parts = br.readLine().split(" ");
//          int v1 = Integer.parseInt(parts[0]);
//          int v2 = Integer.parseInt(parts[1]);
//          int wt = Integer.parseInt(parts[2]);
//          graph[v1].add(new Edge(v1, v2, wt));
//          graph[v2].add(new Edge(v2, v1, wt));
//       }

  public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited) {

    if (src == dest)
      return true;

    visited[src] = true;
    for (Edge edge : graph[src]) {
      if (!visited[edge.nbr]) {
        boolean nbrHasPath = hasPath(graph, edge.nbr, dest, visited);
        if (nbrHasPath)
          return true;
      }
    }

    return false;
  }

  public static void allPaths(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, String path) {

    if (src == dest) {
      System.out.println(path);
      return;
    }

    visited[src] = true;
    for (Edge edge : graph[src]) {
      if (!visited[edge.nbr]) {
        allPaths(graph, edge.nbr, dest, visited, path + edge.nbr);

      }
    }
    visited[src] = false;

  }

  static String spath;
  static Integer spathwt = Integer.MAX_VALUE;
  static String lpath;
  static Integer lpathwt = Integer.MIN_VALUE;
  static String cpath;
  static Integer cpathwt = Integer.MAX_VALUE;
  static String fpath;
  static Integer fpathwt = Integer.MIN_VALUE;
  static PriorityQueue<Pair> pq = new PriorityQueue<>();

  static class Pair implements Comparable<Pair> {
    int wsf;
    String psf;

    Pair(int wsf, String psf) {
      this.wsf = wsf;
      this.psf = psf;
    }

    public int compareTo(Pair o) {
      return this.wsf - o.wsf;
    }
  }

  public static void multisolver(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, int criteria, int k,
      String psf, int wsf) {
    if (src == dest) {
      if (wsf <= spathwt) {
        spathwt = wsf;
        spath = psf;
      }
      if (wsf >= lpathwt) {
        lpathwt = wsf;
        lpath = psf;
      }
      if (wsf > criteria && wsf < cpathwt) {
        cpathwt = wsf;
        cpath = psf;
      }
      if (wsf < criteria && wsf > fpathwt) {
        fpathwt = wsf;
        fpath = psf;
      }

      if (pq.size() < k) { // k=0,1,2
        pq.add(new Pair(wsf, psf));
      } else { // k=3
        if (pq.peek().wsf < wsf) { // team of strongest players
          pq.remove();
          pq.add(new Pair(wsf, psf));
        }
      }

      return;
    }

    visited[src] = true;
    for (Edge edge : graph[src]) {
      if (!visited[edge.nbr]) {
        multisolver(graph, edge.nbr, dest, visited, criteria, k, psf + edge.nbr, wsf + edge.wt);

      }
    }
    visited[src] = false;
  }

  // ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
  // boolean[] visited = new boolean[vtces];

  // for(int i=0; i<vtces; i++){
        // if(!visited[i]){
        //       ArrayList<Integer> comp= new ArrayList<>();
        //       gcc(graph, i, visited, comp);
        //       comps.add(comp);
        // }
  // }

  public static void gcc(ArrayList<Edge>[] graph, int v, boolean[] visited, ArrayList<Integer> comp) {

    visited[v] = true;
    comp.add(v);

    for (Edge edge : graph[v]) {
      if (!visited[edge.nbr]) {
        gcc(graph, edge.nbr, visited, comp);
      }
    }

  }
  
  //is graph connected
//   static void dfs(int v, boolean[] visited, ArrayList<Edge>[] graph){
          
//     visited[v] = true;
//     numc++;

//         for (Edge edge : graph[v]) {
//           if (!visited[edge.nbr]) {
//             dfs(edge.nbr, visited, graph);
//           }
//         }
//     }
   
//    static int numc=0;
  
//    dfs(0, new boolean[vtces], graph);
//    System.out.println(numc==vtces);
  
  
  
  
  

  // boolean[][] visited = new boolean[m][n];
  // int count=0;

  // for (int i = 0; i < m; i++) {
  // for (int j = 0; j < n; j++) {
  // if (arr[i][j] == 0 && !visited[i][j]) {

  // drawTreeForComponent(arr, i, j, visited);
  // count++; // components count
  // }
  // }
  // }

  // System.out.println(count);

  public static void drawTreeForComponent(int[][] arr, int i, int j, boolean[][] visited) {

    if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length || arr[i][j] == 1 || visited[i][j] == true)
      return;

    visited[i][j] = true;

    drawTreeForComponent(arr, i + 1, j, visited);
    drawTreeForComponent(arr, i, j + 1, visited);
    drawTreeForComponent(arr, i - 1, j, visited);
    drawTreeForComponent(arr, i, j - 1, visited);
  }

  //perfect friends
  // int pairs=0;
  // for(int i=0; i<comps.size()-1; i++){
  //     for(int j=i+1; j<comps.size(); j++){
  //       pairs += comps.get(i).size()*comps.get(j).size();  
  //     }
  // }
  
  // System.out.println(pairs);

  public static void hamiltonian(ArrayList<Edge>[] graph, int v, int src, int vtces, HashSet<Integer> visited,
  String path) {

    if (path.length() == vtces) {  //or visited.size() == vtces-1

      boolean flag = true;
      for (Edge edge : graph[v]) {
        if (edge.nbr == src) {
          System.out.println(path + "*"); //hcycle
          flag = false;
        }
      }

      if (flag)
        System.out.println(path + "."); //hpath

      return;
    }

    visited.add(v);
    for (Edge edge : graph[v]) {
      if (!visited.contains(edge.nbr)) {
        hamiltonian(graph, edge.nbr, src, vtces, visited, path + edge.nbr);

      }
    }
    visited.remove(v);
  }

  static class Pair{
    int v;
    String psf;

    Pair(int v, String psf){
      this.v=v;
      this.psf=psf;
    }
  }

  // BFS

  // boolean[] visited = new boolean[vtces]; 
  // Queue<Pair> q = new ArrayDeque<>();
  // q.add(new Pair(src,src+""));

  // while(q.size()>0){
  //   Pair rem = q.remove(); 
  //   if (visited[rem.v]) continue;    // if already visited then we are reaching here late with may be longer path. hence, discarded.

  //   visited[rem.v] = true;

  //   System.out.println(rem.v + "@" + rem.psf); 
  //   for (Edge edge : graph[rem.v]) {
  //     if (!visited[edge.nbr]) {       // if already visited then we are reaching here late with may be longer path. hence, discarded.
  //       q.add(new Pair(edge.nbr, rem.psf + edge.nbr));
  //     }
  //   }
  // }
  

  
  // is graph cyclic

  // boolean[] visited = new boolean[vtces]; 

  // for(int i = 0;i<vtces;i++){
  //   if (!visited[i]) {
  //     Queue<Integer> q = new ArrayDeque<>();
  //     q.add(i);

  //     while (q.size() > 0) {
  //       int rem = q.remove();
  //       if (visited[rem]) {
  //         System.out.println("true");
  //         return;
  //       }

  //       visited[rem] = true;

  //       for (Edge edge : graph[rem]) {
  //         if (!visited[edge.nbr]) {
  //           q.add(edge.nbr);
  //         }
  //       }
  //     }
  //   }
  // }

  // System.out.println("false");




//   by dfs
   
//   for(int v = 0; v<vtces; v++){
//     if (!visited[v]) {
//         if(dfs(v, -1, graph, visited)){
//            System.out.println(true);
//            return; 
//         } 
//     } 
//  }
 
//  System.out.println(false);



//  public static boolean dfs(int src, int parent, ArrayList<Edge>[] graph, boolean[] visited) {

//   if (visited[src]==true)
//     return true;

//   visited[src] = true;
//   for (Edge edge : graph[src]) {
//     if (edge.nbr!=parent) {
//       boolean nbrHasPath = dfs(edge.nbr, src, graph, visited);
//       if (nbrHasPath)
//         return true;
//     }
//   }

//   return false;
// }



  // bipartite
  // int[] visited = new int[vtces];  // array for levels
  // Arrays.fill(visited,-1);
  // for(int i = 0;i<vtces;i++){
  //   if (visited[i] == -1) {
  //     if (isComponentBipartite(graph, i, visited) == false) {
  //       System.out.println("false");
  //       return;
  //     }
  //   }
  // }

  // System.out.println("true");

  static class Pair1{
      int v;
      int level;

      Pair1(int v, int level){
        this.v=v;
        this.level=level;
      }
  }

  public static boolean isComponentBipartite(ArrayList<Edge>[] graph, int src, int[] visited) {

    Queue<Pair> q = new ArrayDeque<>();
    q.add(new Pair1(src, 0));

    while (q.size() > 0) {
      Pair1 rem = q.remove();

      if (visited[rem.v] == -1) visited[rem.v] = rem.level;  
      else if (visited[rem.v] != rem.level) return false;
      

      for (Edge edge : graph[rem.v]){
        if (visited[edge.nbr] == -1){
          Pair1 p = new Pair1(edge.nbr, rem.level + 1);
          q.add(p);
        }
      }
    }

    return true;
  }

      

  // infection
  // int count = 0; 

  // boolean[] visited = new boolean[vtces]; // BFS
  // Queue<Pair> q = new ArrayDeque<>();
  // q.add(new Pair(src,1));

  // while(q.size()>0){
  //   Pair rem = q.remove();
  //   if (rem.t > t)
  //     break;
  //   if (visited[rem.v])
  //     continue;

  //   count++;
  //   visited[rem.v] = true;

  //   for (Edge edge : graph[rem.v]) {
  //     if (!visited[edge.nbr]) {
  //       Pair p = new Pair(edge.nbr, rem.t + 1);
  //       q.add(p);
  //     }
  //   }
  // }

  // System.out.println(count);




  // dijkstra
  static class Pair implements Comparable<Pair> { 
    int v;
    String path;
    int wt;

    Pair(int v, String path, int wt) {
      this.v = v;
      this.path = path;
      this.wt = wt;
    }

    public int compareTo(Pair p) {
      return this.wt - p.wt;
    }
  }

  // int src = Integer.parseInt(br.readLine());
  // boolean[] visited = new boolean[vtces];
  // Queue<Pair> q = new PriorityQueue<>();
  // q.add(new Pair(src,src+"",0));

  // while(q.size()>0)
  // {
  //   Pair rem = q.remove();
  //   if (visited[rem.v])
  //     continue;

  //   visited[rem.v] = true;
  //   System.out.println(rem.v + " via " + rem.path + " @ " + rem.wt);
  //   for (Edge edge : graph[rem.v]) {
  //     if (!visited[edge.nbr]) {
  //       Pair p = new Pair(edge.nbr, rem.path + edge.nbr + "", rem.wt + edge.wt);
  //       q.add(p);
  //     }
  //   }
  // }


  //min wire
  //prim's algo

  // static class Pair implements Comparable<Pair> { 
  //   int v;
  //   int av;
  //   int wt;

  //   Pair(int v, int av, int wt) {
  //     this.v = v;
  //     this.av = av;
  //     this.wt = wt;
  //   }

  //   public int compareTo(Pair p) {
  //     return this.wt - p.wt;
  //   }
  // }

  // boolean[] visited = new boolean[vtces];
  // Queue<Pair> q = new PriorityQueue<>();
  // q.add(new Pair(0,-1,0));

  // while(q.size()>0){
  //   Pair rem = q.remove();

  //   if (visited[rem.v])
  //     continue;

  //   visited[rem.v] = true;
  //   if (rem.v != 0)
  //     System.out.println("[" + rem.v + "-" + rem.av + "@" + rem.wt + "]");

  //   for (Edge edge : graph[rem.v]) {
  //     if (!visited[edge.nbr]) {
  //       Pair p = new Pair(edge.nbr, rem.v, edge.wt);
  //       q.add(p);
  //     }
  //   }
  // }




  // topological sort 

  // boolean[] visited = new boolean[vtces]; 
  // Stack<Integer> st = new Stack<>();
  // for(int i = 0;i<vtces;i++)
  // {
  //   if (!visited[i]) {
  //     topologicalSort(graph, i, visited, st);
  //   }
  // }

  // while(st.size()>0)
  // {
  //   System.out.println(st.pop());
  // }

  // public static void topologicalSort(ArrayList<Edge>[] graph, int v, boolean[] visited, Stack<Integer> st){
       
  //       visited[v]=true;
  //       for(Edge edge: graph[v]){
  //           if(!visited[edge.nbr])
  //          topologicalSort(graph, edge.nbr, visited, st); 
  //       }
  //       st.push(v);
  //   }





  // iterative DFS  

  static class Pair {
    int v;
    String path;

    Edge(int v, String path) {
       this.v = v;
       this.path = path;
    }
 }

  boolean[] visited = new boolean[vtces]; 
  Stack<Pair> st = new Stack<>();
  st.push(new Pair(src,src+""));

  while(st.size()>0){
    Pair rem = st.pop();

    if (visited[rem.v])
      continue;

    visited[rem.v] = true;

    System.out.println(rem.v + "@" + rem.path);

    for (Edge edge : graph[rem.v]) {
      if(!visited[edge.nbr]) st.push(new Pair(edge.nbr, rem.path + edge.nbr));
    }
  }


}
