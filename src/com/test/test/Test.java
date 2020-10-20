package com.test.test;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Ming
 * @date 2020/10/12 - 19:15
 * @describe
 */
public class Test {

    public static void main(String[] args) {
        String key = null;
        Integer value = null;
        Hashtable table = new Hashtable();
        key="3";value=4;
        table.put(key,value);
        key="2";value=2;
        table.put(key,value);
        key="3";value=2;
        table.put(key,value);
        iteratorEntrySet(table);



    }

    private static void iteratorEntrySet(Hashtable table) {

        if (table==null) return;
        String key = null;
        Integer integer = null;
        Iterator iter = table.entrySet().iterator();
        while (iter.hasNext()){
            Map.Entry entry = (Map.Entry) iter.next();
            key = (String) entry.getKey();
            integer = (Integer) entry.getValue();
            System.out.println(key + " -- " + integer.intValue());
        }
    }


}
