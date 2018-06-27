package Course3.Week4;

import java.io.*;
import java.util.*;

public class Knapsack2 {
    static int capacity,n, weight[];
    static long value[];
    static HashMap<Pair,Long> hashMap;
    public static void main(String[] args)  throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("knapsack2.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        capacity = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        weight = new int[n+1];
        value = new long[n+1];
        hashMap = new HashMap<Pair,Long>();
        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            value[i] = Integer.parseInt(st.nextToken());
            weight[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(recurse(n,capacity));
    }

    public static long recurse(int i, int j) {
        if(i == 0 || j == 0)
            return 0L;
        Pair curr = new Pair(i,j);
        if(hashMap.containsKey(curr))
            return hashMap.get(curr);
        long answer = -1;
        if(j < weight[i])
            answer = recurse(i-1,j);
        else
            answer = Math.max(recurse(i-1, j), value[i] + recurse(i-1,j- weight[i]));
        hashMap.put(curr, answer);
        return answer;
    }

    static class Pair{
        int i,j;
        Pair(int i,int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public int hashCode() {
            final int prime = 1000000007;
            long product = i*j;
            int result =(int)( product % prime);
            return result;
        }

        @Override
        public boolean equals(final Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            final Pair other = (Pair) obj;
            if(other.hashCode() != this.hashCode())
                return false;
            if(other.i != this.i)
                return false;
            if(other.j != this.j)
                return false;
            return true;
        }
    }
}
