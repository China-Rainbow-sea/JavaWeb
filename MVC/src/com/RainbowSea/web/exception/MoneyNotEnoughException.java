package com.RainbowSea.web.exception;

public class MoneyNotEnoughException extends Exception{
    public MoneyNotEnoughException() {
        super();
    }

    public MoneyNotEnoughException(String message) {
        super(message);
    }
}
