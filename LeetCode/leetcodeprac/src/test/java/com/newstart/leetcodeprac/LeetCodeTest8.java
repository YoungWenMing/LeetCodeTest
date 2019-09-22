package com.f.mylibrary;

import android.content.ContentProvider;
import android.support.v4.app.Fragment;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class LeetCodeTest8 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
     }

    public int compareVersion(String version1, String version2) {
        String regex = "\\.", s1[] = version1.split(regex), s2[] = version2.split(regex);
        int i = 0, t1, t2, l1 = s1.length, l2 = s2.length;
        for (; i < l2 && i < l1; i ++){
            t1 = Integer.parseInt(s1[i]);
            t2 = Integer.parseInt(s2[i]);
            if (t1 < t2)        return -1;
            else if (t1 > t2)   return 1;
        }
        for (; i < l1 || i < l2 ; i ++){
            if (l1 < l2 && Integer.parseInt(s2[i]) > 0)    return -1;
            if (l1 > l2 && Integer.parseInt(s1[i]) > 0)     return 1;
        }

        return 0;
    }

    @Test
    public void compareVersionTest(){
        String v1 = "1.0.1.2.005", v2 = "1.0.1.2.1";
        String[] strs = v1.split("\\.");
        for (String s : strs) {
            System.out.println(s);
            System.out.println(Integer.parseInt(s));
        }

        System.out.println(compareVersion(v1, v2));
    }


    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0)     return "0";
        boolean neg = false;
        if (numerator * denominator < 0)    neg = true;
        if (numerator < 0)  numerator = - numerator;
        if (denominator < 0)    denominator = - denominator;

        int div, i = 0, pos = -1;
        List<Integer>   res = new ArrayList<>();
        HashMap<Integer, Integer>   map = new HashMap<>();
        while (numerator != 0){
            div = numerator / denominator;
            numerator -= div * denominator;
            res.add(div);
            if (map.containsKey(numerator)){
                pos = map.get(numerator);
                break;
            }
            map.put(numerator, i++);
            numerator *= 10;
        }
        if (res.size() == 1)    return Integer.toString(res.get(0));
        StringBuilder builder = new StringBuilder();
        if (neg)    builder.append('-');
        builder.append(res.get(0));
        builder.append(".");
        if (pos == 0)   builder.append('(');
        for (int j = 1; j < res.size(); j++){
            builder.append(res.get(j));
            if (pos == j)   builder.append('(');
        }
        if (pos != -1)  builder.append(')');
        return builder.toString();
    }

    public String fractionToDecimal2(int numerator, int denominator) {
        if (numerator == 0) return Integer.toString(0);
        StringBuilder builder = new StringBuilder();
        if ((numerator < 0) ^ (denominator < 0))    builder.append('-');
        long nu = Math.abs((long) numerator), de = Math.abs((long) denominator), div;
        div = nu / de;
        nu -= div * de;
        builder.append(div);
        if (nu == 0)    return builder.toString();
        builder.append('.');
        HashMap<Long, Integer> map = new HashMap<>();
        int i = builder.length();
        while (nu != 0){
            if (map.containsKey(nu)){
                builder.insert(map.get(nu), "(");
                builder.append(')');
                break;
            }else {
                map.put(nu, i ++);
                nu *= 10;
            }
            div = nu / de;
            nu -= div * de;
            builder.append(div);
        }
        return builder.toString();
    }

    @Test
    public void fractionToDecimalTest(){
        System.out.println(fractionToDecimal2(1, 2));
        System.out.println(fractionToDecimal2(10, 3));
        System.out.println(fractionToDecimal(1, 4));
        System.out.println(fractionToDecimal2(3, 8));
        System.out.println(fractionToDecimal2(3, 7));
        System.out.println(fractionToDecimal2(1, 6));
        System.out.println(fractionToDecimal(-50, 8));
        System.out.println(fractionToDecimal2(50, -8));
    }


    @Test
    public void maxNumTest(){
        StringComparator comparator = new StringComparator();
        System.out.println(comparator.compare("334", "3345"));
        System.out.println(comparator.compare("334", "3341"));
        String[] strings = new String[]{"3", "334", "3345", "3341"};
        System.out.println(largestNumber(new int[]{3, 334, 3345, 3341}));
        System.out.println(Arrays.toString(strings));
        System.out.println(largestNumber(new int[]{121, 12}));
    }

    class StringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            String t1 = o1 + o2, t2 = o2 + o1;
            return - t1.compareTo(t2);
        }

    }

    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] strings = new String[n];
        for(int i = 0; i < n; i ++){
            strings[i] = Integer.toString(nums[i]);
        }
        Arrays.sort(strings, new StringComparator());
        StringBuilder builder = new StringBuilder();
        for (String s : strings)
            builder.append(s);
        return builder.toString();

    }

    public List<String> findRepeatedDnaSequences(String s) {
        int n;
        if (s == null || (n = s.length()) < 11)       return new ArrayList<>();
        final int WIN = 10;
        StringBuilder builder = new StringBuilder();
        builder.append(s.substring(0, 9));
        HashMap<String, Integer>    map = new HashMap<>();
        List<String>    res = new LinkedList<>();
        String temp;
        for (int i = 9; i < n; i ++){
            builder.append(s.charAt(i));
            temp = builder.toString();
            if (map.containsKey(temp)) {
                if (map.get(temp) == 1){
                    res.add(temp);
                    map.put(temp, 2);
                }
            } else                      {
                map.put(temp, 1);
            }
            builder.deleteCharAt(0);
        }
        return res;
    }

    public List<String> findRepeatedDnaSequences2(String s) {
        int n;
        if (s == null || (n = s.length()) < 11)       return new ArrayList<>();
        HashMap<String, Boolean>    map = new HashMap<>();
        List<String>    res = new LinkedList<>();
        String temp;
        Boolean b;
        for (int i = 0; i < n - 9; i ++){
            temp = s.substring(i, i + 10);
            b = map.get(temp);
            if (b != null ) {
                if (b) {
                    res.add(temp);
                    map.put(temp, false);
                }
            } else              {
                map.put(temp, true);
            }
        }
        return res;
    }

    @Test
    public void findRepeatedTest(){
        List<String> res = findRepeatedDnaSequences2("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
        for (String s : res)
            System.out.println(s);

        res = findRepeatedDnaSequences("AAAAAAAAAAAA");
        for (String s : res)
            System.out.println(s);
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer>   res = new ArrayList<>();
        goRightDown(root, 0, res);
        return res;
    }

    private void goRightDown(TreeNode node, int count, List<Integer> list){
        if (node == null)   return ;
        count ++;
        if (count > list.size())    list.add(node.val);
        goRightDown(node.right, count, list);
        goRightDown(node.left, count, list);
    }

    @Test
    public void rightSideViewTest(){
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(5);
        for (int i : rightSideView(root))
            System.out.println(i + " -> ");
    }

    public int numIslands(char[][] grid) {
        int m, n, res = 0;
        if ((m = grid.length) ==0 ) return 0;
        if ((n = grid[0].length) == 0)  return 0;
        for (int i = 0; i < m; i ++){
            for (int j = 0; j < n; j ++){
                if (grid[i][j] == '1'){
                    res ++;
                    dfsSearch(grid, i, j, m, n);
                }
            }
        }
        return res;
    }

    public void dfsSearch(char[][] grid, int y, int x, int m, int n){
        grid[y][x] = '2';
        if (y > 0 && grid[y - 1][x] == '1')     dfsSearch(grid, y - 1, x, m, n);
        if (y < m - 1 && grid[y + 1][x] == '1') dfsSearch(grid, y + 1, x, m, n);
        if (x > 0 && grid[y][x - 1] == '1')     dfsSearch(grid, y, x - 1, m, n);
        if (x < n - 1 && grid[y][x + 1] == '1') dfsSearch(grid, y, x + 1, m, n);
    }

    @Test
    public void numsIslandTest(){
        char[][] nums = new char[][]{
                new char[]{'1','1','0','0','0'},
                new char[]{'1','1','0','0','0'},
                new char[]{'0','0','1','0','0'},
                new char[]{'1','0','0','0','0'},
                new char[]{'1','1','0','0','0'}
        };
        System.out.println(numIslands(nums));
    }

    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums, k, 0, nums.length - 1);
    }

    public int findKthLargest(int[] nums, int k, int start, int end){
        int pivot = nums[start], left = start , right = end + 1;
        while (left < right){
            while (++left <= end && pivot < nums[left])  ;
            while (--right > start && pivot >= nums[right]);
            if (left < right){
                int t = nums[left];
                nums[left] = nums[right];
                nums[right] = t;
            }
        }
        nums[start] = nums[right];
        nums[right] = pivot;
        int  index = right - start + 1;
        if (index == k) return pivot;
        else if (index < k) return findKthLargest(nums, k - index, right + 1, end);
        else                return findKthLargest(nums, k, start, right - 1);
    }

    public void quickSort(int[] nums, int start, int end) {
        if (start >= end)   return;
        int pivot = nums[start], left = start + 1, right = end;
        while (left < right) {
            while (left <= end && pivot >= nums[left]) left++;
            while (right > start && pivot < nums[right]) right--;
            if (left < right) {
                int t = nums[left];
                nums[left] = nums[right];
                nums[right] = t;
            }
        }
        nums[start] = nums[right];
        nums[right] = pivot;
        quickSort(nums, start, right - 1);
        quickSort(nums, right + 1, end);
    }

    @Test
    public void findKthLargestTest(){
        int nums[] = new int[]{1,3,4,-1, -2, 5};
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{-2, -1, 1, 3, 4, 5};
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{5, 4, 3, 0, -1, -2};
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{2,1};
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{3,2,3,1,2,4,5,5,6};
        System.out.println(findKthLargest(nums, 4));

        nums = new int[]{7,6,5,4,3,2,1};
        System.out.println(findKthLargest(nums, 5));

        nums = new int[]{3,2,1,5,6,4};
        System.out.println(findKthLargest(nums, 2));

        nums = new int[]{2,1};
        System.out.println(findKthLargest(nums, 1));

        nums = new int[]{2,1};
        System.out.println(findKthLargest(nums, 2));

        nums = new int[]{1,2};
        System.out.println(findKthLargest(nums, 1));

        nums = new int[]{1,2};
        System.out.println(findKthLargest(nums, 2));

        nums = new int[]{1};
        System.out.println(findKthLargest(nums, 1));

        nums = new int[]{2,1};
        quickSort(nums, 0, 1);
        System.out.println(Arrays.toString(nums) );

    }

    @Test
    public void canFinishTest(){
        int[][] pres = new int[][]{{0,1}, {1,2}, {2, 3}, {0, 4}};
        System.out.println(canFinish(5, pres));
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int m = prerequisites.length, pre[], pos;
        if (m == 0)  return true;
        List<Integer>[] pres = new List[numCourses];
        for (int i = 0;i < m; i ++){
            pre = prerequisites[i];
            pos = pre[0];
            if (pres[pos] == null){
                pres[pos] = new ArrayList<>();
            }
            List list = pres[pos];
            for (int j = 1; j < pre.length; j ++){
                list.add(pre[j]);
            }
        }
        int[] checked = new int[numCourses];
        for (int i = 0; i < numCourses; i ++){
            if (dfsCheckRing(i, pres, checked))
                return false;
        }
        return true;
    }

    public boolean canFinish2(int numCourses, int[][] prerequisites){
        // build indegree table
        int[] indegree = new int[numCourses];
        for (int[] pre : prerequisites){
            indegree[pre[0]] ++;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i ++){
            if (indegree[i] == 0)
                queue.add(i);
        }
        while (!queue.isEmpty()){
            int t = queue.removeFirst();
            numCourses --;
            for (int[] pre : prerequisites){
                if (pre[1] != t)    continue;
                if (--indegree[pre[0]] == 0 )
                    queue.add(pre[0]);
            }
        }
        return numCourses == 0;
    }

    private boolean dfsCheckRing(int pos, List<Integer>[] pres, boolean[] checked, boolean[] inPath){
        if (inPath[pos])    return true;
        if (checked[pos] || pres[pos] == null)   return false;
        checked[pos] = true;
        inPath[pos] = true;
        for (int i :pres[pos]){
            if (dfsCheckRing(i, pres, checked, inPath))
                return true;
        }
        inPath[pos] = false;
        return false;
    }

    private boolean dfsCheckRing(int pos, List<Integer>[] pres, int[] checked){
        if (checked[pos] == 1)     return true;
        if (checked[pos] == -1 || pres[pos] == null)     return false;
        checked[pos] = 1;
        for (int i : pres[pos]){
            if (dfsCheckRing(i, pres, checked))
                return true;
        }
        checked[pos] = -1;
        return false;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] nexts = new List[numCourses];
        int indegrees[] = new int[numCourses], t, pos = 0, res[] = new int[numCourses];
        for (int[] pre : prerequisites){
            indegrees[pre[0]] ++;
            t = pre[1];
            if (nexts[t] == null)  nexts[t] = new ArrayList<>();
            nexts[t].add(pre[0]);
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i ++){
            if (indegrees[i] == 0)  queue.add(i);
        }
        // delete node -- reduce their indegree
        while (! queue.isEmpty()){
            t = queue.removeFirst();
            res[pos++] = t;
            if (nexts[t] != null){
                for (int i : nexts[t]){
                    indegrees[i] --;
                    if (indegrees[i] == 0)
                        queue.add(i);
                }
            }
        }
        if (pos < numCourses)   return new int[0];
        return res;
    }

    @Test
    public void findOrderTest(){
        int[][] pres = new int[][]{{0,1}, {1,2}, {2, 3}, {0, 4}};
        System.out.println(Arrays.toString(findOrder(5, pres)));

        pres = new int[][]{{0,1}, {1,2}, {2, 3}, {3,0}};
        System.out.println(Arrays.toString(findOrder(4, pres)));
    }

    class Trie {

        private Trie[] base = new Trie[26];

        private boolean isEnd = false;

        /** Initialize your data structure here. */
        public Trie() {

        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            int l = word.length();
            if (l == 0)    return;
            int pos = word.charAt(0) - 'a';
            if (base[pos] == null)  {
                base[pos] = new Trie();
            }
            if (l == 1){
                isEnd = true;
                return;
            }

            base[pos].insert(word.substring(1));
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            if (word == null || word.length() == 0 )   return true;
            int l = word.length(),pos = word.charAt(0) - 'a';
            if (l == 1)             return isEnd;
            if (base[pos] == null)  return false;
            return base[pos].search(word.substring(1));
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            if (prefix == null || prefix.length() == 0) return true;
            int pos = prefix.charAt(0) - 'a';
            if (base[pos] == null)      return false;
            return base[pos].startsWith(prefix.substring(1));
        }

    }


    @Test
    public void trieTest(){
        Trie trie = new Trie();
        trie.insert("app");
        System.out.println(trie.search("app"));

        System.out.println(trie.startsWith("ap"));
        System.out.println(trie.startsWith("app"));
        System.out.println(trie.startsWith("appp"));

        trie.insert("apple");
        System.out.println(trie.startsWith("apply"));
        System.out.println(trie.search("apple"));

    }

    public int minSubArrayLen(int s, int[] nums) {
        int left = 0, right = 0, n = nums.length, res = n + 1, sum = 0, t;
        for (; right < n; right ++){
            sum += nums[right];
            while (sum - nums[left] >= s){
                sum -= nums[left++];
            }
            t = right - left + 1;
            if (sum >= s && t < res)   res = t;
        }
        return res == n + 1? 0 : res;
    }


    @Test
    public void minSubArrayLenTest(){
        int[] nums = new int[]{2,1,4,3,5,6,8};
        System.out.println(minSubArrayLen(7, nums));

        nums = new int[]{2,3,1,2,4,2,1};
        System.out.println(minSubArrayLen(7, nums));
        nums = new int[]{1,2,3,4,5};
        System.out.println(minSubArrayLen(11, nums));

    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer>   temp = new ArrayList<>();
        combinationSum3(1, k, n, lists, temp);
        return lists;
    }

    public void combinationSum3(int start, int k, int n, List<List<Integer>> lists, List<Integer> temp){
        if (k == 0 && n == 0)   lists.add(new ArrayList<>(temp));
        if (k == 0 || n <= 0)   return;
        for (int i = start; i <= 9 - k + 1 && i <= n; i ++){
            temp.add(i);
            combinationSum3(i + 1, k - 1, n - i, lists, temp);
            temp.remove(temp.size() - 1);
        }

    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        int t;
        for(int i = 0; i < nums.length; i ++){
            t = nums[i];
            if(set.size() > k)     set.remove(nums[i - k - 1]);
            if(set.contains(t))     return true;
            set.add(t);
        }
        return false;
    }

    @Test
    public void combinationSum3Test(){
//        for (List<Integer> list : combinationSum3(3, 7))
//            System.out.println(list);
//
//        for (List<Integer> list : combinationSum3(3, 9))
//            System.out.println(list);
//
//        for (List<Integer> list : combinationSum3(4, 12))
//            System.out.println(list);

        System.out.println(containsNearbyDuplicate(new int[]{1,2,3,1,2,3}, 2));
        System.out.println(containsNearbyDuplicate(new int[]{1,1}, 2));
    }

}
