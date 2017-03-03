package com.cc.activity.bean.factory;

import com.cc.activity.bean.Activity;

/**
 * Created by xn032607 on 2017/2/23.
 */
@FunctionalInterface
public interface ActivityFactory<A extends Activity> {
    A create();
}
