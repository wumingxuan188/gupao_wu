package com.visitor;

public class Test {
    public static void main(String[] args) {
        Report report = new Report();
        report.showReport(new CEOVisitor());
        System.err.println("=================================");
        report.showReport(new CTOVisitor());
    }
}
