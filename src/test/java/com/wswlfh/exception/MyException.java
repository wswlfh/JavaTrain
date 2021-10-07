package com.wswlfh.exception;

import org.junit.Test;

public class MyException extends RuntimeException {


    private static final long serialVersionUID = 53125232165674L;

    private int idNumbers;

    public MyException(String msg, int id) {
        super(msg);
        this.idNumbers = id;
    }

}

class Main {

    public static void main(String[] args) {
        new Main().run(2);
    }

    public void run(int id) {
        if (id > 50)
            throw new MyException("id is illegal", id);
        System.out.println(id);
    }
}
