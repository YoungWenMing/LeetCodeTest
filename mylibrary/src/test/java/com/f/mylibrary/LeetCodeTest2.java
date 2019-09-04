package com.f.mylibrary;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class LeetCodeTest2 {

    @Test
    public void testValidParenthese(){
        System.out.println(generateParenthesis(3));


        System.out.println(generateParenthsisD(3));
    }

    private List<String> generateParenthesis(int n) {
        boolean[] combination = new boolean[n * 2];
        StringBuilder builder = new StringBuilder();

        List<String> list = new ArrayList<>();
        generateParenthesis(2 * n, 0, n, builder, list );
        return list;
    }

    /**
     * if positive > 0, we can take right parenthesis
     * */
    private void generateParenthesis(int slots, int positive, int n, StringBuilder builder, List<String> list){
        if (positive > n || positive < 0)   return;

        if (slots == 0){
            if (positive > 0)   return;
            else                list.add(builder.toString());

        }else {
            builder.append('(');
            generateParenthesis(slots - 1, positive + 1, n, builder, list);
            builder.deleteCharAt(builder.length() - 1);

            builder.append(')');
            generateParenthesis(slots - 1, positive - 1, n, builder, list);
            builder.deleteCharAt(builder.length() - 1);
        }


    }


    private List<String> generateParenthsisD(int n){
        List<String>[] lists = new List[n + 1];
        lists[0] = new ArrayList<>(1);
        lists[0].add("");
        lists[1] = new ArrayList<>(1);
        lists[1].add("()");

        List<String> l1, l2;
        String left = "(", right = ")";

        for (int i = 2; i <= n; i ++){
            List<String> list = new LinkedList<>();
            lists[i] = list;
            for (int j = 0; j < i; j ++){
                l1 = lists[j];
                l2 = lists[i - j - 1];
                for (String s1 : l1){
                    for (String s2 : l2){
                        list.add(left + s1 + right + s2);
                    }
                }
            }
        }
        return lists[n];
    }

    @Test
    public void testMatrixRotate(){
        int[][] matrix = {{1,2,3}, {4,5,6}, {7,8,9}};

        for (int i =0; i < matrix.length; i ++){
            System.out.println(Arrays.toString(matrix[i]));
        }

        rotate(matrix);
        for (int i =0; i < matrix.length; i ++){
            System.out.println(Arrays.toString(matrix[i]));
        }

        int[][] matrix2 = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};

        for (int i =0; i < matrix2.length; i ++){
            System.out.println(Arrays.toString(matrix2[i]));
        }
        rotate(matrix2);
        for (int i =0; i < matrix2.length; i ++){
            System.out.println(Arrays.toString(matrix2[i]));
        }

        int[][] matrix3 = {{1}};
        matrix = matrix3;

        for (int i =0; i < matrix.length; i ++){
            System.out.println(Arrays.toString(matrix[i]));
        }

        rotate(matrix);
        for (int i =0; i < matrix.length; i ++){
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    private void rotate(int[][] matrix){
        int n = matrix.length, x0, y0, x, y, temp, metaTemp;
        for (int i = 0; i < n / 2; i ++){
            for (int j = i; j < n - 1 -i; j ++){
                x0 = i;
                y0 = j;
                metaTemp = matrix[x0][y0];
                for (int k = 0; k < 4; k ++){
                    x = y0;
                    y = n - 1 - x0;
                    temp = matrix[x][y];
                    matrix[x][y] = metaTemp;
                    metaTemp = temp;
                    x0 = x;
                    y0 = y;
                }
            }
        }
    }



    @Test
    public void testGroup(){
        String[] groups = {"eat", "tea", "tan", "ate", "nat", "bat"};

        for (List<String> lst : groupAnagrams(groups))
            System.out.println(lst);
    }


    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        for (String string : strs){
            builder.delete(0, builder.length());
            String key = reSort(builder, string);
            List<String> lst = map.get(key);
            if (lst != null)
                lst.add(string);
            else{
                lst = new ArrayList<>();
                lst.add(string);
                map.put(key, lst);
            }
        }
        return new ArrayList<>(map.values());
    }

    private String reSort(StringBuilder builder, String str){
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        for (char ch : chars)
            builder.append(ch);
        return builder.toString();
    }

    private String reSort(StringBuilder builder, String str, int[] count){
        Arrays.fill(count, 0);
        int n = str.length();
        for (int i = 0; i < n; i ++)
            count[str.charAt(i) - 'a'] ++;
        for (int i = 0; i < 26; i ++) {
            builder.append(count[i]);
            builder.append(',');
        }
        return builder.toString();
    }


    @Test
    public void testPow(){
//        System.out.println(Integer.toHexString(Integer.MIN_VALUE));
//        System.out.println(Integer.toHexString(- Integer.MIN_VALUE));
//        System.out.println(Integer.toHexString(1));
//        System.out.println(Integer.toHexString(-1));
//        System.out.println(Integer.toHexString( - (Integer.MIN_VALUE + 1)));
//        System.out.println(Integer.toHexString(Integer.MIN_VALUE + 1));
//        System.out.println( - (Integer.MIN_VALUE + 1));
//        System.out.println(0 - (Integer.MIN_VALUE + 1));
//        System.out.println(Math.pow(2, Integer.MIN_VALUE));


        System.out.println(myPow(2, 10));

        System.out.println(myPow(1.0, Integer.MIN_VALUE));
        System.out.println(myPow(-1.0, Integer.MIN_VALUE));
        System.out.println(myPow(2.0, Integer.MIN_VALUE));
    }

    public double myPow(double x, int n) {
        if  (x == 1.0)  return 1.0;
        if  (n == Integer.MIN_VALUE)    return myPow(x, n + 1) * x;
        boolean negative = false;
        if(n < 0){
            n = -n;
            negative = true;
        }
        double result = littlePow(x, n);
        return negative? 1 / result : result;
    }

    private double littlePow(double x , int n){
        if ( n == 0.0)  return 1.0;
        if ((n & 1) == 0)   return littlePow(x  * x, n / 2);
        else                return x * littlePow(x, n - 1);
    }


    @Test
    public void canJumpTest(){
        int[] nums = {2,3,1,1,4};
        System.out.println(canJump(nums));

        nums = new int[]{3,2,1,0,4};
        System.out.println(canJump(nums));


    }

    public boolean canJump(int[] nums) {
        int n = nums.length, current = n - 2, startYet = n - 1;
        for (; current > -1; current --){
            if (startYet == 0)  return true;
            if (current + nums[current] >= startYet)
                startYet = current;
        }
        if (startYet == 0)  return true;
        return false;
    }

    @Test
    public void intervalsSort(){
        int[][] intervals = {{1, 2}, {0, 3}, {1,3}, {0, 5}};
        Arrays.sort(intervals, new intervalComparator());
        for (int i = 0; i < intervals.length; i ++)
            System.out.println(Arrays.toString(intervals[i]));
        ArrayList<int[]> result = new ArrayList<>();
        result.add(intervals[0]);
        int i = 0, current[], target[], finalResult[][];
        for (int j = 1; j < intervals.length; j ++){
            target = result.get(i);
            current = intervals[j];
            if (current[0] > target[1])
                result.add(++i, current);
            else
                target[1] = current[1];
        }

        finalResult = new int[result.size()][];


        for (int k = 0; k < result.size(); k ++)
            finalResult[k] = result.get(k);

        for (int k = 0; k < finalResult.length; k ++)
            System.out.println(Arrays.toString(finalResult[k]));
    }

    class intervalComparator implements Comparator{
        @Override
        public int compare(Object o1, Object o2) {
            int[] t1 = (int[]) o1, t2 = (int[]) o2;
            if (t1[0] < t2[0])  return -1;
            if (t1[0] == t2[0]){
                if (t1[1] > t2[1])  return 1;
                if (t1[1] < t2[1])  return -1;
                return 0;
            }
            return 1;
        }
    }


    @Test
    public void testFindNthPermutation(){
        System.out.println( findNthPermutation(2, 1));

        System.out.println( findNthPermutation(3, 1));
        System.out.println( findNthPermutation(3, 5));

        System.out.println( findNthPermutation(4, 1));
        System.out.println( findNthPermutation(4, 7));
    }

    private String findNthPermutation(int n, int k){
        if (n == 1) return  "1";
        int[] num = new int[n], p = new int[n - 1];
        for (int i = 0; i < n; i ++)
            num[i] = i + 1;

        p[n - 2]  = 1;
        int t = 1;
        for (int i = n - 3; i > -1; i --)
            p[i] = p[i + 1] * ++t;

        int current, temp, start = 0;
        for (int index = 0; index < n -1; index ++){
            //
            current = (k - 1) / p[index];
            k = k - current * p[index];

            temp = num[current + start];
            for (int i = current + start; i > start;)
                num[i] = num[--i];
            if (current > 0)   num[start] = temp;
            if (k == 0) break;
            start ++;
        }


        String res = "";
        for (int i = 0; i < n; i ++)
            res += num[i];
        return res;
    }


    private void findNthPermutation(int k,int start,  int[] num, int[] p){
        if (k == 0 || start == num.length - 1) return;
        int current = (k - 1) / p[start];
        k = k - current * p[start];

        int temp = num[current + start];
        for (int i = current + start; i > start;)
            num[i] = num[--i];
        if (current > 0)   num[start ++] = temp;
        findNthPermutation(k , start, num, p);
    }
}
