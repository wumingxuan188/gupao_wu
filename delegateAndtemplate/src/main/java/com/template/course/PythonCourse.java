package com.template.course;

public class PythonCourse extends AbstractCourse{
    private  boolean isCheck=false;

    public PythonCourse(boolean isCheck) {
        this.isCheck = isCheck;
    }

    @Override
    public boolean isCheck() {
        return  this.isCheck;
    }

    protected void checkHome() {

        System.err.println("检查python作业");
    }
}
