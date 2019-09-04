package com.f.mylibrary;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class LeetCodeTest {

    @Test
    public void searchRotatedArrayTest(){
        int[] nums = new int[]{4,5,6,7,0,1,2};
        int target = 0;
        System.out.println(search(nums, target));

        nums = new int[]{4,5,6,7,0,1,2};
        target = 4;
        System.out.println(search(nums, target));

        nums = new int[]{4,5,6,7,0,1,2};
        target = 2;
        System.out.println(search(nums, target));

        nums = new int[]{4,5,6,7,0,1,2};
        target = 5;
        System.out.println(search(nums, target));

        nums = new int[]{4,5,6,7,0,1,2};
        target = 1;
        System.out.println(search(nums, target));

        nums = new int[]{4,5,6,7,0,1,2};
        target = 10;
        System.out.println(search(nums, target));

        nums = new int[]{3, 1};
        target = 1;
        System.out.println(search(nums, target));
    }


    private int search(int[] nums, int target){
        int n = nums.length, left = 0, right = n - 1, temp, mid;
        while (left <= right){
            mid = (left + right) / 2;
            temp = nums[mid];
            if (target == temp)     return mid;

            if (temp >= nums[left]){
                if (target < nums[left] || target > temp)
                    left = mid + 1;
                else
                    right = mid - 1;
            }else {
                if (target > nums[right] || target < temp)
                    right = mid - 1;
                else
                    left = mid + 1;
            }

        }
        return -1;
    }

    @Test
    public void testSoduku(){
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'},
        };

        System.out.println(isValidSoduku(board));
    }




    private boolean isValidSoduku(char[][] board){
        HashSet[] rows = new HashSet[9], cols = new HashSet[9], squs = new HashSet[9];
        for (int i= 0; i < 9; i++){
            rows[i] = new HashSet();
            cols[i] = new HashSet();
            squs[i] = new HashSet();
        }

        for (int i = 0; i < 9; i ++){
            for (int j = 0; j < 9; j ++){
                char t = board[i][j];
                if (t == '.')          continue;
                if(!rows[i].add(t))   return false;
                if(!cols[j].add(t))   return false;
                if (!squs[(i / 3) * 3 + j / 3].add(t))  return false;
            }
        }
        return true;
    }


    @Test
    public void combinationTest(){
        int[] candidates = {2,3, 6, 7};
        int target = 8;

        for (List l : combinationSum(candidates, target)){
            System.out.println(l);
        }

        candidates = new int[]{2,3, 5};
        target = 8;

        for (List l : combinationSum(candidates, target)){
            System.out.println(l);
        }

        candidates = new int[]{3, 5};
        target = 2;

        for (List l : combinationSum(candidates, target)){
            System.out.println(l);
        }
    }

    private List<List<Integer>> combinationSum(int[] candidates, int target){
        Arrays.sort(candidates);
        List<Integer> tempList = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        combination(candidates, 0, target, tempList, res);
        return res;
    }

    // we can choose any integer in the candidates array from index startPos
//    to the end
//    candidates is a sorted array without duplicate items
    private void combination(int[] candidates, int startPos, int target, List<Integer> tempList, List<List<Integer>> res){
        if (target < 0 ) return;
        if (target == 0){
            res.add(new ArrayList<Integer>(tempList));
            return;
        }

        List<List<Integer>> lists = new ArrayList<>(), temp;
        int n = candidates.length, t;
        for (int i = startPos; i < n; i ++){
            t = candidates[i];
            tempList.add(tempList.size(), t);
            combination(candidates, i, target - t, tempList, res);
            tempList.remove(tempList.size() - 1);
        }

        /*

        for (int i = startPos; i < n; i ++){
            t = candidates[i];
            if (t > target)     break;
            if (t == target){
                List<Integer> l = new ArrayList<>();
                l.add(t);
                lists.add(l);
            }else {
                temp = combination(candidates, i, target -t);
                for (List l : temp) {
                    l.add(t);
                    lists.add(l);
                }

            }
        }*/
    }



    private List<List<Integer>> combinationSum2(int[] candidates, int target){
        Arrays.sort(candidates);
        List<Integer> tempList = new ArrayList<>();
        HashSet<List<Integer>> lists = new HashSet<>();
        combination2(candidates, 0, target, tempList, lists);

        return new ArrayList<>(lists);
    }

    // we can choose any integer in the candidates array from index startPos
//    to the end
//    candidates is a sorted array without duplicate items
    private HashSet<List<Integer>> combination2(int[] candidates, int startPos, int target){
        if (startPos == candidates.length)  return new HashSet<>();
        HashSet<List<Integer>> lists = new HashSet<>(), temp;
        int n = candidates.length, t;
        for (int i = startPos; i < n; i ++){
            t = candidates[i];
            if (t > target) break;
            if (t == target){
                List<Integer> l = new ArrayList<>();
                l.add(t);
                lists.add(l);
            }else {
                temp = combination2(candidates, i + 1 , target - t);
                for (List l : temp){
                    l.add(t);
                    lists.add(l);
                }
            }
        }
        return lists;

    }

    private void combination2(int[] candidates, int startPos, int target, List<Integer> tempList, HashSet<List<Integer>> lists){
        if (target == 0){
            lists.add(new ArrayList<>(tempList));
            return;
        }
        //HashSet<List<Integer>> lists = new HashSet<>(), temp;
        int n = candidates.length, t;
        for (int i = startPos; i < n; i ++){
            t = candidates[i];
            if (t > target) break;
            tempList.add(tempList.size(), t);
            combination2(candidates, i + 1, target - t, tempList, lists);
            tempList.remove(tempList.size() - 1);

        }
        //return lists;
    }


    @Test
    public void combinationTest2(){
        int[] candidates = {10,1,2,7,6,1,5};
        int target = 8;

        for (List l : combinationSum2(candidates, target)){
            System.out.println(l);
        }

        candidates = new int[]{2,5,2,1,2};
        target = 5;

        for (List l : combinationSum2(candidates, target)){
            System.out.println(l);
        }

        candidates = new int[]{3, 3, 5,7};
        target = 10;

        for (List l : combinationSum2(candidates, target)){
            System.out.println(l);
        }

//
        System.out.println("=============");
        candidates = new int[]{3, 5,7};
        target = 10;

        for (List l : combinationSum2(candidates, target)){
            System.out.println(l);
        }
    }



    @Test
    public void permuteUniqueTest(){
        int[] nums = {1,2,3};
        System.out.println(permuteUnique(nums));

        nums = new int[]{1,2,3,4};
        System.out.println(permuteUnique(nums));
    }


    private List<List<Integer>> permuteUnique(int[] nums){
        Arrays.sort(nums);
        List<Integer> tempList = new ArrayList<>();
        HashSet<List<Integer>> res = new HashSet<>();
        permuteUnique(nums, 0, tempList, res);
        return new ArrayList<>(res);
    }


    private void permuteUnique(int[] nums, int startPos, List<Integer> tempList, HashSet<List<Integer>> res){
        if (startPos == nums.length){
            res.add(new ArrayList<Integer>(tempList));
            return;
        }
        int t;
        for (int i = startPos; i < nums.length; i ++){
            t = nums[i];
            if (i == startPos || t != nums[startPos]){
                nums[i] = nums[startPos];
                nums[startPos] = t;
                tempList.add(tempList.size(), t);
                permuteUnique(nums, startPos + 1, tempList, res);
                tempList.remove(tempList.size() - 1);
                nums[startPos] = nums[i];
                nums[i] = t;
            }
        }
    }
}
