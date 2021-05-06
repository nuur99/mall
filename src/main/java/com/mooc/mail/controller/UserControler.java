package com.mooc.mail.controller;

import com.mooc.mail.bean.User;
import com.mooc.mail.enumUtils.ResponseEnum;
import com.mooc.mail.form.UserLoginForm;
import com.mooc.mail.form.UserRegisterForm;
import com.mooc.mail.service.UserService;
import com.mooc.mail.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.mooc.mail.consts.MallConst.CURRENT_USER;

@RestController
@Slf4j
public class UserControler {
    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public ResponseVo Register(@Valid @RequestBody UserRegisterForm userFrom) {
        User user = new User();
        BeanUtils.copyProperties(userFrom, user);
        return userService.register(user);
    }

    @PostMapping("/user/login")
    public ResponseVo Register(@Valid @RequestBody UserLoginForm userLoginForm,
                               HttpServletRequest httpServletRequest) {
        ResponseVo<User> login = userService.login(userLoginForm.getUsername(), userLoginForm.getPassword());
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(CURRENT_USER, login.getData());
        login.getData().setPassword(" ");
        return login;
    }

    @GetMapping("/user")
    public ResponseVo Register(HttpServletRequest httpServletRequest) {

        HttpSession session = httpServletRequest.getSession();
        log.info("session id" + session.getId());
        User user = (User)session.getAttribute(CURRENT_USER);
        return ResponseVo.success(user);
    }
    @GetMapping("/user/loginOut")
    public ResponseVo loginOut(HttpServletRequest httpServletRequest) {

        HttpSession loginOut = httpServletRequest.getSession();
        loginOut.removeAttribute(CURRENT_USER);

        return ResponseVo.successByMsg("退出登录成功");
    }

}
