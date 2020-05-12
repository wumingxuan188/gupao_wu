package com.iterator;

public class Test {
    public static void main(String[] args) {
        Course java = new Course("java");
        Course python = new Course("python");
        Course php = new Course("php");

        ICourseAggregateImpl aggregate = new ICourseAggregateImpl();
        aggregate.add(java);
        aggregate.add(python);
        aggregate.add(php);
        aggregate.remove(python);
        Iterator<Course> iterator = aggregate.iterator();
        while (iterator.hasNext()){
            Course next = iterator.next();
            System.err.println(next.getName());
        }
        System.err.println("=======================");
        ICourseAggregateImpl a1 = new ICourseAggregateImpl();
        Course php1 = new Course("php1");
        a1.add(php1);
        Iterator<Course> it = a1.iterator();

        while (it.hasNext()){
            Course next = it.next();
            System.err.println(next.getName());
        }

    }
}
