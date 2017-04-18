package com.pactera.exception;

/**
 * Created by Pactera on 2017/3/29.
 */
public class MyException extends RuntimeException {

    private static final long serialVersionUID = -4943117481939153971L;

    public MyException(String s) {
        super(s);
    }
}
