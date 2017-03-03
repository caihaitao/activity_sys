package com.cc.activity.service;

import com.cc.activity.bean.Activity;
import com.cc.activity.bean.ActivityRecord;
import com.cc.activity.bean.Player;
import com.cc.activity.mapper.ActivityRecordDAO;
import com.cc.activity.util.ActivityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by xn032607 on 2017/2/10.
 */
@Service
public class ActivityRecordService {

    @Autowired
    private ActivityRecordDAO activityRecordDAO;

    public List<ActivityRecord> getActivityRecordsByActivityId(Integer activityId) {
        if(activityId == null) {
            throw new IllegalArgumentException("activityId参数非法");
        }
        return Optional.ofNullable(activityRecordDAO.getActivityRecordsByActivityId(activityId)).orElse(Collections.EMPTY_LIST);
    }

    public void signUpActivity(Player player,Activity activity) {
        ActivityUtil.validateParams(player, activity);
        ActivityRecord activityRecord = ActivityUtil.buildActivityRecord(player,activity);
        activityRecordDAO.insert(activityRecord);
    }

    public ActivityRecord getRecordByPlayerAndActivity(Integer playerId,Integer activityId) {
        return activityRecordDAO.getRecordByPlayerAndActivity(playerId,activityId);
    }
}
