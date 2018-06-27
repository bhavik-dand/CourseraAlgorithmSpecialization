package Course1.Week2;

import java.io.*;

public class CountInversions {
    public static void main(String[] args) throws Exception{
        FileReader file = new FileReader("IntegerArray.txt");
        BufferedReader br = new BufferedReader(file);
        int[] array = new int[100000];
        int i=0;
        String str;
        while((str=br.readLine())!=null){
            array[i] = Integer.parseInt(str);
            i++;
        }
        int[] temp = new int[array.length];
        long count  = countInversionsAndMerge(array,temp,0,array.length-1);
        System.out.println(count);


    }

    private static long countInversionsAndMerge(int[] array, int[] temp, int l, int r) {
        long countInvs = 0;
        if(l < r){
            int mid = (l+r)/2;
            countInvs = countInversionsAndMerge(array,temp, l, mid);
            countInvs += countInversionsAndMerge(array,temp, mid+1, r);
            countInvs += merge(array,temp,l,mid+1,r);
        }
        return countInvs;
    }

    private static long merge(int[] array, int[] temp, int l, int mid, int r) {
        int i=l,j=mid,k=l;
        long inversions = 0;

        while( i <= (mid - 1) && j <= r){
            if(array[i] < array[j]){
                temp[k++] = array[i++];
            } else{
                temp[k++] = array[j++];
                inversions += mid - i;
            }
        }

        while (i <= (mid - 1)){
            temp[k++] = array[i++];
        }

        while (j <= r){
            temp[k++] = array[j++];
        }

        for(i = l; i<=r; i++){
            array[i] = temp[i];
        }
        return inversions;
    }
}
