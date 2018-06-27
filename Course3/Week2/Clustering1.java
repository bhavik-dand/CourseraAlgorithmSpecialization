package Course3.Week2;

import java.io.*;
import java.util.*;

public class Clustering1 {
    static int par[];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("clustering1.txt"));
        int n = Integer.parseInt(br.readLine());
       
        ArrayList<Edge> e = new ArrayList<>();
        while(true) {
            String line = br.readLine();
            if(line == null)
                break;
            StringTokenizer st = new StringTokenizer(line);
            int u = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken())-1;
            int cost = Integer.parseInt(st.nextToken());
            e.add(new Edge(u,v,cost));
        }

        Collections.sort(e,new Comparator<Edge>(){
            public int compare(Edge e1,Edge e2){
                return e1.cost - e2.cost;
            }
        });

        int clusters = n;
        par = new int[n];
        int i = 0;
        Arrays.fill(par, -1);
        while(clusters > 4) {
            Edge next = e.get(i++);
            int u = next.u;
            int v = next.v;
            if(root(u) == root(v)) continue;
            formCluster(u,v);
            clusters--;
        }

        int answer = 100000000;

        for(Edge c:e) {
            if(root(c.u) != root(c.v))
                answer = Math.min(answer, c.cost);
        }
        System.out.println(answer);

    }

    static void formCluster(int x, int y) {
        x = root(x);
        y = root(y);
        if(x == y) return;
        if(par[x] < par[y]) {
            int tmp = x;
            x = y;
            y = tmp;
        }
        par[y] += par[x];
        par[x] = y;
    }

    static int root(int x)    {
        return (par[x] < 0 ? x : (par[x] = root(par[x])));
    }

    static class Edge{
        int u,v,cost;
        Edge(int u,int v,int cost)
        {
            this.u = u;
            this.v = v;
            this.cost = cost ;
        }
    }
}
