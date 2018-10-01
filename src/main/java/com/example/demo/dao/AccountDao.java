package com.example.demo.dao;

import com.example.demo.domain.Account;

public interface AccountDao {
    Account findAccountByNumber(String accountNumber);
}
