package com.algorithm.august.one_two;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 * @date 2020/8/12 - 17:04
 * @describe
 */
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
