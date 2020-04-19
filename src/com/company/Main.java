package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Node> listdata = new ArrayList<Node>();
    public static ArrayList<String> Codes_symbol = new ArrayList<String>();
    public static ArrayList<String> Codes_code = new ArrayList<String>();
    public static ArrayList<String> list = new ArrayList<String>();
    public static ArrayList<String> list1 = new ArrayList<String>();
    public static ArrayList<ArrayList<String>> hufman = new ArrayList<ArrayList<String>>();
/*
    public static void main(String[] args) throws IOException {

        String data = read_from_file();
        String[] separate = data.split(",");
        ArrayList<String> returnn = zeros(separate);

        getFrequency(returnn);
        StandardCompression standard = new StandardCompression(listdata);
        standard.sortNodes();
        Node root = standard.createTree();
        writeinFile1(standard.createTableCodes(root, ""));
         Codes_symbol= standard.Codes_symbol;
         Codes_code = standard.Codes_code;

        writeinFile(compress());
        writeinFile2(decompress());

    }
*/
    public static String compress()
    {
        String total = "";
        for (int i=0; i<list.size(); i++)
        {

            int x = Codes_symbol.indexOf(list.get(i));
            total += Codes_code.get(x) + "," + connert_to_binary(list1.get(i)) + " ";
        }


        return total;
    }

    public static String decompress() throws FileNotFoundException {
        String total ="";
        String dec = read_from_file1();
        String compress = read_from_file2();

        String[] decArr = dec.split(" ");
        String[] compressArr = compress.split(" ");

        for(int i=0; i<compressArr.length;i++)
        {
            String[] a = compressArr[i].split(",");
            String x = getcode(a[0],decArr);
            if(!x.equals("EOB")){
            char s = x.charAt(0);
            int d =Integer.parseInt(String.valueOf(s));
            for (int j=0; j<d; j++)
            {
                total += "0";
            }
            if(!a[1].equals("*")){
            total += convert_to_decimal(a[1]);}}
            else
            {
                total += "EOB";
            }


        }
return total;
    }
    public static ArrayList<String> zeros(String[] t)
    {
        String temp="";
        int counter=0;
        for (int i = 0 ; i < t.length ; i++)
        {
            if (t[i].equals("0"))
            {
                counter++;
            }
            else if ((!t[i].equals("0"))&&(!t[i].equals("EOB")))
            {
                temp+= counter + "/" +connert_to_binary(t[i]).length();
                list.add(temp);
                list1.add(t[i]);
                temp="";
                counter=0;
            }
            else
                {
                    list1.add(t[i]);
                    list.add("EOB");
            break;
            }
            }


        return list;
    }

    public static String connert_to_binary(String c)
    {
     String binary="";
        if(c.charAt(0) == '-'){
            int decimal = ~Integer.parseInt(connert_to_binary(c.substring(1)),2);
            String out = Integer.toBinaryString(decimal);
            return out.substring(out.indexOf("0", 0));
        }
        if (c.equals("EOB"))
        {
            return "*";
        }
        else{
            return Integer.toBinaryString(Integer.parseInt(c));
        }
    }
    public static String convert_to_decimal(String bin){
        if(bin.charAt(0) == '0'){
            String out ="";
            for (int i = 0 ; i< bin.length() ;++i){
                if(bin.charAt(i) == '0')    out+="1";
                else    out+="0";
            }
            return "-"+Integer.parseInt(out, 2);
        }
        else{
            return Integer.parseInt(bin,2)+"";
        }
             }


     static String read_from_file() throws FileNotFoundException {
        File file = new File("C:\\Users\\ahmedelsayed\\Desktop\\input.txt");
        String total = "";
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            total += sc.nextLine();
        }
        return total;
    }
    static String read_from_file1() throws FileNotFoundException {
        File file = new File("C:\\Users\\ahmedelsayed\\Desktop\\dec.txt");
        String total = "";
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            total += sc.nextLine();
        }
        return total;
    }
    static String read_from_file2() throws FileNotFoundException {
        File file = new File("C:\\Users\\ahmedelsayed\\Desktop\\compress.txt");
        String total = "";
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            total += sc.nextLine();
        }
        return total;
    }

    public static void writeinFile1(String content) throws IOException {
        File file = new File("C:\\Users\\ahmedelsayed\\Desktop\\dec.txt");
        FileWriter fr = new FileWriter(file, true);
        fr.write(content);
        fr.write("\n");
        fr.close();
    }

    public static void writeinFile(String content) throws IOException {
        File file = new File("C:\\Users\\ahmedelsayed\\Desktop\\compress.txt");
        FileWriter fr = new FileWriter(file, true);
        fr.write(content);
        fr.write("\n");
        fr.close();
    }

    public static void writeinFile2(String content) throws IOException {
        File file = new File("C:\\Users\\ahmedelsayed\\Desktop\\original.txt");
        FileWriter fr = new FileWriter(file, true);
        fr.write(content);
        fr.write("\n");
        fr.close();
    }

    public static void getFrequency(ArrayList<String> str)
    {
        String total = "";
        String c;
        int x = 0;
        for(int i=0; i<str.size(); i++)
        {
            c = str.get(i);
            for(int j=0; j<str.size(); j++)
            {
                if(c.equals(str.get(j))){
                    x++;
                }
            }
            if(!total.contains(c)) {
                total+= c;
                listdata.add(new Node(c, x));
            }
            x = 0;
        }

    }
    public static String getcode(String code , String[] arr)
    {
        String x="";
        for (int i=0; i<arr.length; i++)
        {
            String a[] = arr[i].split(",");
            if(a[1].equals(code))
            {
                x=a[0];
            }
            if (a[1].equals("EOB"))
            {
                x = "EOB";
            }
        }
        return x;
    }
}
