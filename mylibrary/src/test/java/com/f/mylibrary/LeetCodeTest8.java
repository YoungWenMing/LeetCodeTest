package com.f.mylibrary;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LeetCodeTest8 {

    public int compareVersion(String version1, String version2) {
        String regex = "\\.", s1[] = version1.split(regex), s2[] = version2.split(regex);
        int i = 0, t1, t2, l1 = s1.length, l2 = s2.length;
        for (; i < l2 && i < l1; i ++){
            t1 = Integer.parseInt(s1[i]);
            t2 = Integer.parseInt(s2[i]);
            if (t1 < t2)        return -1;
            else if (t1 > t2)   return 1;
        }
        for (; i < l1 || i < l2 ; i ++){
            if (l1 < l2 && Integer.parseInt(s2[i]) > 0)    return -1;
            if (l1 > l2 && Integer.parseInt(s1[i]) > 0)     return 1;
        }

        return 0;
    }

    @Test
    public void compareVersionTest(){
        String v1 = "1.0.1.2.005", v2 = "1.0.1.2.1";
        String[] strs = v1.split("\\.");
        for (String s : strs) {
            System.out.println(s);
            System.out.println(Integer.parseInt(s));
        }

        System.out.println(compareVersion(v1, v2));
    }


    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0)     return "0";
        boolean neg = false;
        if (numerator * denominator < 0)    neg = true;
        if (numerator < 0)  numerator = - numerator;
        if (denominator < 0)    denominator = - denominator;

        int div, i = 0, pos = -1;
        List<Integer>   res = new ArrayList<>();
        HashMap<Integer, Integer>   map = new HashMap<>();
        while (numerator != 0){
            div = numerator / denominator;
            numerator -= div * denominator;
            res.add(div);
            if (map.containsKey(numerator)){
                pos = map.get(numerator);
                break;
            }
            map.put(numerator, i++);
            numerator *= 10;
        }
        if (res.size() == 1)    return Integer.toString(res.get(0));
        StringBuilder builder = new StringBuilder();
        if (neg)    builder.append('-');
        builder.append(res.get(0));
        builder.append(".");
        if (pos == 0)   builder.append('(');
        for (int j = 1; j < res.size(); j++){
            builder.append(res.get(j));
            if (pos == j)   builder.append('(');
        }
        if (pos != -1)  builder.append(')');
        return builder.toString();
    }

    public String fractionToDecimal2(int numerator, int denominator) {
        if (numerator == 0) return Integer.toString(0);
        StringBuilder builder = new StringBuilder();
        if ((numerator < 0) ^ (denominator < 0))    builder.append('-');
        long nu = Math.abs((long) numerator), de = Math.abs((long) denominator), div;
        div = nu / de;
        nu -= div * de;
        builder.append(div);
        if (nu == 0)    return builder.toString();
        builder.append('.');
        HashMap<Long, Integer> map = new HashMap<>();
        int i = builder.length();
        while (nu != 0){
            if (map.containsKey(nu)){
                builder.insert(map.get(nu), "(");
                builder.append(')');
                break;
            }else {
                map.put(nu, i ++);
                nu *= 10;
            }
            div = nu / de;
            nu -= div * de;
            builder.append(div);
        }
        return builder.toString();
    }

    @Test
    public void fractionToDecimalTest(){
        System.out.println(fractionToDecimal2(1, 2));
        System.out.println(fractionToDecimal2(10, 3));
        System.out.println(fractionToDecimal(1, 4));
        System.out.println(fractionToDecimal2(3, 8));
        System.out.println(fractionToDecimal2(3, 7));
        System.out.println(fractionToDecimal2(1, 6));
        System.out.println(fractionToDecimal(-50, 8));
        System.out.println(fractionToDecimal2(50, -8));
    }

}
