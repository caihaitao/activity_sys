package com.cc.service;

import com.cc.person.Person;
import com.cc.person.PersonService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by xn032607 on 2017/1/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("development")
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Test
    public void testFindAll() {
        System.out.println("-----------------------------");
        personService.findAll().stream().filter(person -> person.getName().startsWith("c")).forEach(person -> System.out.println(person));
//        List<Person> persons = personService.findAll().stream().filter(person -> person.getName().startsWith("c")).collect(Collectors.toList());
//        persons.forEach(person->{
//            System.out.println(person);
//        });
        System.out.println("-----------------------------");
    }

    @Test
    public void testFindByPage() {
        System.out.println("-----------------------------");
        List<Person> persons = personService.findByPage(1,3);
        PageInfo pageInfo = new PageInfo(persons);
        System.out.println("Total rows :"+pageInfo.getTotal());
        persons.forEach(person -> {
            System.out.println(person);
        });
        System.out.println("-----------------------------");
    }

}
