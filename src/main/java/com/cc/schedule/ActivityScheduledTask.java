package com.cc.schedule;

import com.cc.activity.bean.Activity;
import com.cc.activity.bean.factory.ActivityFactory;
import com.cc.activity.constants.ActivityConstants;
import com.cc.activity.constants.ActivityTypeEnum;
import com.cc.activity.service.ActivityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Created by xn032607 on 2017/3/13.
 */
@Component
public class ActivityScheduledTask {

    private Logger logger = Logger.getLogger(getClass());
    @Value("${activity_default_badminton_image_path}")
    public String defaultBadmintonImagePath;

    @Autowired
    private ActivityService activityService;

    @Scheduled(cron = "0 0/30 9 ? * MON-TUE")
    public void createBadmintonTask() {
        LocalDate now = LocalDate.now();
        LocalDate activityDate = getActicityDate(now);
        logger.info("current date is " + now + " activity date is " + activityDate);

        Activity activity = activityService.getActivityByDateAndType(activityDate, ActivityTypeEnum.badminton.getType());
        if(activity == null) {
            Activity newActivity = buildActivity(activityDate,ActivityTypeEnum.badminton.getType(),"羽毛球活动");
            int res = activityService.addActivity(newActivity);
            if(res == 0) {
                logger.warn("活动创建失败");
            }
        }
    }

    @Scheduled(cron = "0 0/60 0 1 * ?")
    public void cleanTask() {
        LocalDate now = LocalDate.now();
        LocalDate cleanDate = now.minusMonths(2);
        logger.info("current date is " + now + " clean date is " + cleanDate);
        int res = activityService.inactiveActivity(cleanDate);
        if(res == 0) {
            logger.warn("活动清理失败");
        }
    }

    private Activity buildActivity(LocalDate activityDate, int type,String activityName) {
        ActivityFactory<Activity> activityFactory = Activity::new;
        Activity activity = activityFactory.create();
        activity.setActivityDate(activityDate);
        activity.setStatus(ActivityConstants.ACTIVE_STATUS);
        activity.setImagePath(defaultBadmintonImagePath);
        activity.setRemark("系统自动创建活动");
        activity.setCost(ActivityConstants.DEFAULT_COST);
        activity.setActivityType(type);
        activity.setActivityName(activityName);
        return activity;
    }

    private LocalDate getActicityDate(LocalDate now) {
        LocalDate activityDate = null;
        int dateAdd = Math.abs(DayOfWeek.WEDNESDAY.getValue() - now.getDayOfWeek().getValue());
        if(now.getDayOfWeek().compareTo(DayOfWeek.WEDNESDAY) > 0) {
            activityDate = now.plusDays(7 - dateAdd);
        } else if(now.getDayOfWeek().compareTo(DayOfWeek.WEDNESDAY) == 0){
            activityDate = now;
        } else {
            activityDate = now.plusDays(dateAdd);
        }
       return activityDate;
    }

}
