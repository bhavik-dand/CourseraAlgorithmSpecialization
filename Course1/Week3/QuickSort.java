package Course1.Week3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class QuickSort {
    public static void main(String[] args) throws IOException{
        FileReader file = new FileReader("QuickSort.txt");
        BufferedReader br = new BufferedReader(file);
        int n = 10000;
        int[] array = new int[n];
        int i=0;
        String str;
        while((str=br.readLine())!=null){
            array[i] = Integer.parseInt(str);
            i++;
        }
        System.out.println(array.length);

        long count = quickSort(array,0,n-1);
        System.out.println(count);
        for(i=0;i<n;i++){
            System.out.println(array[i]);
        }
    }

    private static long quickSort(int[] array, int low, int high) {
        long count = 0;
        if(low<high){
            //int p = partitionUsingFirstElement(array,low,high); 
            //int p = partitionUsingLastElement(array,low,high);  
            int p = partitionUsingMedianMethod(array,low,high); 
			
            count+=quickSort(array,low,p-1);
            count+=quickSort(array,p+1,high);
        }
        return count + high - low;
    }

    private static int partitionUsingMedianMethod(int[] array, int low, int high) {
        int mid = (high - low )/2;
        if((array[mid]> array[low]) && (array[high] > array[mid]) ||
                (array[mid]> array[high] ) && (array[low] > array[mid])){ //we choose mid as pivot
            int temp = array[low];
            array[low] = array[mid];
            array[mid] = temp;

            return partitionUsingFirstElement(array,low,high);
        }
        else if((array[low]>array[mid] && array[low]<array[high] ) ||
                 array[low]>array[high] && array[low]<array[mid]){ //we choose low as pivot
            return partitionUsingFirstElement(array,low,high);
        }
        else{
            return partitionUsingLastElement(array,low,high);
        }
    }

    private static int partitionUsingLastElement(int[] array, int low, int high) {
        int pivot = array[high],i = low-1,temp;
        for(int j = low; j < high; j++){
            if(array[j] < pivot){
                i++;
                temp = array[j];
                array[j] = array[i];
                array[i] = temp;
                }
        }
        temp = array[high];
        array[high] = array[i+1];
        array[i+1] = temp;

        return i+1;

    }

    private static int partitionUsingFirstElement(int[] array, int low, int high) {
        int pivot = array[low], i=low+1,temp;
        for(int j = low+1; j<=high; j++ ){
            if(array[j] < pivot){
                temp = array[j];
                array[j] = array[i];
                array[i] = temp;
                i++;
            }
        }
        temp = array[low];
        array[low] = array[i-1];
        array[i-1] = temp;

        return i-1;
    }

}
