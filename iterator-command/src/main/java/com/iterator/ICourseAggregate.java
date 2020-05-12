package com.iterator;

/**
 * 集合
 */
public interface ICourseAggregate {
    void  add(Course course);

    void remove(Course course);

    Iterator<Course> iterator();
}
