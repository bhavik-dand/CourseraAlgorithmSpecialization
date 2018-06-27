package Course2.Week4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class TwoSum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("hash.txt"));

        int n =  10^6;
        boolean counter[] = new boolean[2 * 10^4 + 1];//creating a counter array

        HashSet<Long> hashSet = new HashSet<>();//creating a hashset

        for(int i=0;i<n;i++){ //logic
            long num = Long.parseLong(br.readLine());

            for(int j=-10000;j <= 10000;j++ ){
                if(num == j/2)
                    continue;
                if(hashSet.contains(j-num))
                    counter[j+10000] = true;
            }
            hashSet.add(num);
        }

        int answer = 0;
        for(int i=0; i<=20000; i++){
            if(counter[i])
                answer++;
        }

        System.out.println(answer);
    }
}
