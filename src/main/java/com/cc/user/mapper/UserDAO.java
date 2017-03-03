package com.cc.user.mapper;

import com.cc.user.bean.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {
    int insert(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(User record);

    User selectByUsername(String username);
}