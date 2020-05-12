package com.test;

public class Test {
    public static void main(String[] args) {
        String data ="Tom";
        char[] chars = data.toCharArray();
        chars[0] +=32;
        System.err.println(new String(chars));
    }
}
