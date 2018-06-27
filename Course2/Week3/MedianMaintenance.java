package Course2.Week3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianMaintenance {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Median.txt"));
        int n = 10000;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() { //Initialize Max Heap using PriorityQueue
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); //Initialize Min Heap using Priority Queue

        int answer = 0;

        for(int i=0; i<n; i++){
            int num = Integer.parseInt(br.readLine());
            if(i==0){
                maxHeap.add(num);
                answer+=num;
            }

            if(i==1){
                if(maxHeap.peek()>num){
                    minHeap.add(maxHeap.poll());
                    maxHeap.add(num);
                }else {
                    minHeap.add(num);
                }
                answer+= maxHeap.peek();
                continue;
            }

            int max = maxHeap.peek();
            if(num<max){
                minHeap.add(max);
                maxHeap.poll();
                maxHeap.add(num);
            }else{
                minHeap.add(num);
            }

            if(minHeap.size() - maxHeap.size() > 0)
                maxHeap.add(minHeap.poll());

            answer += maxHeap.peek();
            answer %= 10000;
        }

        System.out.println(answer);
    }
}
