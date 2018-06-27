package Course3.Week3;

import java.io.*;
import java.util.*;

public class Huffman {

    public static void main(String[] args)  throws  IOException{
        BufferedReader br = new BufferedReader(new FileReader("huffman.txt"));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Vertex> queue = new PriorityQueue<>(new Comparator<Vertex>(){
            public int compare(Vertex n1, Vertex n2) {
                if(n1.w < n2.w)
                    return -1;
                else if(n1.w > n2.w)
                    return 1;
                else return 0;
            }
        });

        for(int i=0; i<n; i++) {
            int w = Integer.parseInt(br.readLine());
            queue.add(new Vertex(w,0,0));
        }

        while(queue.size() > 1) {
            Vertex i = queue.remove();
            Vertex j = queue.remove();
            queue.add(new Vertex(i.w + j.w, Math.max(i.max, j.max) + 1,Math.min(i.min, j.min) + 1));
        }
        System.out.println("Answer:");
        System.out.println(queue.peek().max);
        System.out.println(queue.peek().min);

    }

    static class Vertex {
        long w;
        int max, min;
        Vertex(long w, int max, int min) {
            this.w = w;
            this.max = max;
            this.min = min;
        }
    }

}
