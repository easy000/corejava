package com.easy.principle.dependence_inversion;

public class Tom {
    public void studyJavaCourse() {
        System.out.println("tom 学习 Java 课程");
    }

    public void studyPythonCource() {
        System.out.println("tom 学习 Python 课程");
    }

    public void study(ICourse course) {
        course.study();
    }

    public static void main(String[] args) {
        Tom tom = new Tom();
//        tom.studyJavaCourse();
//        tom.studyPythonCource();
        tom.study(new JavaCourse());
    }
}
