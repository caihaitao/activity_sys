package com.cc.person;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xn032607 on 2017/1/4.
 */
@Repository("personMapper")
public interface PersonMapper {
    List<Person> findAll();
}
