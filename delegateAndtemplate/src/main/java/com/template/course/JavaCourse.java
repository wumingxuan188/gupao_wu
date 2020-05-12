package com.template.course;

public class JavaCourse extends AbstractCourse{
   private  boolean isCheck=false;

    public JavaCourse(boolean isCheck) {
        this.isCheck = isCheck;
    }

    protected void checkHome() {
        System.err.println("检查Java作业");
    }

    @Override
    public boolean isCheck() {
        return this.isCheck;
    }

    @Override
    protected void liveVideo() {
        System.err.println("直播java课程");
    }
}
