package com.f.mylibrary;

import android.support.annotation.MainThread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class LeetCodeTest6 {

    @Test
    public void robTest(){
        System.out.println(rob(new int[]{2, 7, 9, 3, 1}));
        System.out.println(rob(new int[]{1, 2, 3, 1}));
    }

    private int rob(int[] cash){
        int before = 0, preBefore = 0, res = 0, n = cash.length;
        for(int i = 0; i < n; i ++){
            res = Math.max(before, preBefore + cash[i]);
            preBefore = before;
            before = res;
        }
        return res;
    }

    private int rob2(int[] nums){
        int before = 0, preBefore = 0, res = 0, res1 = 0, n = nums.length;
        for(int i = 0; i < n - 1; i ++){
            res = Math.max(before, preBefore + nums[i]);
            preBefore = before;
            before = res;
        }
        before = 0;
        preBefore = 0;
        for (int i = 1; i < n ; i ++){
            res1 = Math.max(before, preBefore + nums[i]);
            preBefore = before;
            before = res1;
        }
        return Math.max(res, res1);
    }


    @Test
    public void maximalSquareTest(){
        char[][] matrix = {{'1','0','1','0','0'}, {'1','0','1','1','1'}, {'1','0','1','1','1'}};
        System.out.println(maximalSquare(matrix));

        char[][] matrix2 = {{'1','0','1','0'},{'1','0','1','1'},{'1','0','1','1'},{'1','1','1','1'}};
        maximalSquare(matrix2);
    }

    private int maximalSquare(char[][] matrix) {
        char One = '1';
        int m = matrix.length, n = matrix[0].length, dp[]= new int[n],
            t , max = 0;
        int corner = 0;
        for (int i = 0; i < m; i ++){
            corner = dp[0];
            dp[0] = (matrix[i][0] == One)? 1:0;
            for (int j = 1; j < n; j++){
                if (matrix[i][j] == One){
                    t = Math.min( Math.min(dp[j - 1], dp[j]), corner) + 1;
                    corner = dp[j];
                    dp[j] = t;
                    if (t > max){
                        max = t;
                    }
                }else {
                    dp[j] = 0;
                }
            }
            System.out.println(Arrays.toString(dp));
        }

        return max * max;
    }


    @Test
    public void nthUglyNumberTest(){
        System.out.println(nthUglyNumber(10));
    }



    public int nthUglyNumber(int n ){
        int n2 = 0, n3 = 0, n5 = 0, ugly[] = new int[n], N2, N3, N5, next;
        ugly[0] = 1;
        for (int i = 1; i < n ; i ++){
            N2 = ugly[n2] * 2;
            N3 = ugly[n3] * 3;
            N5 = ugly[n5] * 5;
            next = Math.min(Math.min(N2, N3), N5);
            if (N2 == next) n2 ++;
            if (N3 == next) n3 ++;
            if (N5 == next) n5 ++;
            ugly[i] = next;
        }
        return ugly[n - 1];
    }

    public int numSquares(int n){
        int dp[] = new int[n + 1], t;
        dp[0] = 0;
        for (int i = 1; i < n + 1; i ++){
            t = i;
            for (int j = 1; j * j <= i; j ++){
                t = Math.min(t , dp[i - j * j] + 1);
            }
            dp[i] = t;
        }
        return dp[n];
    }

    @Test
    public void numSquaresTest(){
        System.out.println(numSquares(4));
        System.out.println(numSquares(5));
        System.out.println(numSquares(6));
        System.out.println(numSquares(8));
        System.out.println(numSquares(9));
        System.out.println(numSquares(41));
        System.out.println(numSquares(29));
        System.out.println(numSquares(12));
    }

    public int lengthOfLIS(int[] nums) {
        int n = nums.length, lengths[] = new int[n + 1], pre, cur, max = 0;
        lengths[0] = 0;
        for (int i = 0; i < n ; i ++){
            cur = nums[i];
            pre = 0;
            for (int j = 0; j < i  ; j ++){
                if (cur > nums[j])     pre = pre > lengths[j + 1]? pre : lengths[j + 1];
            }
            lengths[i + 1] = pre + 1;
            if (pre + 1 > max)  max = pre + 1;
        }
        return max;
    }

    @Test
    public void lengthOfLISTest(){
        System.out.println(lengthOfLIS(new int[]{1,3,6,7,9,4,10,5,6}));
    }


    @Test
    public void maxProfitTest(){
        int[] prices = {1,2,4,2,5,7,2,4,9,0};
        System.out.println(maxProfit(prices));
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n == 0)  return 0;
        int localMin = prices[0], profit = 0, cur, start = 0, max1 =0, max2 = 0;
        int in1 = 0, in2 = 0, out1 = 0, out2 = 0;
        for(int i = 1; i < n; i ++){
            cur = prices[i];
            if (cur >= prices[i - 1]){
                profit += cur - prices[i - 1];
                if (profit > max1 || profit > max2){
                    int merge1 = max2 + cur - prices[in1];
                    int merge2 = profit + prices[out1] - prices[in2];
                    int pick = profit + Math.max(max1, max2);
                    int last = Math.max(Math.max(merge1, merge2), pick);
                    if (last == pick){
                        if (max1 >= max2){
                            in2 = in1;
                            out2 = out1;
                        }
                        in1 = start;
                        out1 = i;
                    }else if (last == merge1){
                        max1 = prices[i] - prices[in1];
                        out1 = i;
                        start = in1;
                    }else {

                    }
                }
            }
        }
        return max1 + max2;
    }


    public List<List<Integer>> generate(int numRows) {
        if (numRows == 0)   return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>(numRows);
        List<Integer> list = new ArrayList<>(), lst, cur;
        list.add(1);
        res.add(list);
        for (int i = 1; i < numRows; i ++){
            lst = res.get(i - 1);
            cur = new ArrayList<>(i + 1);
            cur.add(1);
            for (int j = 1; j < i; j ++){
                cur.add(j, lst.get(j - 1) + lst.get(j));
            }
            cur.add(1);
            res.add(cur);
        }
        return res;
    }

    @Test
    public void generateTest(){
        for (List<Integer> list : generate(10))
            System.out.println(list);
    }

    public void solveBoard(char[][] board) {
        int rows = board.length, cols;
        if (rows == 0)  return;
        cols = board[0].length;
        if (cols == 0)  return;
        int[][] checked = new int[rows][cols];
        for (int i = 0; i < cols; i ++){
            if (board[0][i] == 'O') checkDFS(board, checked, 0, i, rows, cols);
            if (rows > 1 && board[rows - 1][i] == 'O') checkDFS(board, checked, rows - 1, i, rows, cols);
        }
        for (int i = 1; i < rows - 1; i ++){
            if (board[i][0] == 'O') checkDFS(board, checked, i, 0, rows, cols);
            if (cols > 1 && board[i][cols - 1] == 'O')  checkDFS(board, checked, i, cols - 1, rows, cols);
        }
        for (int i = 0; i < rows; i ++){
            for (int j = 0; j < cols; j ++){
                if (board[i][j] == 'O' && checked[i][j] == 0)
                    board[i][j] = 'X';
            }
        }
    }

    private void checkDFS(char[][] board, int[][] checked, int row, int col, int rS, int cS){
        if (board[row][col] == 'X'){
            checked[row][col] = 2;
            return;
        }
        checked[row][col] = 1;
        if (row + 1 < rS && checked[row + 1][col] == 0)
            checkDFS(board, checked, row + 1, col, rS, cS);
        if (row - 1 > -1 && checked[row - 1][col] == 0)
            checkDFS(board, checked, row - 1, col, rS, cS);
        if (col + 1 < cS && checked[row ][col + 1] == 0)
            checkDFS(board, checked, row, col + 1, rS, cS);
        if (col - 1 > -1 && checked[row ][col - 1] == 0)
            checkDFS(board, checked, row, col - 1, rS, cS);
    }


    @Test
    public void checkBoardTest(){
        char[][] board = {new char[]{'X','X','X','X'},
                new char[]{'X','O','O','X'},
                new char[]{'X','X','O','X'},
                new char[]{'X','O','X','X'}};
        solveBoard(board);
        for (int i = 0; i < board.length; i ++){
            System.out.println(Arrays.toString(board[i]));
        }

        board = new char[][]{new char[]{'X','X','X','X'},
                new char[]{'X','O','O','X'}};
        solveBoard(board);
        for (int i = 0; i < board.length; i ++){
            System.out.println(Arrays.toString(board[i]));
        }

        board = new char[][]{new char[]{'X'},
                new char[]{'X'},
                new char[]{'X'},
                new char[]{'X'}};
        solveBoard(board);
        for (int i = 0; i < board.length; i ++){
            System.out.println(Arrays.toString(board[i]));
        }
    }


    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //first construct matching table
        int L = beginWord.length();
        HashMap<String, List<String>>  table = new HashMap<>();
        String temp;
        List<String> list;
        for (String word : wordList){
            for (int i = 0; i < L; i ++){
                temp = word.substring(0, i) + '*' + word.substring(i + 1, L);
                list = table.get(temp);
                if (list == null){
                    list = new ArrayList<>();
                    list.add(word);
                    table.put(temp, list);
                }else {
                    list.add(word);
                }
            }
        }

        //breadth first searching
        int dist = 1;
        HashSet<String> cur = new HashSet<>(), next = new HashSet<>(), t;
        cur.add(beginWord);
        while (!cur.isEmpty()){
            dist ++;
            for (String word : cur){
                for (int i = 0; i < L; i ++){
                    temp = word.substring(0, i) + '*' + word.substring(i + 1, L);
                    if (table.containsKey(temp)){
                        for (String s : table.remove(temp)){
                            if (s.equals(endWord))  return dist;
                            next.add(s);
                        }
                    }
                }
            }
            t = cur;
            cur = next;
            next = t;
            next.clear();

        }
        return 0;
    }

    @Test
    public void ladderLengthTest(){
        List<String> strs = new ArrayList(Arrays.asList("hot","dot","dog","lot","log","cog"));
        System.out.println( ladderLength("hit", "cog", strs));

        strs = new ArrayList(Arrays.asList("hot","dot","dog","lot","log"));
        System.out.println( ladderLength("hit", "cog", strs));

        strs = new ArrayList(Arrays.asList("hot","cot","cap","dap","dop","dog"));
        System.out.println( ladderLength("cat", "dog", strs));
    }


    @Test
    public void partitionTest(){
//        checkParlindrome("abb", null);
        for (List<String> list : partition("aaa"))
            System.out.println(list);
    }

    public List<List<String>> partition(String s) {
        List<List<String>> lists = new ArrayList<>();
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        List<List<Integer>> friends = new ArrayList<>(n);
        List<Integer> temp;
        for (int i = 0; i < n; i ++){
            dp[i][i] = true;
            temp = new ArrayList<>();
            temp.add(i);
            if (i < n - 1 && s.charAt(i) == s.charAt(i + 1)){
                dp[i][i + 1] = true;
                temp.add(i + 1);
            }
            friends.add(temp);
        }
        for (int i = 2; i < n ; i ++){
            for (int j = 0; j + i < n; j ++){
                if (dp[j + 1][j + i - 1] && s.charAt(j) == s.charAt(j + i)){
                    dp[j][j + i] = true;
                    friends.get(j).add(j + i);
                }
            }
        }
        assembleParlindrome(friends, 0, lists, new ArrayList<String>(), n, s);
        return lists;
    }

    private void assembleParlindrome(List<List<Integer>> friends, int cur, List<List<String>> lists, List<String> part, int n, String s){
        if (cur == n)   {
            lists.add(new ArrayList(part));
            return;
        }
        List<Integer> friend = friends.get(cur);
        for (int i : friend){
            String parlin = s.substring(cur, i + 1);
            part.add(parlin);
            assembleParlindrome(friends, i + 1, lists, part, n , s);
            part.remove(part.size() - 1);
        }
    }

}
