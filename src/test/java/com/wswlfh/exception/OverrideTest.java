package com.wswlfh.exception;

import org.junit.Test;

public class OverrideTest {
    /*
    测试子类重写父类方法抛出的异常级别
    结论：子类抛出级别不能比父类高
     */

    class Father {
        public void run() throws RuntimeException {
            System.out.println("Father is running");
        }
    }

    class Son extends Father {

        public void method1(){
            try {
                run();
            } catch (ArithmeticException e) {
                e.printStackTrace();
            }
        }


        @Override
        public void run() throws ArithmeticException{
            System.out.println(10/0);
        }
    }


    @Test
    public void junitTest(){
        new Son().method1();
    }

}
