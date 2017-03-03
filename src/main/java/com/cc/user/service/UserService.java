package com.cc.user.service;

import com.cc.exception.ExceptionCode;
import com.cc.exception.UserException;
import com.cc.user.bean.User;
import com.cc.user.mapper.UserDAO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xn032607 on 2017/2/17.
 */
@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    static final Pattern usernamePattern = Pattern.compile("[a-zA-Z]{1}[a-zA-Z0-9_]{1,15}");

    public void validateUsername(String username) {
        final Matcher matcher = usernamePattern.matcher(username);
        if(!matcher.matches()) {
            throw new UserException(ExceptionCode.USER_NAME_ERR,"用户名格式不合法");
        }
        User user = userDAO.selectByUsername(username);
        if(user != null) {
            throw new UserException(ExceptionCode.USER_NAME_ALREADY_EXISTS,"用户名已存在");
        }
    }

    public int insert(User user) {
        return userDAO.insert(user);
    }

    public void validateUser(User user) {
        if (user == null) {
            throw new UserException("用户绑定异常");
        }
        if (StringUtils.isBlank(user.getUsername())) {
            throw new UserException("非法的用户名");
        }
        if (StringUtils.isBlank(user.getPassword())) {
            throw new UserException("非法的密码");
        }
        validateUsername(user.getUsername());
    }

    public User loginUser(User user) {
        if(user == null) {
            return null;
        }
        User u = userDAO.selectByUsername(user.getUsername());
        if(u == null || !u.getPassword().equals(user.getPassword())) {
            return null;
        }
        return u;
    }

    public User getUserByUsername(String username) {
        if(StringUtils.isBlank(username)) {
            return null;
        }
        return userDAO.selectByUsername(username);
    }
}
