package com.mooc.mail.service;

import com.mooc.mail.bean.User;
import com.mooc.mail.dao.UserMapper;
import com.mooc.mail.enumUtils.ResponseEnum;
import com.mooc.mail.enumUtils.UserEnum;
import com.mooc.mail.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public ResponseVo register(User user){
        int countByUsername = userMapper.countByUsername(user.getUsername());
        if (countByUsername > 0) {
            return ResponseVo.userExeit();
        }
        int countByUserEmail = userMapper.countByUserEmail(user.getEmail());
        if (countByUserEmail > 0) {
            return ResponseVo.userExeit();
        }
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));
        //user.setId(2);
        user.setRole(UserEnum.ADMIN.getCode());
        int insert = userMapper.insert(user);
        if (insert == 0) {
            return ResponseVo.dbRegisterError();
        }

        return ResponseVo.success();

    }

    @Override
    public ResponseVo<User> login(String username, String password) {
        User userByUsername = userMapper.slectUserByUsername(username);
        if (userByUsername == null) {
            return ResponseVo.error(ResponseEnum.USER_OR_PASSWORD_ERROR);
        }
        if (!userByUsername.getPassword().equalsIgnoreCase(DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)))){
            return ResponseVo.error(ResponseEnum.USER_OR_PASSWORD_ERROR);
        }
        return ResponseVo.success(userByUsername);
    }
}
