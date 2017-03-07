package com.cc.activity.controller;

import com.cc.activity.bean.Activity;
import com.cc.activity.bean.ActivityRecord;
import com.cc.activity.bean.Player;
import com.cc.activity.service.ActivityRecordService;
import com.cc.activity.service.ActivityService;
import com.cc.activity.service.PlayerService;
import com.cc.activity.util.ActivityUtil;
import com.cc.common.model.ResponseDTO;
import com.cc.common.model.ResponseDTOFactory;
import com.cc.exception.ActivityException;
import com.cc.user.bean.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xn032607 on 2017/2/23.
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {

    private Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivityRecordService activityRecordService;
    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO doSignUp(@RequestParam("activityId") Integer activityId, HttpSession session) {
        ResponseDTOFactory<ResponseDTO> responseDTOFactory = ResponseDTO::new;
        Object pp = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if(pp instanceof UserDetails) {
            userDetails = (UserDetails)pp;
        }

        if (userDetails == null) {
            return responseDTOFactory.create(false, "请登录后再报名");
        }
        if (activityId == null) {
            return responseDTOFactory.create(false, "请选择活动再报名");
        }
        Activity activity = activityService.selectByPrimaryKey(activityId);
        if (activity == null) {
            return responseDTOFactory.create(false, "活动不存在");
        }
        User currentUser = (User) userDetails;
        String playName = currentUser.getRealname() == null ? currentUser.getUsername() : currentUser.getRealname();
        Player player = playerService.getByName(playName);
        if (player == null) {
            player = ActivityUtil.convertFromUser(currentUser);
            playerService.insert(player);
        }
        try {
            ActivityRecord record = activityRecordService.getRecordByPlayerAndActivity(player.getId(), activityId);
            if (record != null) {
                return responseDTOFactory.create(false, "您已经报名该活动了");
            }

            activityRecordService.signUpActivity(player, activity);
            return responseDTOFactory.create(true, null);
        } catch (ActivityException | IllegalArgumentException e) {
            return responseDTOFactory.create(false, e.getMessage());
        } catch (DuplicateKeyException de) {
            return responseDTOFactory.create(false, "您已经报名该活动了");
        } catch (Exception ie) {
            logger.error(ie);
            return responseDTOFactory.create(false, "系统错误");
        }
    }

    @RequestMapping("/{activityId}")
    public String activityDetail(@PathVariable("activityId") Integer activityId, ModelMap modelMap) {
        if (activityId == null) {
            return "redirect:index";
        }
        Activity activity = activityService.selectByPrimaryKey(activityId);
        if (activity == null) {
            return "redirect:index";
        }
        List<ActivityRecord> records = activityRecordService.getActivityRecordsByActivityId(activityId);

        modelMap.addAttribute("activity", activity);
        if (!CollectionUtils.isEmpty(records)) {
            modelMap.addAttribute("totalPlayers", records.stream().count());
            modelMap.addAttribute("players", records.stream().map(r -> r.getPlayerName()).reduce((p1, p2) -> p1 + ";" + p2).get());
        }
        return "activityDetail";
    }
}
