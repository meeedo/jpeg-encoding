package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StandardCompression {
    ArrayList<Node> list;
    ArrayList<String> Codes_symbol = new ArrayList<String>();
    ArrayList<String> Codes_code = new ArrayList<String>();
    public static String table="";
    public StandardCompression(ArrayList<Node> list) {
        this.list = list;
    }
    void sortNodes()
    {

        Collections.sort(list, new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                // avoiding NullPointerException in case name is null
                Long l1 = new Long(n1.frq);
                Long l2 = new Long(n2.frq);
                return l1.compareTo(l2);
            }

        });


    }
    Node createTree()
    {
        Node parent_node = null;
        while (list.size() > 1) {

            Node left_node = list.get(0);
            Node right_node = list.get(1);

            list.remove(0);
            list.remove(0);
            parent_node = new Node("0", right_node.frq + left_node.frq );
            parent_node.left = left_node;
            parent_node.right = right_node;


            right_node.parent = parent_node;
            left_node.parent = parent_node;

            list.add(parent_node);
            sortNodes();
        }
        return parent_node;
    }
    String  createTableCodes(Node root , String s)
    {

        if(root == null)
        {
            return "";
        }
        if (root.left == null && root.right == null)
        {
            table+= String.valueOf(root.symbol)+"," + s + " ";
            Codes_symbol.add(String.valueOf(root.symbol));
            Codes_code.add(s);

        }
        createTableCodes(root.left , s+"1");
        createTableCodes(root.right , s+"0");


        return table;
    }
}
