package com.company;

public class Node {
    String symbol;
    int frq;
    Node left;
    Node right;
    Node parent;

    public Node(String symbol, int frq) {
        this.symbol = symbol;
        this.frq = frq;
        left = null;
        right = null;
        parent = null;
    }
}
