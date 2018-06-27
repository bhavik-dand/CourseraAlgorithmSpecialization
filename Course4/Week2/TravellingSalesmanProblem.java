package Course4.Week2;

import java.io.*;
import java.util.*;

public class TravellingSalesmanProblem {

    public static void main(String[] args)  throws IOException{

        BufferedReader br = new BufferedReader(new FileReader("tsp.txt"));
        int n = Integer.parseInt(br.readLine())-1;

        double x[] = new double[n];
        double y[] = new double[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        double sourcex = Double.parseDouble(st.nextToken());
        double sourcey = Double.parseDouble(st.nextToken());

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Double.parseDouble(st.nextToken());
            y[i] = Double.parseDouble(st.nextToken());
        }

        double dist[][] = new double[n][n];

        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                if(i != j)
                    dist[i][j] = calculateDist(x[i],y[i],x[j],y[j]);

        double dp[][] = new double[(1<<n)][n];

        for(int current=1; current<(1<<n); current++) {
            if((current & (current-1)) == 0 ) {
                int i = 0;
                while(((1<<i) & current) == 0) i++;
                dp[current][i] = calculateDist(sourcex,sourcey,x[i],y[i]);

            }
            else {
                int i = 0;
                while((1<<i) <= current) {
                    if(((1<<i) & current) != 0) {
                        int j = 0;
                        double res = -1;
                        while((1<<j) <= current) {
                            if(((1<<j) & current)!= 0 && i != j) {
                                if(res == -1)
                                    res = dp[current^(1<<i)][j] + dist[j][i];
                                else
                                    res = Math.min(res, dp[current^(1<<i)][j] + dist[j][i]);
                            }
                            j++;
                        }
                        dp[current][i] = res;
                    }
                    i++;
                }
            }
        }
        double answer = Double.MAX_VALUE;

        for(int i=0; i<n; i++) {
            answer = Math.min(answer, dp[(1<<n)-1][i] + calculateDist(sourcex,sourcey,x[i],y[i]));
        }

        System.out.println(answer);
    }

    static double calculateDist(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }

}
