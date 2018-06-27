package Course1.Week1;

import java.util.Scanner;

public class KaratsubaMultiplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next();
        String s2 = sc.next();
        int n = s1.length();
        String ans = multiply(s1,s2,n);
        System.out.println(ans);
        //System.out.println(subtract("40","80"));
    }

    private static String multiply(String s1, String s2, int n) {

        int n1 = s1.length();
        int n2 = s2.length();
        if(n==0){
            return 0+"";
        }

        if(n==1){
            return multiplySingleDigit(s1,s2);
        }


        String a = s1.substring(0,(int)Math.floor(n/2));
        String b = s1.substring((int)Math.ceil(n/2),n);
        String c = s2.substring(0,(int)Math.floor(n/2));
        String d = s2.substring((int)Math.ceil(n/2),n);
        //System.out.println(a+" "+b+" "+c+" "+d);

        String step1 = multiply(a,c,(int)Math.floor(n/2));
        String step2 = multiply(b,d,(int)Math.ceil(n/2));
        String step3 = multiply(add(a,b),add(c,d),(int)Math.floor(n/2));
        String step4 = subtract(step3,add(step1,step2));

        for(int i=0;i<((int)Math.ceil(n/2))*2;i++){
            step1 = step1.concat("0");
        }
        for(int i=0;i<(int)Math.ceil(n/2);i++){
            step4 = step4.concat("0");
        }
        return add(step1,add(step2,step4));
    }

    private static String subtract(String a, String b) {
        int aLen = a.length();
        int bLen = b.length();
        //System.out.println("Subtract "+a+" "+b+" ");
        int c = 0;
        String ans = "";
        int i,j;
        for(i=aLen-1, j=bLen-1;i>=0 && j>=0;i--,j--){
            int temp = Character.getNumericValue(a.charAt(i)) - Character.getNumericValue(b.charAt(j)) - c;
            c = 0;
            if(temp < 0){
                temp = temp + 10;
                c = 1;
            }
            ans = temp + ans;
        }

        while(i>=0){
            int temp = Character.getNumericValue(a.charAt(i)) - c;
            c=0;
            if(temp < 0){
                temp = temp + 10;
                c = 1;
            }
            ans = temp + ans;
            i--;
        }

        while(j>=0){
            int temp = Character.getNumericValue(b.charAt(j)) - c;
            c=0;
            if(temp < 0){
                temp = temp + 10;
                c = 1;
            }
            ans = temp + ans;
            j--;
        }

        //System.out.println(a+"-"+b+"="+ans);
        return ans;
    }

    private static String add(String a,String b){
        int aLen = a.length();
        int bLen = b.length();
        int c = 0;
        String ans = "";
        int i,j;
        for(i = aLen-1, j = bLen-1; i>=0 && j>=0 ; i--,j--){
            int temp = Character.getNumericValue(a.charAt(i)) + Character.getNumericValue(b.charAt(j)) + c;
            c = 0;
            if(temp>=10){
                c = 1;
                temp = temp - 10;
            }
            ans = temp + ans;
        }

        while(i>=0){
            int temp = Character.getNumericValue(a.charAt(i))+c;
            c = 0;
            if(temp>=10){
                c = 1;
                temp = temp - 10;
            }
            ans = temp + ans;
            i--;
        }

        while(j>=0){
            int temp = Character.getNumericValue(b.charAt(j))+c;
            c = 0;
            if(temp>=10){
                c = 1;
                temp = temp - 10;
            }
            ans = temp + ans;
            j--;
        }

        if(c==1){
            ans = "1" + ans;
        }
        //System.out.println(a+"+"+b+"="+ans);
        return ans;

    }

    private static String multiplySingleDigit(String s1, String s2) {
        int i = Integer.parseInt(s1);
        int j = Integer.parseInt(s2);
        int ans = i*j;
        //System.out.println(s1+"*"+s2+"="+ans);
        return ans+"";
    }
}
