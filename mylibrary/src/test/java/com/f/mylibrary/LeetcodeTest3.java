package com.f.mylibrary;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class LeetcodeTest3 {

    @Test
    public void linkedListRotateTest() {
        ListNode head = new ListNode(1), tail = head;
        for (int i = 2; i <= 5; i++) {
            tail.next = new ListNode(i);
            tail = tail.next;
        }
        tail = head;
        while (tail != null) {
            System.out.print(tail.val + "  ");
            tail = tail.next;
        }

        System.out.println();

        tail = rotateRight(head, 8);

        while (tail != null) {
            System.out.print(tail.val + "  ");
            tail = tail.next;
        }

        System.out.println();

        head = new ListNode(3);
        tail = rotateRight(head, 1);

        while (tail != null) {
            System.out.print(tail.val + "  ");
            tail = tail.next;
        }
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    private ListNode rotateRight(ListNode head, int k) {
        if (head == null)   return null;
        int length = 0, steps;
        ListNode temp = head, ahead, tail;
        while (temp != null){
            length ++;
            temp = temp.next;
        }
        steps = k % length;
        if (steps != 0){
            temp = head;
            for (int i = 0; i < length - steps - 1; i ++)
                temp = temp.next;
            // cut
            ahead = temp.next;
            temp.next = null;
            tail = ahead;
            for (int i = 0; i < steps - 1; i ++)
                tail = tail.next;

            tail.next = head;
            head = ahead;

        }
        return head;
    }

    @Test
    public void simplifyPathTest(){
        String path = "/a/./c/d////../b/";
        String[] dirs = path.split("/");
        for (String s : dirs) {
            if (s.equals("")) System.out.println("empty");
            else    System.out.println(s);
        }

        System.out.println(simplifyPath(path));

        path = "/a/../../b/../c//.//";
        System.out.println(simplifyPath(path));

        path = "/a//b////c/d//././/..";
        System.out.println(simplifyPath(path));
    }

    private String simplifyPath(String path){
        String[] dirs = path.split("/");
        Deque<String> deque = new LinkedList<String>();
        for (String dir : dirs){
            if (dir.equals("") || dir.equals("."))     continue;
            if (dir.equals("..") ) {
                if ( ! deque.isEmpty()) deque.removeLast();
            }else {
                deque.addLast(dir);
            }
        }
        if (deque.isEmpty())    return "/";
        StringBuilder builder = new StringBuilder();
        for (String s : deque) {
            builder.append('/');
            builder.append(s);
        }
        return builder.toString();
    }


    @Test
    public void resetZeroTest(){
        int[][] matrix = { new int[]{0,1,2,0},
                            new int[]{3,4,5,2},
                            new int[]{1,3,1,5}};

        for (int i = 0; i < matrix.length; i ++)
            System.out.println(Arrays.toString(matrix[i]));

        setZeroes2(matrix);


        for (int i = 0; i < matrix.length; i ++)
            System.out.println(Arrays.toString(matrix[i]));



        matrix = new int[][]{ new int[]{1,1,1},
                        new int[]{1,0,1},
                        new int[]{1,1,1}};

        for (int i = 0; i < matrix.length; i ++)
            System.out.println(Arrays.toString(matrix[i]));

        setZeroes2(matrix);


        for (int i = 0; i < matrix.length; i ++)
            System.out.println(Arrays.toString(matrix[i]));

        matrix = new int[][]{ new int[]{1,1,1},
                new int[]{0,1,2}};

        for (int i = 0; i < matrix.length; i ++)
            System.out.println(Arrays.toString(matrix[i]));

        setZeroes2(matrix);


        for (int i = 0; i < matrix.length; i ++)
            System.out.println(Arrays.toString(matrix[i]));

    }

    private void setZeroes(int[][] matrix){
        int m = matrix.length, n = matrix[0].length, temp[];
        boolean[] row = new boolean[m], col = new boolean[n];
        for (int i = 0; i < m; i ++){
            temp = matrix[i];
            for (int j = 0; j < n; j ++){
                if (temp[j] == 0)   {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        for (int i = 0; i < m; i ++){
            temp = matrix[i];
            for (int j = 0; j < n; j ++){
                if (row[i])     temp[j] = 0;
                else if (col[j])    temp[j] = 0;
            }
        }
    }


    private void setZeroes2(int[][] matrix){
        int m = matrix.length, n = matrix[0].length, temp[], firstRow[] = matrix[0];
        boolean headRowZero =false, headColZero = false;

        for (int i = 0; i < m; i ++){
            temp = matrix[i];
            for (int j = 0; j < n; j ++){
                if (temp[j] == 0)   {
                    if (! headRowZero && i == 0)    headRowZero =true;
                    if (! headColZero && j == 0)    headColZero =true;
                    temp[0] = 0;
                    firstRow[j] = 0;
                }
            }
        }

        for (int i = m - 1; i > 0; i --){
            temp = matrix[i];
            boolean firstZero = temp[0] == 0;
            for (int j = 1; j < n; j ++){
                if (firstZero)     temp[j] = 0;
                else if (firstRow[j] == 0)    temp[j] = 0;
            }
        }
        if (headColZero){
            for (int i = 0 ;i < m; i ++)
                matrix[i][0] = 0;
        }
        if (headRowZero){
            for (int i = 0; i < n; i ++)
                matrix[0][i] = 0;
        }
    }


    @Test
    public void searchMatrixTest(){

        int[][] matrix = {
                new int[]{1,  3,   5,  7},
                new int[]{10, 11, 16, 20},
                new int[]{23, 30, 34, 50}};

        for (int i = 0; i < matrix.length; i ++)
            System.out.println(Arrays.toString(matrix[i]));

        System.out.println(searchMatrix(matrix, 11));
        System.out.println(searchMatrix(matrix, 20));
        System.out.println(searchMatrix(matrix, 1));

        System.out.println(searchMatrix(matrix, 33));

    }

    private boolean searchMatrix(int[][] matrix, int target){
        int m = matrix.length, n = matrix[0].length, left = 0, right = m - 1, mid, t;
        while (left < right){
            mid = (left + right) / 2;
            t = matrix[mid][n - 1];
            if (target == t)    return true;
            else if (target > t)    left = mid + 1;
            else                    right = mid;
        }
        int row = right;
        left = 0;
        right = n - 1;
        while (left <= right){
            mid = (left + right) / 2;
            t = matrix[row][mid];
            if (target == t)    return true;
            else if (target > t)    left = mid + 1;
            else                    right = mid - 1;
        }
        return false;
    }

    @Test
    public void sortColorTest(){
        int[] colors = new int[]{0,1,0,2,2,1,0,1,2};
        System.out.println(Arrays.toString(colors));

        sortColor(colors);

        System.out.println(Arrays.toString(colors));

        colors = new int[]{0, 0, 0, 0, 2, 2, 2};
        System.out.println(Arrays.toString(colors));
        sortColor(colors);
        System.out.println(Arrays.toString(colors));

        colors = new int[]{1,1,1,1,1};
        System.out.println(Arrays.toString(colors));
        sortColor(colors);
        System.out.println(Arrays.toString(colors));


        colors = new int[]{2,2,2,2,2};
        System.out.println(Arrays.toString(colors));
        sortColor(colors);
        System.out.println(Arrays.toString(colors));

        colors = new int[]{0, 0, 0, 0, 0};
        System.out.println(Arrays.toString(colors));
        sortColor(colors);
        System.out.println(Arrays.toString(colors));

        colors = new int[]{2,0,2,1,1,0};
        System.out.println(Arrays.toString(colors));
        sortColor(colors);
        System.out.println(Arrays.toString(colors));

        colors = new int[]{2,0,1};
        System.out.println(Arrays.toString(colors));
        sortColor(colors);
        System.out.println(Arrays.toString(colors));
    }

    private void sortColor(int[] colors){
        int m;
        if ((m = colors.length) == 0)   return;
        int left = 0, right = m - 1, probe , t;

        while (left < m && colors[left] == 0)   left ++;
        while (right > 0 && colors[right] == 2)  right --;
        probe = left;
        while (probe <= right){
            if (colors[probe] == 2){
                colors[probe] = colors[right];
                colors[right--] = 2;
            }else if (colors[probe] == 0){
                colors[probe++] = colors[left];
                colors[left++] = 0;
            }else {
                probe ++;
            }
        }
    }

    @Test
    public void combineTest(){
        List<List<Integer>> list = combine(4,2);
        for (List l : list)
            System.out.println(l);

        list = combine(4,4);
        for (List l : list)
            System.out.println(l);

        list = combine(4,0);
        for (List l : list)
            System.out.println(l);

        list = combine(1,1);
        for (List l : list)
            System.out.println(l);
    }

    private List<List<Integer>> combine(int n, int k){
        List<Integer> current = new ArrayList<>(k);
        List<List<Integer>> res = new LinkedList<>();
        combine(n, k, 1, current, res);
        return res;
    }

    private void combine(int n, int k, int start, List<Integer> current, List<List<Integer>> res){
        if (k == 0) {
            res.add(new ArrayList<Integer>( current));
            return;
        }
        for (int i = start; i <= n - k + 1; i ++){
            current.add(i);
            combine(n, k - 1, i + 1, current, res);
            current.remove(current.size() - 1);
        }
    }

    @Test
    public void wordExistinMatrix(){
        char[][] board = new char[][]{
                new char[]{'A','B','C','E'},
                new char[]{'S','F','C','S'},
                new char[]{'A','D','E','E'}
        };
        System.out.println(exist(board, "ASAD"));

        System.out.println(exist(board, "ASADECC"));

        System.out.println(exist(board, "ABAD"));


    }

    private boolean exist(char[][] board, String target){
        int m;
        if ((m = board.length) == 0)    return false;
        int n = board[0].length;
        char first = target.charAt(0);
        boolean[][] track = new boolean[m][n];
        for (int i = 0 ; i < m; i ++){
            for (int j = 0; j < n; j ++){
                if (board[i][j] == first && exist(board, target, i, j, 0, track))
                    return true;
            }
        }
        return false;
    }

    private boolean exist(char[][] board, String target, int row, int col, int pos, boolean[][] track){
        if (pos == target.length())     return true;

        int m = board.length, n = board[0].length;
        char goal = target.charAt(pos);
        // all these requirements must be satisfied before valid process
        if (row < 0 || row >= m || col < 0 || col >= n || track[row][col] || board[row][col] != goal)  return false;

        track[row][col] = true;
        if (exist(board, target, row - 1, col, pos + 1, track))  return true;
        if (exist(board, target, row, col - 1, pos + 1, track))  return true;
        if (exist(board, target, row + 1, col, pos + 1, track))  return true;
        if (exist(board, target, row , col + 1, pos + 1, track))  return true;
        //if any of four neighbors meets the standard, this path is invalid, erase the trajectory
        track[row][col] =false;
        return false;
    }


    @Test
    public void removeDuplicateTest(){
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        System.out.println(removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));

        nums = new int[]{0,0,0,1,4,4,5,5, 6, 6, 6, 6};
        System.out.println(removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));

        nums = new int[]{1};
        System.out.println(removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }

    public int removeDuplicates(int[] nums) {
        int m = nums.length;
        if (m == 0)     return 0;
        int rightBound = 1, currentNum = nums[0];
        boolean twice = true;
        for (int i = 1; i < m; i ++){
            if (currentNum == nums[i] && twice) {
                nums[rightBound++] = currentNum;
                twice = false;
            }else if (currentNum != nums[i]){
                currentNum = nums[i];
                nums[rightBound++] = currentNum;
                twice = true;
            }
        }
        return rightBound;
    }

    @Test
    public void searchInRotatedArrayTest(){
        int[] nums = new int[]{2,5,6,0,0,1,2};
        System.out.println(search(nums, 6, 0, nums.length - 1));
        System.out.println(search(nums, 7, 0, nums.length - 1));
        System.out.println(search(nums, 0, 0, nums.length - 1));
        System.out.println(search(nums, 3, 0, nums.length - 1));
        System.out.println(search(nums, -1, 0, nums.length - 1));

        nums = new int[]{3,1,1};
        System.out.println(search(nums, 3, 0, nums.length - 1));

        nums = new int[]{1,1,3,1};
        System.out.println(search(nums, 3, 0, nums.length - 1));
    }

    private boolean search(int[] nums, int target, int left, int right){
        int n = nums.length;
        if (n == 0 || left > right)     return false;
        int mid = (left + right) / 2;
        if (nums[mid] == target)    return true;
        if (nums[left] < nums[right]){
            if (nums[mid] > target)     return search(nums, target, left, mid - 1);
            else                        return search(nums, target, mid + 1, right);
        }else {
            return search(nums, target, left, mid - 1) || search(nums, target, mid + 1, right);
        }
    }
}
