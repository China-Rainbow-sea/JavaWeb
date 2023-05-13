package com.RainbowSea.bank.service;

import com.RainbowSea.bank.exceptions.AppException;
import com.RainbowSea.bank.exceptions.MoneyNotEnoughException;

public interface AccountService {
    public void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, AppException;
}
