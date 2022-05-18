package com.company;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String> ();
        String ano = "2022";
        map.put(ano, String.valueOf(15.90));

        map.put("2021", String.valueOf(10.90));

        System.out.println(map.get("2021"));

    }
}
