package com.pactera.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Pactera on 2017/3/28.
 */
public class Pattern_Mather {



    public static void main(String[] args) {
        String a = "[`~!@#$%^&*()+=|{}':;',]";
        String b = "[~!@#$%]";
        Pattern p = Pattern.compile(a);
        Matcher m = p.matcher(b);
        while (m.find()){
            String tmp = m.group();
            if (!"".equals(tmp)) {
                System.out.println(tmp);
            }
        }
        System.out.println(m.matches());
    }
}
