package com.bridge.course;

public class Test {
    public static void main(String[] args) {
        JavaCourse course = new JavaCourse();
        course.setiNote(new JavaNote());
        course.getiNote().edit("java");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
