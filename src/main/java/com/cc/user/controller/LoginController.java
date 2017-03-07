package com.cc.user.controller;

import com.cc.activity.bean.Activity;
import com.cc.activity.bean.Player;
import com.cc.activity.service.ActivityService;
import com.cc.activity.service.PlayerService;
import com.cc.activity.util.ActivityUtil;
import com.cc.common.model.ResponseDTO;
import com.cc.common.util.EncryptUtils;
import com.cc.exception.UserException;
import com.cc.user.bean.User;
import com.cc.user.bean.UserRoleEnum;
import com.cc.user.service.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

/**
 * Created by xn032607 on 2017/2/13.
 */
@Controller
public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;
    @Autowired
    private PlayerService playerService;

    @RequestMapping({"/index", "/","/home"})
    public String index(ModelMap modelMap) {
        List<Activity> activities = activityService.getAllUsableActivities();
        modelMap.addAttribute("activities", activities);
        return "index";
    }

    @RequestMapping("/doLogin")
    public String doLogin(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping("/doRegister")
    public String doRegister(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register/validate")
    @ResponseBody
    public Object validateUsername(@RequestParam("username") String username) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            userService.validateUsername(username);
            responseDTO.setFlag(true);
        } catch (UserException e) {
            responseDTO.setFlag(false);
            responseDTO.setErrMsg(e.getMessage());
        } catch (Exception e) {
            logger.error(e);
            responseDTO.setFlag(false);
            responseDTO.setErrMsg("系統错误");
        }
        return responseDTO;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register/regist")
    @ResponseBody
    public Object registUser(User user) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            userService.validateUser(user);
            user.setRole(UserRoleEnum.VISITOR.getRole());
            user.setPassword(EncryptUtils.encodeMD5String(user.getPassword()));
            userService.insert(user);
            Player player = ActivityUtil.convertFromUser(user);
            playerService.insert(player);
            responseDTO.setFlag(true);
        } catch (UserException e) {
            responseDTO.setFlag(false);
            responseDTO.setErrMsg(e.getMessage());
        } catch (DuplicateKeyException e) {
            responseDTO.setFlag(false);
            responseDTO.setErrMsg("用户名已存在");
        } catch (Exception e) {
            logger.error(e);
            responseDTO.setFlag(false);
            responseDTO.setErrMsg("系統错误");
        }
        return responseDTO;
    }

    @RequestMapping("/login")
    public String login(User user) {
            return "login";
    }

}
