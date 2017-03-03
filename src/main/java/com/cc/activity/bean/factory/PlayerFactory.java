package com.cc.activity.bean.factory;

import com.cc.activity.bean.Player;

/**
 * Created by xn032607 on 2017/2/23.
 */
@FunctionalInterface
public interface PlayerFactory<P extends Player> {
    P create(String pname, String mobile, String imagePath);
}
