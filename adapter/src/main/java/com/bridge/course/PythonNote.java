package com.bridge.course;

public class PythonNote implements INote {
    public void edit(String data) {
        System.err.println("做笔记: " + data);
    }
}
