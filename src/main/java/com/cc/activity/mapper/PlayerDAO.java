package com.cc.activity.mapper;


import com.cc.activity.bean.Player;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerDAO {
    int insert(Player record);

    Player selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Player record);

    Player getByName(String pName);
}