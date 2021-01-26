package com.easy.principle.dependence_inversion;

public class JavaCourse implements ICourse {
    @Override
    public void study() {
        System.out.println("学习Java");
    }
}
