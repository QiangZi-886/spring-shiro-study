package com.yang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yang.entity.Account;
import com.yang.mapper.AccountMapper;
import com.yang.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account findByName(String username) {
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        return accountMapper.selectOne(wrapper);
    }
}
