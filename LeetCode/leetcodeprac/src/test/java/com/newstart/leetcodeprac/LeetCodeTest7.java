package com.f.mylibrary;

import android.view.LayoutInflater;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class LeetCodeTest7 {

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(val + " : [");
            for (Node node : neighbors){
                builder.append(node.val + " ");
            }
            builder.append("]");
            return builder.toString();
        }
    }

    class ListNode {
          int val;
          ListNode next;
          ListNode(int x) {
              val = x;
              next = null;
          }

        @Override
        public String toString() {
            return val + " --> " + next;
        }
    }


    @Test
    public void cloneGraphTest(){
        Node node = new Node(), left = new Node(), right = new Node();
        node.val = 1;
        left.val = 2;
        right.val = 3;
        node.neighbors = Arrays.asList(left, right);
        left.neighbors = Arrays.asList(node);
        right.neighbors = Arrays.asList(node);

        Node res = cloneGraph(node);
        System.out.println(res);
        for (Node neigh : res.neighbors){
            System.out.println(neigh);
        }

        System.out.println(res == node);
    }

    public Node cloneGraph(Node node) {
        return cloneGraph(node, new HashMap<Node, Node>());
    }

    private Node cloneGraph(Node node, HashMap<Node, Node> map){
        Node copy = new Node(node.val, new ArrayList<Node>()), temp;
        map.put(node, copy);
        List<Node> neighbors = copy.neighbors;
        for (Node neighbor : node.neighbors){
            if (map.containsKey(neighbor))  temp = map.get(neighbor);
            else                            temp = cloneGraph(neighbor, map);
            neighbors.add(temp);
        }
        return copy;
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length, vol = 0, start , pos;
        for (int i = 0; i < n; ){
            if (gas[i] >= cost[i]){
                start = i;
                pos = (start + 1) % n;
                vol = gas[i] - cost[i];
                for (;;pos = (pos + 1) % n){
                    if (pos == start)       return start;
                    if ((vol += (gas[pos] - cost[pos])) < 0)  break;
                }
                if (pos < start)    break;
                i = pos + 1;
            }else {
                i ++;
            }
        }
        return -1;
    }

    @Test
    public void canCompleteCircuitTest(){
        int[] gas = {1,2,3,4,5}, cost = {3,4,5,1,2};
        System.out.println(canCompleteCircuit(gas, cost));
    }

    public boolean hasCycle(ListNode head) {
        if (head == null)   return false;
        ListNode p1 = head, p2 = head;
        while (p2 != null){
            p1 = p1.next;
            if (p2.next == null) {
                break;
            }
            p2 = p2.next.next;
            if (p1 == p2)   return true;
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null)   return null;
        ListNode p1 = head, p2 = head;
        int t1 = 0, t2 = 0;
        while (p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
            t1 += 1;
            t2 += 2;
            if (p1 == p2)   {
                int cycle = t2 - t1;
                p1 = p2 = head;
                while (cycle > 0){
                    p2 = p2.next;
                    cycle --;
                }
                while (p1 != p2){
                    p1 = p1.next;
                    p2 = p2.next;
                }
                return p1;
            }
        }
        return null;
    }

    public void reorderList(ListNode head) {
        if(head == null)    return;
        ListNode p1 = head , p2 = head, tail;
        while (true){
            if (p2.next == null){
                p2 = p1.next;
                p1.next = null;
                break;
            }else if (p2.next.next == null){
                p2 = p1.next.next;
                p1.next.next = null;
                break;
            }else {
                p1 = p1.next;
                p2 = p2.next.next;
            }
        }
        p1 = head.next;
        tail = head;
        Stack<ListNode> stack = new Stack<>();
        while (p2 != null){
            stack.push(p2);
            p2 = p2.next;
        }
        while (!stack.empty()){
            tail.next = stack.pop();
            tail = tail.next;
            tail.next = p1;
            p1 = p1.next;
            tail = tail.next;
        }
    }

    @Test
    public void reorderListTest(){
        ListNode node = new ListNode(0), probe = node;
        probe.next = new ListNode(1);
        probe = probe.next;
        probe.next = new ListNode(2);
        probe = probe.next;
        probe.next = new ListNode(3);
        probe = probe.next;
        probe.next = new ListNode(4);
        probe = probe.next;
        probe.next = new ListNode(5);
        probe = probe.next;
        reorderList(node);
        while (node != null){
            System.out.print(node.val + "-> ");
            node = node.next;
        }

    }


    class LRUCache {

        public LRUCache(int capacity) {
            this.capacity = capacity;
            nums = 0;
            map = new LinkedHashMap<>();
        }

        public int get(int key) {
            int value = -1;
            if (map.containsKey(key) ){
                value = map.get(key);
                map.remove(key);
                map.put(key, value);
            }
            return value;
        }

        public void put(int key, int value) {
            if (nums == capacity){
                for (Map.Entry entry : map.entrySet()){
                    map.remove(entry.getKey());
                    nums --;
                    break;
                }
            }
            if (map.containsKey(key)){
                map.remove(key);
                nums --;
            }
            map.put(key, value);
            nums ++;
        }

        private LinkedHashMap<Integer, Integer> map;

        private int nums;

        private int capacity;

    }

    @Test
    public void LRUTest(){
        LRUCache cache = new LRUCache(2);
        System.out.println(cache.get(2));
        cache.put(2,6);
        System.out.println(cache.get(1));
        cache.put(1, 5);
        cache.put(1, 2);
        System.out.println();

    }

    public ListNode insertionSortList(ListNode head) {
        if(head == null)    return null;
        ListNode hair = new ListNode(0), probe = head, temp, p;
        hair.next = head;
        while (probe.next != null){
            if (probe.next.val >= probe.val){
                probe = probe.next;
            }else {
                temp = probe.next;
                probe.next = temp.next;
                temp.next = null;
                p = hair;
                while (p.next.val < temp.val){
                    p = p.next;
                }
                temp.next = p.next;
                p.next =temp;
            }
        }
        return hair.next;
    }

    public ListNode sortList(ListNode head) {
        if (head == null)   return null;
        int STEP = 1, length = 0;
        ListNode hair = new ListNode(0), before, behind , left, right;
        ListNode[] two ;
        hair.next = head;
        while (head != null){
            head = head.next;
            length ++;
        }
        while (STEP < length){
            before = hair;
            behind = hair.next;
            while (behind != null){
                two = cutK(behind, STEP);
                left = two[0];
                two = cutK(two[1], STEP);
                right = two[0];
                behind = two[1];
                before = merge(before, left, right);
            }
            STEP *= 2;
        }
        return hair.next;
    }

    private ListNode[] cutK(ListNode behind, int step){
        if  (behind == null)    return new ListNode[]{null, null};
        ListNode top = behind, bottom;
        while (step > 1 && behind != null){
            behind = behind.next;
            step --;
        }
        if (behind != null) {
            bottom = behind;
            behind = behind.next;
            bottom.next = null;
        }
        return new ListNode[]{top, behind};
    }

    private ListNode merge(ListNode before, ListNode left, ListNode right){
        while (left != null && right != null){
            if (left.val < right.val) {
                before.next = left;
                left = left.next;
            } else {
                before.next = right;
                right = right.next;
            }
            before = before.next;
        }
        before.next = left == null ? right : left;
        while (before.next != null){
            before = before.next;
        }
        return before;
    }

    public ListNode sortListR(ListNode head){
        if (head == null || head.next == null)  return head;
        ListNode fast = head.next, slow = head;
//        split the list into halves
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode temp = slow.next;
        slow.next = null;
        ListNode left = sortListR(head), right = sortListR(temp), hair = new ListNode(0), h = hair;
        while (left != null && right != null){
            if (left.val < right.val){
                hair.next = left;
                left = left.next;
            }else {
                hair.next = right;
                right = right.next;
            }
            hair = hair.next;
        }
        hair.next = left == null? right : left;
        return h.next;
    }

    @Test
    public void ListNodeSortTest(){
        ListNode node = new ListNode(5), probe = node;
        probe.next = new ListNode(1);
        probe = probe.next;
        probe.next = new ListNode(2);
        probe = probe.next;
        probe.next = new ListNode(0);
        probe = probe.next;
        probe.next = new ListNode(4);
        probe = probe.next;
        probe.next = new ListNode(3);
        probe = probe.next;
        probe.next = new ListNode(-1);
        System.out.println(node);
        System.out.println(sortListR(node));
    }


    public int lamps(int l, String s){
        int[] nums = new int[l + 3];
        nums[0] = nums[1] = nums[2] = 0;
        for (int i = 3; i < l + 3; i ++){
            char c = s.charAt(i - 3);
            if (c == 'X')   nums[i] = nums[i - 1];
            else            nums[i] = nums[i - 3] + 1;
        }
        return nums[l + 2];
    }

    @Test
    public void lampTest(){
        System.out.println(lamps(6, "XXXXXX"));
        System.out.println(lamps(8, "...X.X.."));
    }


    static class Ring implements Comparable{

        int hour, min;

        public Ring(int h, int m){
            hour = h;
            min = m;
        }

        @Override
        public String toString() {
            return hour + " : " + min;
        }

        @Override
        public int compareTo(Object o) {
            Ring r = (Ring) o;
            if (r.hour < hour){
                return 1;
            }else if (r.hour == hour) {
                if (r.min < min)        return 1;
                if (r.min == min)       return 0;
            }
            return -1;
        }
    }

    @Test
    public void ringTest(){
        Ring[] rings = new Ring[]{new Ring(1,0), new Ring(2,10), new Ring(2, 5), new Ring(2, 5), new Ring(1, 59)};
        Arrays.sort(rings);
        System.out.println(Arrays.toString(rings));

        System.out.println(binarySearch(rings, new Ring(2, 0)));
//
//        int hour = 0, minu = 59, t = 35;
//        while (t > 0){
//            if (t > minu)  {
//                t -= minu;
//                minu = 60;
//                hour --;
//            }else {
//                minu -= t;
//                t = 0;
//            }
//        }
//        hour = hour < 0? hour + 24 : hour;
//        System.out.println(hour + " : " + minu);

    }

    private static Ring binarySearch(Ring[] rings, Ring target){
        int n = rings.length;
        int left = 0, right = n - 1, mid, cmp;
        while (left <= right){
            mid = (left + right) / 2;
            cmp = rings[mid].compareTo(target);
            if (cmp == 0)   return rings[mid];
            else if (cmp < 0)   left = mid + 1;
            else                right = mid - 1;
        }
        return rings[right];
    }

    private int lastGuy(int n){
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = n ; i > 0; i --)
            queue.addFirst(i);
        int last = n;
        while (! queue.isEmpty()){
//            queue.addLast(n);
            queue.addLast( queue.removeFirst());
            queue.addLast(queue.removeFirst());
            last = queue.removeFirst();
        }
        return last;
    }


    @Test
    public void lastTest(){
//        System.out.println(lastGuy(5));
        System.out.println(reverseDigit(-1099));
        System.out.println(Integer.MIN_VALUE);
        System.out.println(reverseDigit(Integer.MIN_VALUE));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(reverseDigit(Integer.MAX_VALUE));

    }

    public String reverseDigit(int n){
        boolean neg = n < 0;
        long l = (long) n;
        String s = Long.toString(neg? -l : l);
        StringBuilder builder = new StringBuilder();
        int len = s.length();
        for (int i = len - 1; i >= 0; i --){
            builder.append(s.charAt(i));
        }
        return neg ? '-'+ builder.toString() : builder.toString();
    }

    public static int minDist(String s1, String s2){
        String temp;
        if (s1.length() < s2.length()){
            temp = s1;
            s1 = s2;
            s2 = temp;
        }
        int n = s1.length(), m =s2.length(), dp[][] = new int[m][n], min = Integer.MAX_VALUE;
        dp[0][0] = s1.charAt(0) == s2.charAt(0) ? 0 : 1;
        for (int i = 1; i < n; i ++){
            dp[0][i] = s1.charAt(i) == s2.charAt(0) ? dp[0][i - 1] : dp[0][i - 1] + 1;
        }
        for (int i = 1; i < m; i++){
            for (int j = i; j < n; j ++){
                dp[i][j] = s1.charAt(j) == s2.charAt(i) ? dp[i - 1][j - 1] : dp[i - 1][j - 1] + 1;
            }
        }
        for (int i = m - 1; i < n; i ++){
            if (min > dp[m - 1][i])     min = dp[m - 1][i];
        }

        return min;
    }

    @Test
    public void minDistTest(){
        System.out.println(minDist("hello", "helle"));
        System.out.println(minDist("abc", "bc"));

        System.out.println(minDist("string", "hstrong"));

    }

    public int evalRPN(String[] tokens) {

        final char TIMES = '*', ADD = '+', SUB = '-', DIV = '/', z = '0', n = '9';
        char t;
        String cur;
        int m = tokens.length, o1, o2;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < m; i++){
            cur = tokens[i];
            t = cur.charAt(0);
            if ((z <= t && n >= t) || (cur.length() > 1)){
                stack.push(Integer.parseInt(cur));
            }else {
                o1 = stack.pop();
                o2 = stack.pop();
                switch (t){
                    case ADD:
                        o1 = o1 + o2;
                        break;
                    case SUB:
                        o1 = o2 - o1;
                        break;
                    case TIMES:
                        o1 = o1 * o2;
                        break;
                    case DIV:
                        o1 =  o2 / o1;
                        break;
                }
                stack.push(o1);
            }
        }
        return stack.empty() ? 0: stack.pop();
    }

    @Test
    public void evalRPNTest(){
        String[] exprs = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(evalRPN(exprs));

        String s = " I am the king and my   wife is the queen! ";
        s = s.trim();
        String[] strs = s.split("\\s+");
        System.out.println(Arrays.toString(strs));
        StringBuilder builder = new StringBuilder();
        int t = strs.length;
        builder.append(strs[ t - 1]);
        for (int i = t - 2; i > -1; i --){
            builder.append(' ');
            builder.append(strs[i]);
        }
        System.out.println(builder.toString());

    }

    public int findMin(int[] nums){
        int n = nums.length, left = 0, right = n - 1, mid, cur;
        boolean b1, b2;
        while (left < right){
            mid = (left + right) / 2;
            b1 = nums[left] > nums[mid];
            b2 = nums[right] > nums[mid];
            // in smaller sequence
            if (b1 && b2)   right = mid;
            // left is smaller or equal
            else if (b2)    right = mid;
            // right is smaller or equal
            else if (b1)    left = mid + 1;
            // in bigger sequence
            else            left = mid + 1;
        }
        return nums[right];
    }

    @Test
    public void findMinTest(){
        int[] nums = new int[]{4, 5, 6, 0, 1, 2};
        System.out.println(findMin(nums));

        nums = new int[]{4, 5, 6, 0, 1, 2, 3};
        System.out.println(findMin(nums));

        nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        System.out.println(findMin(nums));

        nums = new int[]{ 6, 0, 1, 2, 4, 5};
        System.out.println(findMin(nums));

        nums = new int[]{ 0, 1, 2, 4, 5};
        System.out.println(findMin(nums));

    }

    public int findPeakElement(int[] nums) {
        int n = nums.length, left = 0, right = n - 1, mid;
        boolean b1, b2;
        while (left < right){
            mid = (left + right) / 2;
            b1 = mid == 0 || nums[mid] > nums[mid - 1];
            b2 = mid == n - 1 || nums[mid] > nums[mid + 1];
            if (b1 && b2)   return mid;
            else if (b2)    right = mid - 1;
            else if (b1)    left = mid + 1;
            else            left = mid + 1;
        }
        return left == n? right : left;
    }


    @Test
    public void findPeakElementTest(){
        int[] nums = new int[]{4, 5, 6, 0, 1, 2};
        System.out.println(findPeakElement(nums));

        nums = new int[]{1,2,3,4};
        System.out.println(findPeakElement(nums));

        nums = new int[]{4,3,2,1};
        System.out.println(findPeakElement(nums));

        nums = new int[]{1};
        System.out.println(findPeakElement(nums));

        nums = new int[]{1, 2, 3, 1};
        System.out.println(findPeakElement(nums));

    }


    class NumMatrix {

        int[][] matrix;
        int m, n;

        public NumMatrix(int[][] matrix) {
            m = matrix.length;
            if (m == 0){
                n = 0;
            }else {
                n = matrix[0].length;
                for (int i =1; i < n; i ++)
                    matrix[0][i] += matrix[0][i - 1];
                for (int i = 1; i < m;i ++){
                    matrix[i][0] += matrix[i - 1][0];
                    for (int j = 1; j < n; j ++){
                        matrix[i][j] += matrix[i - 1][j] + matrix[i][j - 1]- matrix[i - 1][j - 1];
                    }
                }
            }
            this.matrix = matrix;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (row1 == 0 && col1 == 0)     return matrix[row2][col2];
            else if (row1 == 0)             return matrix[row2][col2] - matrix[row2][col1 - 1];
            else if (col1 == 0)             return matrix[row2][col2] - matrix[row1 - 1][col2];
            else                            return matrix[row2][col2] + matrix[row1 - 1][col1 - 1] - matrix[row2][col1 - 1] - matrix[row1 - 1][col2];
        }
    }

    @Test
    public void numMatrixTest(){
        int[][] matrix = new int[][]{
                new int[]{3, 0, 1, 4, 2},
                new int[]{5, 6, 3, 2, 1},
                new int[]{1, 2, 0, 1, 5},
                new int[]{4, 1, 0, 1, 7},
                new int[]{1, 0, 3, 0, 5}
        };
        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(2,1,4,3));
        for (int i = 0; i < matrix.length; i ++){
            System.out.println(Arrays.toString(matrix[i]));
        }

        System.out.println(numMatrix.sumRegion(1,1,2,2));
        System.out.println(numMatrix.sumRegion(1,2,2,4));
        System.out.println(numMatrix.sumRegion(0,0,2,2));
        System.out.println(numMatrix.sumRegion(1,0,3,2));
        System.out.println(numMatrix.sumRegion(0,1,3,2));
    }

//    Problem 322
    public int coinChange(int[] coins, int amount) {
        int dp[] = new int[amount + 1], min, t, c = coins.length;
//        dp[0] = 0;
//        Arrays.sort(coins);
//        for (int i = 1; i < coins[0] && i <= amount; i ++)
//            dp[i] = -1;
//        for (int i = coins[0]; i <= amount; i ++){
//            min = Integer.MAX_VALUE;
//            for (int j = 0; j < c; j ++){
//                if (i >= coins[j])  {
//                    t = dp[i - coins[j]];
//                    if (t == -1)    continue;
//                    if (t < min)    min = t;
//                }else {
//                    break;
//                }
//            }
//            if (min == Integer.MAX_VALUE)   min = -2;
//            dp[i] = min + 1;
//        }


        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i ++ ){
            for (int j = 0; j < c; j ++){
                if (coins[j] <= i){
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        return dp[amount] == amount + 1? -1 : dp[amount];
    }

    @Test
    public void coinChangeTest(){
        int[] coins = new int[]{1,2,5};
        System.out.println(coinChange(coins, 11));
        System.out.println(coinChange(coins, 13));
        System.out.println(coinChange(coins, 15));

        coins = new int[]{3, 5};
        System.out.println(coinChange(coins, 2));
    }

}
