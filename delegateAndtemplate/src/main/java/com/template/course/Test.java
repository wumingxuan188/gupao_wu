package com.template.course;

public class Test {
    public static void main(String[] args) {
        AbstractCourse course = new JavaCourse(false);
        course.execute();
        System.err.println("=====================");
        AbstractCourse pythonCourse = new PythonCourse(true);
        pythonCourse.execute();
    }
}
