package Course3.Week3;

import java.io.*;
import java.util.*;

public class MWIS {

    public static void main(String[] args)  throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("mwis.txt"));
        int n = Integer.parseInt(br.readLine());
        long a[] = new long[n+1];
        long dp[] = new long[n+1];
        for(int i=1; i<=n; i++)
            a[i] = Integer.parseInt(br.readLine());
        dp[1] = a[1];
        for(int i=2; i<=n; i++)
            dp[i] = Math.max(dp[i-1], a[i] + dp[i-2]);
        int mwis[] = new int[n+1];
        int i = n;
        while(i >= 1) {
            if(i == 1) {
                mwis[i] = 1;
                break;
            }
            if(dp[i-1] > dp[i-2]+ a[i])
                i--;
            else {
                mwis[i] = 1;
                i = i-2;
            }
        }
        int b[] = new int[]{1, 2, 3, 4, 17, 117, 517, 997};
        for(int k:b)
            System.out.print(mwis[k]);
    }

}
