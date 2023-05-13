package com.RainbowSea.bank.mvc;


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
