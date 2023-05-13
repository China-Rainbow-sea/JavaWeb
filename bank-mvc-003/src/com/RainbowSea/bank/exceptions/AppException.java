package com.RainbowSea.bank.exceptions;


/**
 * 余额不足异常
 */
public class AppException extends Exception{

        public AppException() {

        }

        public AppException(String msg) {
            super(msg);
        }

}
