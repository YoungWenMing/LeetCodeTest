package com.f.mylibrary;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class LeetCodeTest9 {

//    224 基本计算器
    public int calculate(String s) {
//        Stack<Character> operators = new Stack<>();
//        Stack<Integer>   numbers = new Stack<>();
//        int parenthese = 0, pos = 0, length = s.length(), cur;
//        char c;
//        while (pos < length){
//            c = s.charAt(pos);
//            if (c <= '9' && c >= '0'){
//                int i = pos + 1;
//                c = s.charAt(i);
//                while (i < length && (c <= '9' && c >= '0')){
//                    c = s.charAt(i);
//                    i ++;
//                }
//                cur = Integer.parseInt(s.substring(pos, i));
//                if (numbers.empty() || )    numbers.push(cur);
//                else {
//                    char k = operators.pop();
//                    if (k == '+')   numbers.push(numbers.pop() + cur);
//                    else            numbers.push(numbers.pop() - cur);
//                }
//                pos = i;
//            }else if (c == '+' || c == '-'){
//                operators.push(c);
//            }
//        }
        return 0;
    }

    @Test
    public void calculateTest(){
        String expr = "1+( 3 -5)";

    }

    public int totalComs(int n, int W, int[] v){
        Arrays.sort(v);
        int coms[] = new int[n + 1], pos, temp;
        coms[0] = 1;
        coms[1] = v[0] <= W? 2 : 1;
        for(int i = 1; i < n; i ++){
            temp = v[i] + v[i - 1];
            if(temp <= W){
                coms[i + 1] = coms[i] << 1;
            }else{
                pos = findPos(v, W - v[i]);
                coms[i + 1] = pos == -1 ? coms[i] : coms[i] + coms[pos];
            }
            v[i] = temp;
        }
        return coms[n];
    }

    private int findPos(int[] v, int target){
        if (target == 0)   return 0;
        else if (target < 0)    return -1;
        int i = 0;
        for( ; i < v.length; i ++){
            if(target < v[i])    break;
        }
        return i ;
    }

    @Test
    public void totalComsTest(){
        int[] v = new int[]{3,2,5};
        System.out.println(totalComs(3, 5, v));
    }



    public int maxLayers2(int N, Rect[] Rects){
        Arrays.sort(Rects);
        int layers[] = new int[N], cmp, temp, lay;
        Arrays.fill(layers, 1);
        Rect cur, t;
        for (int i = 0; i < N; i++){
            cur = Rects[i];
            temp = layers[i];
            for (int j = i + 1; j < N; j ++){
                t = Rects[j];
                cmp = cur.compareTo(t);
                if (cmp < 0 && layers[j] <= temp){
                    layers[j] = temp + 1;
                }
            }
        }
        int max = 0;
        for (int i : layers){
            if (max < i){
                max = i;
            }
        }
        return max;
    }

    class Rect implements Comparable<Rect>{
        int len, wid;

        public Rect (int len, int wid){
            this.len = len;
            this.wid = wid;
        }

        @Override
        public int compareTo(Rect o) {
            if (len == o.len && wid == o.wid)   return 0;
            if (len <= o.len && wid <= o.wid)   return -1;
            if (len >= o.len && wid >= o.wid)   return 1;
            if (len <= o.len)   return -1;
            else                return 1;
        }

        @Override
        public String toString() {
            return len + " * " + wid;
        }
    }

    public int maxLayers(int N, Rect[] Rects){
        int dp[] = new int[N], width[] = new int[N], length = 1, pos;
        Arrays.sort(Rects);
        for (int i = 0; i < N; i ++){
            width[i] = Rects[i].wid;
        }
        dp[0] = width[0];
        for (int i = 1; i < N; i ++){
            if (dp[length - 1] == width[i]){
                pos = length;
            }else {
                pos = Arrays.binarySearch(dp, 0, length, width[i]);
                if (pos < 0){
                    pos = -(pos + 1);
                }
            }
            dp[pos] = width[i];
            if (pos == length){
                length ++;
            }
        }

        return length;
    }

    @Test
    public void maxLayerTest(){
        Rect[] Rects = new Rect[6];
        Rects[0] = new Rect(2,2);
        Rects[1] = new Rect(2,2);
        Rects[2] = new Rect(2, 2);
        Rects[3] = new Rect(2,2);
        Rects[4] = new Rect(2, 2);
        Rects[5] = new Rect(2, 2);


        Arrays.sort(Rects);
        System.out.println(Arrays.toString(Rects));

        System.out.println(maxLayers(6, Rects));
    }


    public int maxSumSubSequence(int[] seq, int m){
        int n = seq.length, pre[] = new int[n], cur[] = new int[n], temp[];
        pre[0] = seq[0] < 0 ? 0 : seq[0];
        for (int i = 1; i < n; i ++){
            pre[i] = pre[i - 1] < 0? seq[i] : pre[i - 1] + seq[i];
            if (pre[i] < 0)     pre[i] = 0;
        }
        int max;
        for (int i = 1; i < m; i ++){
            temp = pre;
            pre = cur;
            cur = temp;

            max = cur[i - 1];
            pre[i] = max + seq[i];
            for (int j = i + 1; j < n; j ++){
                if (cur[j - 1] > max)   max = cur[j - 1];
                pre[j] = Math.max(pre[j - 1] + seq[j], max + seq[j]);
            }
        }

        max = 0;
        for (int k : pre){
            if (max < k)    max = k;
        }
        return max;
    }

    @Test
    public void maxSumTest(){
        int[] sums = new int[]{1,2,3,-2,3,10,3};
        System.out.println(maxSumSubSequence(sums, 0));
    }
}
