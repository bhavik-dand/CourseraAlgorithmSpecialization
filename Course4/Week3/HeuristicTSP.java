package Course4.Week3;

import java.io.*;
import java.util.*;

public class HeuristicTSP {

    public static void main(String[] args)  throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("nn.txt"));
        int n = Integer.parseInt(br.readLine());

        double x[] = new double[n];
        double y[] = new double[n];

        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            x[i] = Double.parseDouble(st.nextToken());
            y[i] = Double.parseDouble(st.nextToken());
        }

        HashSet<Integer> hashSet = new HashSet<>();

        for(int i=1; i<n; i++) {
            hashSet.add(i);
        }


        int current = 0;
        double answer = 0;
        while(hashSet.size() > 0) {
            Iterator<Integer> ir = hashSet.iterator();
            double min = Double.MAX_VALUE;
            int mini = -1;

            while(ir.hasNext()) {
                int j = ir.next();
                double dist = calculateDistance(x[current],y[current],x[j],y[j]);
                if(dist < min) {
                    min = dist;
                    mini = j;
                }
                else if(dist == min) {
                    mini = Math.min(mini, j);
                }
            }

            answer += min;
            hashSet.remove(mini);
            current = mini;

        }

        answer += calculateDistance(x[current],y[current],x[0],y[0]);
        System.out.println(answer);

    }
    static double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }
}