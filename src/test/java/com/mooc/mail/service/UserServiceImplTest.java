package com.mooc.mail.service;

import com.mooc.mail.bean.User;
import com.mooc.mail.enumUtils.UserEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@Transactional
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void register() throws Exception{
        userService.register(new User("swwdsdsd", "sdsdsd", "sdsdswww@qq.com", UserEnum.ADMIN.getCode()));
    }
}