package com.examination.携程;

import com.sun.org.apache.regexp.internal.RE;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Ming
 * @date 2021/3/18 - 19:45
 * @describe
 */
class Main {
    private static int MAX = 0;
    private static String DICT = "dict";
    private static String query = "query";
    private static Map<String, Node> ROOT;
    static class Node {
        String s;
        String type;
        Map<String, Node> map = new HashMap<String, Node>();
    }

    private static void add(Map<String, Node> map, String s, String type){
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.substring(i, i + 1))) {
                map = map.get(s.substring(i, i + 1)).map;
            }else {
                Node node = new Node();
                node.s = s.substring(i, i + 1);
                if (i == s.length() - 1) node.type = type;
                map.put(s.substring(i, i + 1), node);
                map = map.get(s.substring(i, i + 1)).map;
            }
        }
    }

    public static void search(Map<String, Node> map, String query, int index, int start) {
        if (index == query.length()) return;
        String key = query.substring(index, index + 1);
        if (map.containsKey(key)) {
            if (map.get(key).type != null) {
                String output = query.substring(start, index + 1) + ":[" + map.get(key).type + "]";
                if (MAX + output.length() > 1024) {
                    System.out.println(output.substring(0, 1024 - MAX));
                    return;
                }
                MAX += output.length();
                System.out.println(output);
                map = ROOT;
                search(map, query, index + 1, index + 1);
            }else {
                search(map.get(key).map, query, index + 1, start);
            }
        }else {
            map = ROOT;
            search(map, query, index + 1, index + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        Map<String, Node> map = new HashMap<String, Node>();
        while (in.hasNextLine()) {
            String[] s = in.nextLine().split(":");
            //类型
            if (s[0].equals(DICT)) {
                for (String s1 : s[2].split(",")) {
                    add(map, s1, s[1]);
                }
            }else {
                ROOT = map;
                if (s[1].length() > 7168) s[1] = s[1].substring(0, 7168);
                //query
                search(map, s[1], 0, 0);
            }
        }
    }
}
