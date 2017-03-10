package com.cc.activity.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xn032607 on 2017/3/10.
 */
public enum ActivityTypeEnum {
    badminton(1,"羽毛球运动"),
    swim(2,"游泳运动"),
    cards(3,"棋牌游戏"),
    ;

    private static Map<Integer,ActivityTypeEnum> activityTypeEnumMap = new HashMap<>();
    private static List<ActivityTypeEnum> activityTypeEnumList = new ArrayList<>();

    private int type;
    private String typeName;

    static {
        for(ActivityTypeEnum activityTypeEnum : values()) {
            activityTypeEnumMap.put(activityTypeEnum.type,activityTypeEnum);
            activityTypeEnumList.add(activityTypeEnum);
        }
    }

    ActivityTypeEnum(int type,String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public static ActivityTypeEnum getActivityTypeEnum(Integer type) {
       return activityTypeEnumMap.get(type);
    }

    public static List<ActivityTypeEnum> getAllActicityTypes() {
        return activityTypeEnumList;
    }

    public int getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }
}
