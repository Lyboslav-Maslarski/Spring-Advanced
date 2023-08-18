package org.example;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        StudentServiceIfc studentServiceIfc = studentServiceIfc();
        System.out.println("-------");
        System.out.println(studentServiceIfc.getALlStudents());
        System.out.println("-------");
        System.out.println(studentServiceIfc.getALlStudents());
        System.out.println("-------");
    }

    private static StudentServiceIfc studentServiceIfc() {
        return (StudentServiceIfc) Proxy.newProxyInstance(
                Test.class.getClassLoader(),
                new Class[]{StudentServiceIfc.class},
                new CacheableInvocationHandler(new StudentService()));
    }
}
