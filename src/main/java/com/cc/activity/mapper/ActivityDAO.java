package com.cc.activity.mapper;

import com.cc.activity.bean.Activity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ActivityDAO {
    int insert(Activity record);

    Activity selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Activity record);

    List<Activity> findAllActivities();

    int delete(Activity activity);

    void deleteByIds(@Param("ids")List<Integer> ids);

    Activity getActivityByDateAndType(@Param("activityDate")LocalDate activityDate, @Param("activityType")Integer activityType);

    int inactiveActivity(@Param("cleanDate")LocalDate cleanDate, @Param("inactiveStatus")int inactiveStatus);
}