package com.cc.common.model;

/**
 * Created by xn032607 on 2017/2/23.
 */
@FunctionalInterface
public interface ResponseDTOFactory<R extends ResponseDTO> {
    R create(boolean flag,String errMsg);
}
