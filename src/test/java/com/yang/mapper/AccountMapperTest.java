package com.yang.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountMapperTest {

    @Autowired
    private AccountMapper accountMapper;


    @Test
    public  void test(){
        accountMapper.selectList(null).forEach(System.out::println);
    }


}