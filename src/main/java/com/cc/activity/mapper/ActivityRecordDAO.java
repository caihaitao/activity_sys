package com.cc.activity.mapper;


import com.cc.activity.bean.ActivityRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRecordDAO {
    int insert(ActivityRecord record);

    ActivityRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(ActivityRecord record);

    List<ActivityRecord> getActivityRecordsByActivityId(Integer activityId);

    ActivityRecord getRecordByPlayerAndActivity(@Param("playerId")Integer playerId,@Param("activityId") Integer activityId);
}