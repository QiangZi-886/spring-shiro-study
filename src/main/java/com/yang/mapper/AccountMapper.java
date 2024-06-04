package com.yang.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.entity.Account;
import org.springframework.stereotype.Repository;

@Repository//该注解只是避免了注入时爆红错误
public interface AccountMapper extends BaseMapper<Account> {

}
