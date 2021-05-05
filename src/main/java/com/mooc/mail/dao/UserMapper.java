package com.mooc.mail.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mooc.mail.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    int countByUsername(String username);

    int countByUserEmail(String email);

    User slectUserByUsername(String username);

}
