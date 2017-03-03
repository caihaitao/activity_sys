package com.cc.person;

import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xn032607 on 2017/1/4.
 */
@Service("personService")
public class PersonService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PersonMapper personMapper;

    public List<Person> findAll() {
        return personMapper.findAll();
    }

    public List<Person> findByPage(int pageNo,int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Person> persons = findAll();
        return persons;
    }
}
