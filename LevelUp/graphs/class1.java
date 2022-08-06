package graphs;

public class class1 {
    
    //	https://leetcode.com/problems/is-graph-bipartite/
    //	https://leetcode.com/problems/bus-routes/
    //	https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/graphs/number-of-distinct-island-official/ojquestion

    public static int numDistinctIslands(int[][] arr) {
      
        HashSet<String> set = new HashSet<>(); 
        
        int m = arr.length, n = arr[0].length;
        boolean[][] visited = new boolean[m][n]; 
        
        for(int i=0; i<m; i++){
           for(int j=0; j<arr[0].length; j++){
              
               if(!visited[i][j] && arr[i][j]==1){
                   StringBuilder island = new StringBuilder();
                   dfs(arr, i, j, visited, island);
                   set.add(island.toString());
               }
           }    
        }
        
        return set.size();
     }
     
     static void dfs(int[][] arr, int i, int j, boolean[][] visited, StringBuilder island){
         
         visited[i][j]=true;
         
         if(i-1>=0 && arr[i-1][j]==1 && !visited[i-1][j]){
             island.append("N");
             dfs(arr, i-1, j, visited, island);
         }
         
         if(j-1>=0 && arr[i][j-1]==1 && !visited[i][j-1]){
             island.append("W");
             dfs(arr, i, j-1, visited, island);
         }
         
         if(i+1<arr.length && arr[i+1][j]==1 && !visited[i+1][j]){
             island.append("S");
             dfs(arr, i+1, j, visited, island);
         }
         
         if(j+1<arr[0].length && arr[i][j+1]==1 && !visited[i][j+1]){
             island.append("E");
             dfs(arr, i, j+1, visited, island);
         }
         
         island.append("B");
     } 




    //  https://leetcode.com/problems/01-matrix/submissions/
    //  https://leetcode.com/problems/as-far-from-land-as-possible/
    //  https://pepcoding.com/resources/online-java-foundation/graphs/shortest-path-in-weights-official/ojquestion
    //  https://pepcoding.com/resources/online-java-foundation/graphs/shortest-path-in-weights-official/ojquestion
    //  https://leetcode.com/problems/swim-in-rising-water/
    //  https://pepcoding.com/resources/online-java-foundation/graphs/minimum-wire-to-connect-all-pcs-official/ojquestion
    //  https://www.geeksforgeeks.org/minimum-cost-connect-cities/

    // https://github.com/sumeet-malik/level2and3
    // https://leetcode.com/problems/coloring-a-border/
    // https://leetcode.com/problems/shortest-bridge/

    // https://practice.geeksforgeeks.org/problems/strongly-connected-components-kosarajus-algo/1
    // https://www.lintcode.com/problem/178/
    // https://practice.geeksforgeeks.org/problems/mother-vertex/1
    // https://leetcode.com/problems/clone-graph/
    // https://github.com/sumeet-malik/level2and3

    // https://www.geeksforgeeks.org/given-sorted-dictionary-find-precedence-characters/
    // https://www.geeksforgeeks.org/topological-sorting-indegree-based-solution/
    // https://leetcode.com/problems/course-schedule-ii/

   
    //kruskals
    
   static int[] parent;
   static int[] rank;
   
   static void kruskals(ArrayList<Edge>[] graph){
       
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int V = graph.length;
        
        for(int v=0; v<V; v++){
            
            for(Edge edge : graph[v]){
                pq.add(edge);
            }
        }
        
        parent = new int[V];
        rank = new int[V];
        
        for(int v=0; v<V; v++){
            
            parent[v]=v;
            rank[v]=0;
        }
        
        while(pq.size()>0){
            
            Edge e = pq.remove();
            
            int srcLead = find(e.src);
            int nbrLead = find(e.nbr);
            
            if(srcLead != nbrLead){
                System.out.println(e.src+"-"+e.nbr+"@"+e.wt);
                
                union(srcLead, nbrLead);
            }
            
            
        }
   }
   
   static int find(int x){
       if(parent[x]==x) return x;
       else{
           int parentOfX = parent[x];
           int setLead = find(parentOfX);
           
           parent[x] = setLead; // compression
           return setLead;
       }
   }
   
   static void union(int srcLead, int nbrLead){
       
       if(rank[srcLead]<rank[nbrLead]){
            parent[srcLead] = nbrLead;   
       }
       else if(rank[srcLead]>rank[nbrLead]){
            parent[nbrLead] = srcLead;
       }
       else{
            parent[srcLead] = nbrLead;
            rank[srcLead]++;
       }    
   }


   // https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/graphs/number-of-island-2-official/ojquestion

//    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
//     int[][] grid = new int[m][n];
//     parent = new int[m * n];
//     rank = new int[m * n];
    
//     for(int i = 0; i < m * n; i++){
//         parent[i] = i;
//         rank[i] = 0;
//     }
    
//     ArrayList<Integer> res = new ArrayList<>();
//     count = 0;
    
//     for(int[] pos: positions){
//         int x = pos[0];
//         int y = pos[1];
//         grid[x][y] = 1;
        
//         count++;
        
//         handleNewCell(x, y, x - 1, y, m, n, grid);
//         handleNewCell(x, y, x + 1, y, m, n, grid);
//         handleNewCell(x, y, x, y - 1, m, n, grid);
//         handleNewCell(x, y, x, y + 1, m, n, grid);
        
//         res.add(count);
//     }
    
//     return res;
//   }
  
//   static void handleNewCell(int x, int y, int xx, int yy, int m, int n, int[][] grid){
//       if(xx < 0 || yy < 0 || xx >= m || yy >= m || grid[xx][yy] == 0){
//           return;
//       }
      
//       int xyCell = x * n + y;
//       int xxyyCell = xx * n + yy;
      
//       int xylead = find(xyCell);
//       int xxyylead = find(xxyyCell);
      
//       if(xylead != xxyylead){
//           count--;
//           union(xylead, xxyylead);
//       }
//   }
  
//   static int count;
//   static int[] parent;
//   static int[] rank;
  
  
//   static int find(int i){
//       if(parent[i] == i){
//           return i;
//       } else {
//           parent[i] = find(parent[i]);
//           return parent[i];
//       }
//   }
  
//   static void union(int i, int j){
//       if(rank[i] < rank[j]){
//           parent[i] = j;
//       } else if(rank[j] < rank[i]){
//           parent[j] = i;
//       } else {
//           parent[i] = j;
//           rank[j]++;
//       }
//   }



    // https://leetcode.com/problems/redundant-connection/
    // https://leetcode.com/problems/regions-cut-by-slashes/
    // https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/graphs/sentence_similarity_official/ojquestion

    //https://leetcode.com/problems/satisfiability-of-equality-equations/
    //https://leetcode.com/problems/evaluate-division/ -d



    // articulation point
    // https://practice.geeksforgeeks.org/problems/articulation-point2616/1/ -d
    // https://practice.geeksforgeeks.org/problems/bridge-edge-in-graph/1
    // https://practice.geeksforgeeks.org/problems/doctor-strange2206/1


    // https://practice.geeksforgeeks.org/problems/minimum-swaps/1/
    // https://www.geeksforgeeks.org/minimum-swaps-to-make-two-array-identical/
    // https://www.codingninjas.com/codestudio/problems/minimum-swaps-to-make-identical-array_3843992?leftPanelTab=1
    // https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/graphs/optimize-water-distribution-official/ojquestion
    
 




    // https://practice.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1
    // https://codeforces.com/contest/1534/problem/C

    public static void solve(int[] r1, int[] r2){

        int n = r1.length;

        parent = new int[n];
        rank = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
            rank[i] = 0;
        }

        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();

        for(int i = 0; i < n; i++){
            map1.put(r1[i], i);
        }

        for(int i = 0; i < n; i++){
            map2.put(r2[i], i);
        }

        for(int i = 0; i < n; i++){
            int xlead = find(i);

            int y = map2.get(r1[i]);
            int ylead = find(y);

            if(xlead != ylead){
                union(xlead, ylead);
            }
            
        }

        int count = 0;
        for(int i = 0; i < n; i++){
            if(parent[i] == i){
                count++;
            }
        }

        int MOD = 1000000007;
        int ans = 1;

        for(int i = 1; i <= count; i++){
            ans = ((ans % MOD) * (2 % MOD)) % MOD;
        }

        System.out.println(ans);
    }


    // https://www.hackerrank.com/challenges/journey-to-the-moon/problem  - dsu or connected comp
    // https://www.pepcoding.com/resources/online-java-foundation/graphs/perfect-friends-official/ojquestion
    // https://practice.geeksforgeeks.org/problems/euler-circuit-in-a-directed-graph/1
    // https://practice.geeksforgeeks.org/problems/euler-circuit-and-path/1/
    // https://practice.geeksforgeeks.org/problems/eulerian-path-in-an-undirected-graph5052/1/#
    // https://practice.geeksforgeeks.org/problems/castle-run3644/1
    


    // https://leetcode.com/problems/minimize-malware-spread/
    // https://leetcode.com/problems/similar-string-groups/
    // https://leetcode.com/problems/redundant-connection-ii/ -d


    // https://leetcode.com/problems/sliding-puzzle/
    // https://leetcode.com/problems/k-similar-strings/
    // https://leetcode.com/problems/reconstruct-itinerary/
    // https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/


    // https://leetcode.com/problems/find-eventual-safe-states/
    // https://leetcode.com/problems/smallest-string-with-swaps/
    // https://leetcode.com/problems/bricks-falling-when-hit/

    // https://leetcode.com/problems/cracking-the-safe/



    // https://leetcode.com/problems/cheapest-flights-within-k-stops/

    // https://practice.geeksforgeeks.org/problems/distance-from-the-source-bellman-ford-algorithm/1/
    // https://leetcode.com/problems/graph-connectivity-with-threshold/
    // https://practice.geeksforgeeks.org/problems/implementing-floyd-warshall2042/1
    


}
