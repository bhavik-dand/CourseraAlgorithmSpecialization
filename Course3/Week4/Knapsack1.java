package Course3.Week4;

import java.io.*;
import java.util.*;

public class Knapsack1 {

    public static void main(String[] args)  throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("knapsack1.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int capacity = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int weight[] = new int[n+1];
        long value[] = new long[n+1];
        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            value[i] = Integer.parseInt(st.nextToken());
            weight[i] = Integer.parseInt(st.nextToken());
        }
        long a[][] = new long[n+1][capacity+1];
        for(int i=0; i<=n; i++)
            a[i][0] = 0;
        for(int i=0; i<=capacity; i++)
            a[0][i] = 0;
        for(int i=1; i<=n; i++)
            for(int j=1; j<=capacity; j++) {
                if(j < weight[i])
                    a[i][j] = a[i-1][j];
                else
                    a[i][j] = Math.max(a[i-1][j], value[i] + a[i-1][j-weight[i]]);
            }

        System.out.println(a[n][capacity]);

    }

}
