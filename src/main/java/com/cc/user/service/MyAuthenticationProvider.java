package com.cc.user.service;

import com.cc.common.util.EncryptUtils;
import com.cc.user.bean.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = Logger.getLogger(MyAuthenticationProvider.class);
    @Autowired
    private UserService userService;

    /**
     * 自定义验证方式
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        User user = userService.getUserByUsername(username);

        if (user == null) {
            throw new BadCredentialsException("Username not found.");
        }

        //加密过程在这里体现
        if (!EncryptUtils.encodeMD5String(password).equals(user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }
        logger.info("username is " + user.getUsername() +","+ user.getRole()+": 验证通过" );
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

}