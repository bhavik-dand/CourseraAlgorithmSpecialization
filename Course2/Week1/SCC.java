package Course2.Week1;

import java.io.*;
import java.util.*;

public class SCC {

    private ArrayList<ArrayList<Integer>> vertices;
    private ArrayList<ArrayList<Integer>> reverse;
    private int[] labels;
    private int[] leader;
    private int t;
    private int src;
    private boolean[] visited;

    public static void main(String[] args) throws FileNotFoundException{
        SCC g = new SCC("SCC.txt");
        int[] topSCCs = g.computeSCC();
        for (int n : topSCCs){
            System.out.print(n + " ");
        }
    }

    public SCC(String inputFileName) throws FileNotFoundException{
        vertices = new ArrayList<ArrayList<Integer>>();
        reverse = new ArrayList<ArrayList<Integer>>();
        Scanner in = new Scanner(new File(inputFileName));
        //add all vertices
        while (in.hasNextInt()){
            int tail = in.nextInt();
            int head = in.nextInt();
            int max = Math.max(tail, head);
            while (vertices.size() < max){
                vertices.add(new ArrayList<Integer>());
                reverse.add(new ArrayList<Integer>());
            }
            vertices.get(tail-1).add(head-1);
            reverse.get(head-1).add(tail-1);

        }
    }

    public int[] computeSCC(){
        int[] top5 = new int[5];
        DFS();
        DFSdash();
        Arrays.sort(leader);
        for (int i = 0; i < 5; i++){
            top5[i] = leader[leader.length - i - 1];
        }
        return top5;
    }

    public void DFS(){
        t = 0;
        visited = new boolean[reverse.size()];
        labels = new int[vertices.size()];
        for (int i = reverse.size()-1; i >= 0; i--){
            if (visited[i] == false){
                DFS(i);
            }
        }
    }

    public void DFSdash(){
        visited = new boolean[vertices.size()];
        leader = new int[vertices.size()];
        for (int i = labels.length - 1; i >= 0; i--){
            int node = labels[i];
            if (visited[node] == false){
                src = node;
                DFSdash(node);
            }
        }
    }

    public void DFS(int node){
        visited[node] = true;
        for (int head : reverse.get(node)){
            if (visited[head] == false){
                DFS(head);
            }
        }
        labels[t] = node;
        t++;
    }

    public void DFSdash(int node){
        visited[node] = true;
        leader[src] ++;
        for (int head : vertices.get(node)){
            if (visited[head] == false){
                DFSdash(head);
            }
        }
    }



}
