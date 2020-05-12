package com.iterator;

import java.util.ArrayList;
import java.util.List;

public class ICourseAggregateImpl implements ICourseAggregate {
    private List<Course> list=new ArrayList<Course>();

    public void add(Course course) {
        list.add(course);
    }

    public void remove(Course course) {
            list.remove(course);
    }

    public Iterator<Course> iterator() {

        return new IteratorImpl(list);
    }
}
