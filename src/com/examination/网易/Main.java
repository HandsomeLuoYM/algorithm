package com.examination.网易;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String paths = sc.nextLine();
        int result = Integer.parseInt(sc.nextLine());
        paths = paths.substring(1, paths.length() - 1);
        String[] path = paths.split(",");
        int[] treeNode = new int[path.length];
        for (int i = 0; i < path.length; i++) {
            if (path[i].equals( "null")) {
                treeNode[i] = -1;
                continue;
            }
            treeNode[i] = Integer.parseInt(path[i]);
        }
        dfs(treeNode, 0, result, 0);
        if (list.size() == 0) {
            System.out.println(Arrays.toString(new int[]{}));
            return;
        }
        int[] rs = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            rs[i] = list.get(list.size() - i - 1);
        }
        System.out.println(Arrays.toString(rs));
    }

    private static List<Integer> list = new ArrayList<>();
    private static boolean dfs(int[] treeNode, int i, int result, int now) {
        if (i >= treeNode.length || treeNode[i] == -1) {
            return false;
        }
        if (now + treeNode[i] == result) {
            list.add(treeNode[i]);
            return true;
        }else if (now + treeNode[i] < result){
            now += treeNode[i];
            if (dfs(treeNode, i * 2 + 1, result, now) || dfs(treeNode, i * 2 + 2, result, now)) {
                list.add(treeNode[i]);
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
}