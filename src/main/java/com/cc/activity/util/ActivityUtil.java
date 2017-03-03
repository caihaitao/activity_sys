package com.cc.activity.util;

import com.cc.activity.bean.Activity;
import com.cc.activity.bean.ActivityRecord;
import com.cc.activity.bean.Player;
import com.cc.activity.bean.factory.PlayerFactory;
import com.cc.exception.ActivityException;
import com.cc.exception.ExceptionCode;
import com.cc.user.bean.User;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.function.Function;

/**
 * Created by xn032607 on 2017/2/9.
 */
public class ActivityUtil {

    public static void validateParams(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("活动参数错误");
        }
        if (StringUtils.isBlank(activity.getActivityName())) {
            throw new ActivityException(ExceptionCode.ACTIVITY_NAME_ERR, "活动名称错误");
        }
        if (activity.getActivityDate() == null) {
            throw new ActivityException(ExceptionCode.ACTIVITY_DATE_ERR, "活动日期错误");
        }
    }

    public static void validateParams(Player player, Activity activity) {
        if (player == null) {
            throw new IllegalArgumentException("活动报名参数错误");
        }
        if (StringUtils.isBlank(player.getPname())) {
            throw new ActivityException(ExceptionCode.ACTIVITY_PLAYER_NAME_ERR, "参赛选手名称错误");
        }
        validateParams(activity);
    }

    public static ActivityRecord buildActivityRecord(Player player, Activity activity) {
        return new ActivityRecord().setActivityName(activity.getActivityName()).setActivityDate(activity.getActivityDate())
                .setActivityId(activity.getId()).setPlayerId(player.getId()).setPlayerName(player.getPname()).setPlayerMobile(player.getMobile())
                .setRecordTime(LocalDateTime.now());
    }

    public static Player convertFromUser(User currentUser) {
        if(currentUser == null) {
            return null;
        }
        PlayerFactory<Player> playerFactory = Player::new;
        String playName = currentUser.getRealname() == null ? currentUser.getUsername() : currentUser.getRealname();
        return playerFactory.create(playName,currentUser.getMobile(),currentUser.getImagePath());
    }
}
