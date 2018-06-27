package Course3.Week1;

import java.io.*;
import java.util.*;

public class GreedyApproach {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Job jobs[] = new Job[n];

        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            jobs[i] = new Job(w,l);
        }
        System.out.println(differenceApproach(n,jobs));
        System.out.println(ratioApproach(n,jobs));
    }

    static long ratioApproach(int n, Job a[]) {
        Arrays.sort(a,new Comparator<Job>(){
            public int compare(Job j1,Job j2) {
                double i = (double)j1.weight/j1.length;
                double j = (double)j2.weight/j2.length;
                if(i > j)
                    return -1;
                else if(i < j)
                    return 1;
                else return 0;
            }
        });
        long ans = 0;
        long time = 0;
        for(int i=0; i<n; i++) {
            time += a[i].length;
            ans += a[i].weight*time;
        }
        return ans;
    }

    static long differenceApproach(int n, Job a[]) {
        Arrays.sort(a,new Comparator<Job>(){
            public int compare(Job i,Job j) {
                if(i.weight - i.length != j.weight - j.length)
                    return (j.weight - j.length) - (i.weight - i.length);
                else
                    return j.weight - i.weight;
            }
        });
        long ans = 0;
        long time = 0;
        for(int i=0; i<n; i++)
        {
            time += a[i].length;
            ans += a[i].weight*time;
        }
        return ans;
    }

    static class Job{
        int weight, length;
        Job(int weight,int length) {
            this.weight = weight;
            this.length = length;
        }
    }
}
