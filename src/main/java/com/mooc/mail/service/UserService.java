package com.mooc.mail.service;

import com.mooc.mail.bean.User;
import com.mooc.mail.vo.ResponseVo;

public interface UserService {

    ResponseVo register(User user) ;

    ResponseVo<User> login(String username, String password) ;
}
