package com.cc.service;

import com.cc.BaseTest;
import com.cc.activity.bean.Activity;
import com.cc.activity.service.ActivityService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Created by xn032607 on 2017/2/9.
 */
public class ActivityServiceTest extends BaseTest {

    @Autowired
    private ActivityService activityService;

    private Integer activityId;

    @Test
    public void testFindAll() {
        List<Activity> activities = activityService.getAllUsableActivities();
        Assert.assertEquals(1,activities.size());
    }

    @Test
    public void testAdd() {
        Activity activity = new Activity();
        activity.setActivityDate(LocalDate.now());
        activity.setActivityName("羽毛球活动");
        activity.setCost(99.99);
        activity.setStatus(1);
        activity.setRemark("第一届羽毛球活动");

        activityService.addActivity(activity);
        activityId = activity.getId();
    }

    @Test
    public void testSelectByPrimaryKey() {
//        Activity activity =  getActivity();
//        Assert.assertNotNull(activity);
    }

    private Activity getActivity() {
        Activity activity;
        if(activityId == null) {
            activity = activityService.selectByPrimaryKey(1);
        } else {
            activity = activityService.selectByPrimaryKey(activityId);
        }
        return activity;
    }

    @Test
    public void testUpdate() {
        Activity activity =  getActivity();
        Assert.assertNotNull(activity);
        activity.setStatus(0);
        Assert.assertEquals(1,activityService.updateActivity(activity));
    }

    @Test
    public void testDelete() {
        Activity activity =  getActivity();
        Assert.assertNotNull(activity);
        Assert.assertEquals(1, activityService.deleteActivity(activity.getId()));
    }
}
