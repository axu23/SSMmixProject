package com.itcast;

import java.util.ArrayList;
import java.util.HashSet;

public class THshset {
    public static void main(String[] args) {
        HashSet set = new HashSet<String>();
        set.add("123");
        set.add("123");
        System.out.println(set.size());

        ArrayList<String> list=new ArrayList<String>();
        list.add("34");
        list.add("45");
        String a="34";
        boolean contains = list.contains(a);
        System.out.println(contains);

    }
}
