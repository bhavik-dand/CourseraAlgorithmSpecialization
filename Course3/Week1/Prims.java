package Course3.Week1;

import java.io.*;
import java.util.*;

public class Prims{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Integer> adj[] = new ArrayList[n];
        ArrayList<Long> cost[] = new ArrayList[n];
        for(int i=0; i<n; i++) {
            adj[i] = new ArrayList<>();
            cost[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken())-1;
            long cst = Integer.parseInt(st.nextToken());

            adj[u].add(v);
            adj[v].add(u);
            cost[u].add(cst);
            cost[v].add(cst);
        }

        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<Vertex>(new Comparator<Vertex>(){
            public int compare(Vertex v1,Vertex v2) { // vertices will be stored in sorted order on the basis of cost in priority queue
                if(v1.cost < v2.cost)
                    return -1;
                else if(v1.cost > v2.cost)
                    return 1;
                else return 0;
            }
        });

        priorityQueue.add(new Vertex(0,0)); //add 1st node
        boolean visited[] = new boolean[n];
        long answer = 0;

        while(!priorityQueue.isEmpty()) {
            Vertex currentNode = priorityQueue.remove();
            int x = currentNode.u;
            long c = currentNode.cost;

            if(visited[x]) //if visited then skip
                continue;
            visited[x] = true;
            answer += c;

            for(int i=0; i<adj[x].size(); i++) {
                if(!visited[adj[x].get(i)]) { // add neighbours
                    priorityQueue.add(new Vertex(adj[x].get(i),cost[x].get(i)));
                }
            }
        }
        System.out.println(answer);
    }

    static class Vertex{
        int u;
        long cost;
        Vertex(int u,long cost) {
            this.u = u;
            this.cost = cost;
        }
    }

}