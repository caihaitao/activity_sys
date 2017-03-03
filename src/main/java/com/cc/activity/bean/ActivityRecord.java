package com.cc.activity.bean;

import com.cc.base.BaseObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class ActivityRecord extends BaseObject {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer activityId;

    private String activityName;

    private LocalDate activityDate;

    private Integer playerId;

    private String playerName;

    private LocalDateTime recordTime;

    private String playerMobile;

    public Integer getId() {
        return id;
    }

    public ActivityRecord setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public ActivityRecord setActivityId(Integer activityId) {
        this.activityId = activityId;
        return this;
    }

    public String getActivityName() {
        return activityName;
    }

    public ActivityRecord setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
        return this;
    }

    public LocalDate getActivityDate() {
        return activityDate;
    }

    public ActivityRecord setActivityDate(LocalDate activityDate) {
        this.activityDate = activityDate;
        return this;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public ActivityRecord setPlayerId(Integer playerId) {
        this.playerId = playerId;
        return this;
    }

    public String getPlayerName() {
        return playerName;
    }

    public ActivityRecord setPlayerName(String playerName) {
        this.playerName = playerName == null ? null : playerName.trim();
        return this;
    }

    public LocalDateTime getRecordTime() {
        return recordTime;
    }

    public ActivityRecord setRecordTime(LocalDateTime recordTime) {
        this.recordTime = recordTime;
        return this;
    }

    public String getPlayerMobile() {
        return playerMobile;
    }

    public ActivityRecord setPlayerMobile(String playerMobile) {
        this.playerMobile = playerMobile == null ? null : playerMobile.trim();
        return this;
    }
}