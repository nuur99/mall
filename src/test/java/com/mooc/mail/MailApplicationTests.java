package com.mooc.mail;

import com.mooc.mail.bean.User;
import com.mooc.mail.enumUtils.UserEnum;
import com.mooc.mail.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MailApplicationTests {



    @Test
    void contextLoads() {
    }



}
