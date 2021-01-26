package com.xiong.day01;

import com.xiong.entity.L;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

@Slf4j(topic = "enjoy")
public class TestJol {
    static L l = new L();

    public static void main(String[] args) {
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(L.class).toPrintable());
        System.out.println(ClassLayout.parseInstance(l).toPrintable());
    }
}
