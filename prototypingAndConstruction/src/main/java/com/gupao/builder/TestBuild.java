package com.gupao.builder;

import org.junit.Test;

public class TestBuild {

    @Test
    public void test(){
        StudentBuild build = new StudentBuild();
        build.addAge(11);
        build.addName("tom");
        Student stu = build.getInstance();

        System.err.println(stu);
    }

    /**
     * 链式编程
     *
     */
    @Test
    public void testChain(){
        StudentBuild build = new StudentBuild();
        build.addAge(11).addName("tom");
        Student stu = build.getInstance();

        System.err.println(stu);
    }
}
