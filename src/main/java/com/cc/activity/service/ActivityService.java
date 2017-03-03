package com.cc.activity.service;

import com.cc.activity.bean.Activity;
import com.cc.activity.mapper.ActivityDAO;
import com.cc.activity.util.ActivityUtil;
import com.cc.exception.ActivityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by xn032607 on 2017/2/8.
 */
@Service
public class ActivityService {

    @Autowired
    private ActivityDAO activityDAO;

    public List<Activity> getAllActivities() {
        return Optional.ofNullable(activityDAO.findAllActivities()).orElse(Collections.EMPTY_LIST);
    }

    public List<Activity> getAllUsableActivities() {
        return Optional.ofNullable(activityDAO.findAllActivities().stream().filter(activity -> activity.getStatus().intValue() == 1).collect(Collectors.toList())).orElse(Collections.EMPTY_LIST);
    }

    public Activity selectByPrimaryKey(Integer id) {
        checkParams(id);
        return Optional.ofNullable(activityDAO.selectByPrimaryKey(id)).orElse(null);
    }

    public int addActivity(Activity activity) {
        ActivityUtil.validateParams(activity);
        return activityDAO.insert(activity);
    }

    public int updateActivity(Activity activity) {
        ActivityUtil.validateParams(activity);
        return activityDAO.updateByPrimaryKey(activity);
    }

    public int deleteActivity(Integer id) {
        checkParams(id);
        Activity activity = new Activity();
        activity.setId(id);
        return activityDAO.delete(activity);
    }

    private void checkParams(Integer id) {
        if (id == null) {
            throw new ActivityException("id参数不合法");
        }
    }

    public void deleteActivityByIds(List<Integer> ids) {
        if(!CollectionUtils.isEmpty(ids)) {
            activityDAO.deleteByIds(ids);
        }
    }
}
