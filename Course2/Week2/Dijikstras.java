package Course2.Week2;

import javax.swing.plaf.nimbus.State;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Dijikstras {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("dijikstraData.txt"));
        int vertices = 200;
        int cost[][] = new int[vertices][vertices];
        int adj[][] = new int[vertices][vertices];

        for(int u=0;u<vertices;u++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();

            while(st.hasMoreTokens()) {
                String a[] = st.nextToken().split(",");
                int v = Integer.parseInt(a[0]) - 1;
                int weight = Integer.parseInt(a[1]);

                adj[u][v] = adj[v][u] = 1;
                cost[u][v] = cost[v][u] = weight;
            }
        }

        PriorityQueue<PQState> priorityQueue = new PriorityQueue<>(new Comparator<PQState>() {
            @Override
            public int compare(PQState o1, PQState o2) {
                return o1.cost-o2.cost;
            }
        });

        int[] answer = new int[vertices]; //Stores the minimum distance of each node from source node
        answer[0] = 0;

        int[] prev = new int[vertices]; //Stores the previous node for the given node
        boolean visited[] = new boolean[vertices]; // keeps track of visited nodes
        //Starting dijikstras algorithm
        priorityQueue.add(new PQState(0,0,-1)); // adding 1st node in PriorityQueue
        while(!priorityQueue.isEmpty()){
            PQState pqs = priorityQueue.poll();

            int currentNode = pqs.u;
            int cst = pqs.cost;

            if(visited[currentNode]) //skip if current node is already processed
                continue;

            visited[currentNode] = true; //set currentnode as visited
            answer[currentNode] = cst; // update its weight
            prev[currentNode] = pqs.prev; // set its previous node

            for(int i=0;i<200;i++){
                if(adj[currentNode][i]!=0){
                    if(visited[i]!=true){
                        priorityQueue.add(new PQState(i,cst + cost[currentNode][i],currentNode));
                    }
                }
            }

            for(int i=0;i<vertices;i++)
                System.out.print(answer[i]+",");
        }

    }
}
class PQState{
    int u,prev,cost;
    PQState(int u,int prev, int cost){
        this.u = u;
        this.prev = prev;
        this.cost = cost;
    }
}
