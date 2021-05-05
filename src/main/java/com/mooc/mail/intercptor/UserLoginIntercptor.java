package com.mooc.mail.intercptor;

import com.mooc.mail.bean.User;
import com.mooc.mail.enumUtils.ResponseEnum;
import com.mooc.mail.excrption.UserLoginException;
import com.mooc.mail.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.mooc.mail.consts.MallConst.CURRENT_USER;

@Slf4j
public class UserLoginIntercptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(CURRENT_USER);
        if (user == null) {
//            log.info("user==null");
//            return false;
            throw new UserLoginException();
        }
        return true;
    }
}
