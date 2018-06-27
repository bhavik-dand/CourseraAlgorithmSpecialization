package Course1.Week4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class KargerMinCut {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("kargerMinCut.txt" ));

        ArrayList<Edge> edges = new ArrayList<>();

        for(int u=0; u<200; u++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            while(st.hasMoreTokens()){
                int v = Integer.parseInt(st.nextToken());
                if(v < u)  // to avoid adding duplicate edges
                    continue;
                edges.add(new Edge(u,v));
            }
        }
        int min_cut = 200;
        int numOfIterations = 1000;
        while(numOfIterations>0){
            int verticesCG = 200;

            ArrayList<Edge> contractedGraph = (ArrayList<Edge>) edges.clone();
            while(verticesCG > 2){
                int randomEdge = getRandomEdge(contractedGraph.size());
                merge(contractedGraph,randomEdge);
                verticesCG--;
            }
            min_cut = Math.min(min_cut,contractedGraph.size());
        }
        System.out.println(min_cut);
    }

    private static void merge(ArrayList<Edge> contractedGraph, int randomEdge) {
        Edge contractedEdge = contractedGraph.get(randomEdge);
        int min = Math.min(contractedEdge.u,contractedEdge.v);
        int max = Math.max(contractedEdge.u,contractedEdge.v);

        //Removing Contracted Edge
        contractedGraph.remove(contractedEdge);


        //Merging other edges
        for(Edge e : contractedGraph){
            if(e.u == min){
                e.u = max;
            }
            else if(e.v == min){
                e.v = max;
            }

        }
    }

    private static int getRandomEdge(int size) {
        return (int) Math.random() * size;
    }
}
class Edge{
    int u,v;
    Edge(int u,int v){
        this.u = u;
        this.v = v;
    }
}

