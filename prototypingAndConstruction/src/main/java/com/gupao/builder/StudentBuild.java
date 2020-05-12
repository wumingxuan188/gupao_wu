package com.gupao.builder;

/**
 * 建造者模式
 */
public class StudentBuild {
    private Student student=new Student();

    public StudentBuild addName(String name){
        student.setName(name);
        return  this;
    }

    public StudentBuild addAge(Integer age){
        student.setAge(age);
        return  this;
    }

   public    Student getInstance(){
        return  student;
   }

}
