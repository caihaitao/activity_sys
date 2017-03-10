package com.cc.activity.bean.factory;

import com.cc.activity.bean.ActivityTypeDTO;

/**
 * Created by xn032607 on 2017/3/10.
 */
@FunctionalInterface
public interface ActivityTypeDTOFactory<R extends ActivityTypeDTO> {

    R create(Integer type,String typeName);

}
