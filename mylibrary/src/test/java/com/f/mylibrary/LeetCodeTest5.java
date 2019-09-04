package com.f.mylibrary;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class LeetCodeTest5 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }


    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null)       return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode looker = root;
        do{
            while (looker != null){
                stack.push(looker);
                looker = looker.left;
            }
            looker = stack.pop();
            res.add(looker.val);
            looker = looker.right;
        }while (!stack.empty() || looker != null);
        return res;
    }

    @Test
    public void inorderTraversal(){
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        System.out.println(inorderTraversal(root));

        root = new TreeNode(4);
        root.left = new TreeNode(5);
        root.left.right = new TreeNode(6);
        System.out.println(inorderTraversal(root));

        root = new TreeNode(4);
        root.left = new TreeNode(5);
        root.left.right = new TreeNode(6);
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(8);
        System.out.println(inorderTraversal(root));
    }


    @Test
    public void generateTreesTest(){

    }

    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int start, int end){
        List<TreeNode> list = new ArrayList<>(), left, right;
        if (start > end)    {
            list.add(null);
        }else {
            TreeNode current;
            for (int i = start; i <= end; i++) {
                left = generateTrees(start, i - 1);
                right = generateTrees(i + 1, end);
                for (TreeNode leftChild : left){
                    for (TreeNode rightChild : right){
                        current = new TreeNode(i);
                        current.left = leftChild;
                        current.right = rightChild;
                        list.add(current);
                    }
                }
            }
        }
        return list;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> wordSet = new HashSet<>(wordDict);
        int l = s.length();
        boolean dp[] = new boolean[l + 1];
        dp[0] = true;
        for (int i = 1;i <= l; i ++){
            for (int j = 0; j < i; j ++){
                if (dp[j] && wordSet.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[l];
    }



    @Test
    public void wordBreakTest(){
        List<String> dict = Arrays.asList("leet", "code");
        System.out.println(wordBreak("leetcode", dict));
    }


    public List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> lists = new ArrayList<>();
        levelOrderImpl(root, 0, lists);
        return lists;
    }

    private void levelOrderImpl(TreeNode root, int level, List<List<Integer>> lists){
        if (root == null)  {
            return;
        }
        if (level == lists.size()){
            lists.add(new ArrayList<Integer>());
        }
        lists.get(level).add(root.val);
        levelOrderImpl(root.left, level + 1, lists);
        levelOrderImpl(root.right, level + 1, lists);
    }

    @Test
    public void levelOrderTest(){
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(5);
        root.left.right = new TreeNode(6);
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(8);

        for (List<Integer> list : levelOrder(root)){
            System.out.println(list);
        }

        for (List<Integer> list : zigzagLevelOrder(root)){
            System.out.println(list);
        }

        root = new TreeNode(4);
        root.left = new TreeNode(5);
        root.left.right = new TreeNode(6);

        for (List<Integer> list : levelOrder(root)){
            System.out.println(list);
        }
    }


    public List<List<Integer>> zigzagLevelOrder(TreeNode root){
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null)   return lists;
        TreeNode temp ;
        Stack<TreeNode> forward = new Stack<>(), backward = new Stack<>(), current = forward;
        forward.push(root);
        while (!current.empty()){
            List<Integer> list = new ArrayList<>();
            while (!current.empty()){
                temp = current.pop();
                if (current == forward){
                    if (temp.left != null)  backward.push(temp.left);
                    if (temp.right != null) backward.push(temp.right);
                }else {
                    if (temp.right != null) forward.push(temp.right);
                    if (temp.left != null)  forward.push(temp.left);
                }
                list.add(temp.val);
            }
            lists.add(list);
            current = current == forward? backward : forward;
        }
        return lists;
    }

    @Test
    public void zigzagLevelOrderTest(){
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(5);
        root.left.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(8);
        root.right.right = new TreeNode(9);

        for (List<Integer> list : zigzagLevelOrder(root)){
            System.out.println(list);
        }

        root = new TreeNode(4);
        root.left = new TreeNode(5);
        root.left.right = new TreeNode(6);
        root.right = new TreeNode(1);

        for (List<Integer> list : zigzagLevelOrder(root)){
            System.out.println(list);
        }
    }


    @Test
    public void verifyBinaryTreeTest(){
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(6);
//        root.right.left.right = new TreeNode(30);
        root.right.right = new TreeNode(20);
        System.out.println(isValidBST2(root));
    }

    private boolean isValidBST(TreeNode root){
        List<Integer> seq = new ArrayList<>();
        midIndex(root, seq);
        int pre , current, n = seq.size();
        for (int i = 1; i < n; i ++){
            current = seq.get(i);
            pre = seq.get(i - 1);
            if (current < pre)  return false;
        }
        return true;
    }

    private void midIndex(TreeNode root, List<Integer> sequence){
        if (root == null)   return;
        midIndex(root.left, sequence);
        sequence.add(root.val);
        midIndex(root.right, sequence);
    }

    private boolean isValidBST2(TreeNode root){
        if (root == null)   return true;
        int last = 0;
        boolean notInited = true;
        TreeNode probe = root, current;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.empty() || probe != null){
            while (probe != null){
                stack.push(probe);
                probe = probe.left;
            }
            current = stack.pop();
            if (notInited) {
                last = current.val;
                notInited = false;
            }else {
                if (current.val <= last)    return false;
                last = current.val;
            }
            probe = current.right;
        }
        return true;
    }

    @Test
    public void test(){
        System.out.println(type("AaAAAA"));
        System.out.println(type("AAAAAA"));
        System.out.println(type("AaAaAa"));
        System.out.println(type("AAa"));
    }

    private int type(String words){
        int n = words.length();
        char[] chars = words.toCharArray();
        int cur = 0, total = 0;
        boolean Up = false;
        for (char ch : chars){
            if (same(ch, Up)) {
                total ++;
                cur = 0;
            }
            else {
                cur ++;
                if (cur == 1) {
                    total += 2;
                }else {
                    total += 1;
                    Up = !Up;
                    cur = 0;
                }
            }
        }
        return total;
    }

    private boolean same(char c, boolean Up){
        if (c <= 'z' && c >= 'a')   return !Up;
        else                        return Up;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0, preorder.length, 0, preorder.length);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd){
        if (preStart > preEnd) return null;
        if (preStart == preEnd)  return new TreeNode(preorder[preStart]);
        int rootVal = preorder[preStart], pos, left;
        for (pos = inStart; pos <= inEnd; pos ++){
            if (rootVal == inorder[pos]) break;
        }
        left = pos - inStart;
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(preorder, inorder, preStart + 1, preStart + left, inStart, pos - 1);
        root.right = buildTree(preorder, inorder, preStart + left + 1, preEnd, pos + 1, inEnd);
        return root;
    }

    @Test
    public void levelOrderBottomTest(){
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(6);
//        root.right.left.right = new TreeNode(30);
        root.right.right = new TreeNode(20);

        for (List<Integer> list : levelOrderBottom(root)){
            System.out.println(list);
        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> lists = new LinkedList<>();
        levelOrderBottom(root, 1, lists);
        return lists;
    }

    private void levelOrderBottom(TreeNode root, int layer, LinkedList<List<Integer>> lists){
        if (root == null)   return;
        int size = lists.size();
        if (layer > size)   lists.addFirst( new ArrayList<Integer>());
        lists.get(lists.size() - layer).add(root.val);
        levelOrderBottom(root.left, layer + 1, lists);
        levelOrderBottom(root.right, layer + 1, lists);
    }


    public int sumNumbers(TreeNode root) {
        if (root == null)   return 0;
        else return sumNumbers(root, 0);
    }

    private int sumNumbers(TreeNode node, int cur){
        cur = cur * 10 + node.val;
        if (node.left == null && node.right == null){
            return cur;
        }
        if (node.left == null){
            return sumNumbers(node.right, cur);
        }else if (node.right == null){
            return sumNumbers(node.left, cur);
        }else {
            return sumNumbers(node.left, cur) + sumNumbers(node.right, cur);
        }
    }

}
