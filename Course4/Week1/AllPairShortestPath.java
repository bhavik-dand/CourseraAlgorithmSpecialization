package Course4.Week1;

import java.io.*;
import java.util.*;

public class AllPairShortestPath {
    public static void main(String[] args)  throws IOException{
        for(int fileNumber = 1;fileNumber<=3;fileNumber++) {
            String fileName = "g"+fileNumber+".txt";
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            long adj[][] = new long[n][n];

            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    adj[i][j] = 10000000L;

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken());
                adj[u][v] = c;
            }

            long answer = Long.MAX_VALUE;

            for (int k = 0; k < n; k++)
                for (int i = 0; i < n; i++)
                    for (int j = 0; j < n; j++) {
                        adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
                        answer = Math.min(adj[i][j], answer);
                    }

            boolean cycle = false;

            for (int i = 0; i < n; i++)
                if (adj[i][i] < 0)
                    cycle = true;
            System.out.println("Answer for file: "+fileName);
            if (cycle)
                System.out.println("Cycle Exists");
            else
                System.out.println(answer);
        }
    }

}