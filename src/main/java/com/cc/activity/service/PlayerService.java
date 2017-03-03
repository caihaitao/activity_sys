package com.cc.activity.service;

import com.cc.activity.bean.Player;
import com.cc.activity.mapper.PlayerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by xn032607 on 2017/2/23.
 */
@Service
public class PlayerService {
    @Autowired
    private PlayerDAO playerDAO;

    public int insert(Player player) {
        if(player == null)
            return 0;
        return playerDAO.insert(player);
    }

    public Player getByName(String pName) {
        return playerDAO.getByName(pName);
    }
}
