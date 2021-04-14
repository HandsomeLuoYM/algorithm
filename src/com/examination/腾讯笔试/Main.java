package com.examination.腾讯笔试;

import java.util.*;

/**
 * @author Ming
 * @date 2021/4/4 - 19:58
 * @describe
 */
public class Main {

    public static void main(String[] args) {
        List<Integer> rs = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Integer t = Integer.parseInt(sc.nextLine());
        for (int k = 0; k < t; k++) {
            int number = Integer.parseInt(sc.nextLine());
            String[] dataStrings = sc.nextLine().split(" ");
            int[] data = new int[dataStrings.length];
            for (int i = 0; i < dataStrings.length; i++) {
                data[i] = Integer.parseInt(dataStrings[i]);
            }
            rs.add(calc(data));
        }
        for (Integer r : rs) {
            System.out.println(r);
        }
    }

    private static int calc(int[] data) {
        Arrays.sort(data);
        int right = data.length - 1;
        int rs = 0;
        while (right > 2) {
            rs += data[right] + data[0] + data[1] + data[1];
            right -= 2;
        }
        if (right == 2) {
            rs += data[0] + data[1] + data[2];
        }
        if (right == 1) {
            rs += data[1];
        }
        if (right == 0) {
            rs += data[0];
        }
        return rs;
    }

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer n = Integer.parseInt(sc.nextLine());
        String line = sc.nextLine();
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(line.charAt(0) - '0');
        int length = line.length();
        for(int i = 1; i < length; i++) {
            if (!stack.isEmpty() && stack.peek() + line.charAt(i) - '0' == 10) {
                stack.pop();
            }else {
                stack.push(line.charAt(i) - '0');
            }
        }
        System.out.println(stack.size());
    }

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main5(String[] args) {
        Main main = new Main();
        TreeNode root = main.new TreeNode(1);
        TreeNode root1 = main.new TreeNode(2);
        TreeNode root2 = main.new TreeNode(3);
        TreeNode root3 = main.new TreeNode(4);
        TreeNode root4 = main.new TreeNode(5);
        TreeNode root5 = main.new TreeNode(6);
        TreeNode root6 = main.new TreeNode(7);
        TreeNode root7 = main.new TreeNode(8);
        root.left = root1;
        root.right = root2;
        root1.left = root3;
        root1.right = root4;
        root2.left = root5;
        root2.right = root6;
        root3.left = root7;
        TreeNode rs = main.solve(root);

        System.out.println(rs);
    }
    public TreeNode solve (TreeNode root) {
        if (root == null) return root;
        // write code here
        int number = 1;
        TreeNode node;
        List<TreeNode> list = new ArrayList<TreeNode>(), newList = new ArrayList<TreeNode>();
        list.add(root);
        while (list.size() > 0) {
            number = list.size();
            for(int i = 0; i < number; i++) {
                node = list.get(i);
                if (node.right != null) newList.add(node.right);
                if (node.left != null) newList.add(node.left);
            }
            number = number * 2;
            if (newList.size() != number && newList.size() != 0) {
                for (TreeNode treeNode : list) {
                    treeNode.left = null;
                    treeNode.right = null;
                }
                return root;
            }
            list = newList;
            newList = new ArrayList<TreeNode>();
        }
        return root;
    }
}
