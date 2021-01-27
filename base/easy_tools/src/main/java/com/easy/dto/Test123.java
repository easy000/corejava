package com.easy.dto;

import java.text.DecimalFormat;

public class Test123 {
    public static void main(String[] args) {
        DecimalFormat format2 = new DecimalFormat("#");
        System.out.println(format2.format(12345678912345678.345));//输出12.35
    }
}
