package com.wswlfh.exception;

import java.util.Scanner;

public class Practice4_EcmDef {

    class EcdefException extends RuntimeException {
        static final long serialVersionUID = -137892175879182L;

        public EcdefException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        try {
            int num1 = Integer.parseInt(args[0]);
            int num2 = Integer.parseInt(args[1]);
            System.out.println(new Practice4_EcmDef().ecm(num1, num2));
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("this is ArrayIndexOutOfBoundsException! args have no parameter!");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("this is NumberFormatException's catch");
        } catch (EcdefException e) {
            e.printStackTrace();
            System.out.println("this is EcdefException's catch");
        } catch (ArithmeticException e) {
            e.printStackTrace();
            System.out.println("this is ArithmeticException's catch");
        }

    }

    private int ecm(int i, int j) {
        if (i < 0 || j < 0)
            throw new EcdefException("negative in the input's list!");
        if (j == 0)
            throw new ArithmeticException("0 in the input's list!");
        return i / j;
    }
}
