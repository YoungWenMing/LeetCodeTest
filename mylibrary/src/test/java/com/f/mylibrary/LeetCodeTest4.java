package com.f.mylibrary;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class LeetCodeTest4 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        @Override
        public String toString() {
            ListNode node = this;
            String s  = "";
            while (node != null) {
                s += node.val + " ";
                node = node.next;
            }
            return s;
        }
    }

    @Test
    public void deleteDuplicateEasyTest(){
        ListNode node = new ListNode(1);
        node.next = new ListNode(3);
        ListNode t = node.next;
        t.next = new ListNode(3);
        t = t.next;
        t.next = new ListNode(4);

        ListNode res = deleteDuplicateEasy(node);
        System.out.println(res);
    }

    private ListNode deleteDuplicateEasy(ListNode head){
        if (head == null)   return null;
        ListNode current = head.next, pre = head;
        int value = head.val;
        while (current != null){
            if (value == current.val){
                current = current.next;
                pre.next = current;
            }else {
                current = current.next;
                pre = pre.next;
                value = pre.val;
            }
        }
        return head;
    }

    @Test
    public void partitionTest(){
        ListNode node = new ListNode(1);
        node.next = new ListNode(4);
        ListNode t = node.next;
        t.next = new ListNode(3);
        t = t.next;
        t.next = new ListNode(3);
        t = t.next;
        t.next = new ListNode(2);
        t = t.next;
        t.next = new ListNode(5);
        t = t.next;
        t.next = new ListNode(1);

        ListNode k = partition(node, 3);
        System.out.println(k);
    }

    private ListNode partition(ListNode head, int x) {
        ListNode back = new ListNode(0), front = new ListNode(0),
                fP = front, bP = head, pre = back;
        back.next = head;
        while (bP != null){
            if (bP.val < x){
                fP.next = bP;
                fP = fP.next;
                bP = bP.next;
                pre.next = bP;
            }else {
                bP = bP.next;
                pre = pre.next;
            }
        }
        fP.next = back.next;
        return front.next;
    }

    @Test
    public void deleteDuplicateTest(){
        ListNode node = new ListNode(1);
        node.next = new ListNode(3);
        ListNode t = node.next;
        t.next = new ListNode(3);
        t = t.next;
        t.next = new ListNode(4);

        System.out.println(node);

        System.out.println(deleteDuplicate(node));
        System.out.println(deleteDuplicate(t));

        t = new ListNode(1);
        t.next = node;

        node = new ListNode(1);
        node.next = new ListNode(3);
        t = node.next;
        t.next = new ListNode(3);
        t = t.next;
        t.next = new ListNode(4);

        System.out.println(node);
        t = new ListNode(1);
        t.next = node;
        System.out.println(t);
        System.out.println(deleteDuplicate(t));


        node = new ListNode(1);
        node.next = new ListNode(1);
        System.out.println(deleteDuplicate(node));
    }

    private ListNode deleteDuplicate(ListNode head){
        ListNode preHead = new ListNode(0), pre = preHead;
        preHead.next = head;
        int value = head.val;
        int count = 0;
        while (head != null){
            if (value == head.val){
                head = head.next;
                count ++;
                if (head == null && count > 1)  pre.next = null;
            }else if (count == 1){
                pre = pre.next;
                value = head.val;
                head = head.next;
            }else {
                pre.next = head;
                value = head.val;
                count = 0;
            }
        }

        return preHead.next;
    }

    @Test
    public void numDecodingsTest(){
        System.out.println(numDecodings("1"));
        System.out.println(numDecodings("11"));
        System.out.println(numDecodings("111"));
        System.out.println(numDecodings("226"));
        System.out.println(numDecodings("1134"));

        System.out.println(numDecodings("00"));
        System.out.println(numDecodings("01"));
        System.out.println(numDecodings("103"));
        System.out.println(numDecodings("99"));
        System.out.println(numDecodings("99099"));
    }

    private int numDecodings(String s){
        int m;
        if ((m = s.length()) == 0 || s.charAt(0) == '0') return 0;
        int dp[] = new int[m], combine;
        dp[0] = 1;
        for (int i = 1; i < m; i ++){
            combine = (s.charAt(i - 1) - '0') * 10 + s.charAt(i) - '0';
            if (9 < combine && combine < 27)   dp[i] = i > 1? dp[i - 2] : 1;
            dp[i] += s.charAt(i) == '0'? 0 : dp[i - 1];
        }
        return dp[m - 1];
    }

    @Test
    public void minimumTotalTest(){
        List<Integer> zero = new ArrayList<>();
        zero.add(2);

        List<Integer> one = new ArrayList<>();
        one.add(3);
        one.add(4);

        List<Integer> two = new ArrayList<>();
        two.add(6);
        two.add(5);
        two.add(7);

        List<Integer> three = new ArrayList<>();
        three.add(4);
        three.add(1);
        three.add(8);
        three.add(3);

        List<List<Integer>> lists = new ArrayList<>();
        lists.add(zero);
        lists.add(one);
        lists.add(two);
        lists.add(three);

        System.out.println(minimumTotal(lists));
    }

    private int minimumTotal(List<List<Integer>> triangle){
        int n = triangle.size(), dp[] = new int[n], aux[] = new int[n], t, temp[];
        dp[0] = triangle.get(0).get(0);
        List<Integer> list;
        for (int i = 1; i < n; i ++){
            list = triangle.get(i);
            aux[0] = dp[0] + list.get(0);
            for (int j = 0; j < i; j ++){
                t = dp[j] + list.get(j);
                aux[j] = t > aux[j]? aux[j] : t;
                aux[j + 1] = dp[j] + list.get(j + 1);
            }
            temp = aux;
            aux = dp;
            dp = temp;
        }
        int min = dp[0];
        for (int i = 1; i < n; i ++)
            min = dp[i] >= min ? min : dp[i];
        return min;
    }


    @Test
    public void grayCodeTest(){
        System.out.println(grayCode(0));
        System.out.println(grayCode(1));
        System.out.println(grayCode(2));
        System.out.println(grayCode(3));
        System.out.println(grayCode(4));
    }

    private List<Integer> grayCode(int n){
        int total = 1 << n, t = 1, s;
        ArrayList<Integer>  res = new ArrayList<>(total);
        res.add(0);
        for (int i = 0; i < n; i ++){
            s = res.size();
            for (int j = s - 1; j > -1; j --){
                res.add(res.get(j) + t);
            }
            t = t << 1;
        }
        return res;
    }

    @Test
    public void subsetsWithDupTest(){
        int[] nums  =  new int[]{4,4,4,1,4};
        for (List<Integer> list : subsetsWithDup(nums))
            System.out.println(list);
    }


    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        HashSet<List<Integer>> sets = new HashSet<>();
        LinkedList<List<Integer>> temp = new LinkedList<>();
        List<Integer> listTemp;
        sets.add(new ArrayList<Integer>());
        int n = nums.length, t;
        for (int i = 0; i < n; i ++){
            t = nums[i];
            for (List<Integer> list : sets){
                listTemp = new ArrayList<Integer>(list);
                listTemp.add(t);
                temp.add(listTemp);
            }
            sets.addAll(temp);
            temp.clear();
        }
        temp.addAll(sets);
        return temp;
    }

    @Test
    public void reverseBetweenTest(){
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        ListNode t = node.next;
        t.next = new ListNode(3);
        t = t.next;
        t.next = new ListNode(4);
        t = t.next;
        t.next = new ListNode(5);
        t = t.next;
        t.next = new ListNode(6);

        System.out.println(reverseBetween(node, 1,6));

    }

    private ListNode reverseBetween(ListNode head, int m, int n){
        if (m == n) return head;
        ListNode neck = head, preNeck = new ListNode(0), waist, t, hair = preNeck;
        preNeck.next = neck;
        for (int i = 1; i < m; i ++){
            preNeck = neck;
            neck = neck.next;
        }
        waist = neck.next;
        for (int i = m; i < n; i ++){
            t = waist.next;
            waist.next = neck;
            neck = waist;
            waist = t;
        }
        preNeck.next.next = waist;
        preNeck.next = neck;
        return hair.next;
    }



    @Test
    public void restoreIpAddresses(){
        for (String s : restoreIpAddresses("25525511135"))
            System.out.println(s);
        for (String s : restoreIpAddresses("0000"))
            System.out.println(s);

        for (String s : restoreIpAddresses("0000"))
            System.out.println(s);
        for (String s : restoreIpAddresses("010010"))
            System.out.println(s);
    }

    private List<String> restoreIpAddresses(String s){
        if (s == null || s.length() < 4)  return new ArrayList<>();
        List<String> list = new ArrayList<>();
        restoreIpAddresses(s, new StringBuilder(), list, 0, 4);
        return list;
    }

    private void restoreIpAddresses(String s, StringBuilder builder, List<String> res, int start, int a){
        int l = s.length();
        if (a == 0){
            if (start == l)     res.add(builder.substring(0, builder.length() - 1));
            else                    return;
        }else {
            int t = (l - start) / a;
            if (t == 0 || t > 3)    return;
            String temp;
            for (int i = 1; (i <= 3) && (start + i <= l); i ++){
                temp = s.substring(start, start + i);
                if (Integer.parseInt(temp) > 255 || (temp.charAt(0) == '0' && i > 1))   return;
                builder.append(temp);
                builder.append('.');
                restoreIpAddresses(s, builder, res, start + i, a - 1);
                builder.delete(builder.length() - i - 1, builder.length());
            }
        }
    }
}
