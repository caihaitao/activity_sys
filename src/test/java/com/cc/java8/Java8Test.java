package com.cc.java8;

import com.cc.person.Person;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 * Created by xn032607 on 2017/1/11.
 */
public class Java8Test {

    @Test
    public void testFunctional() {
        BinaryOperator<Integer> add = (a,b) -> a + b;
        int result = add.apply(add.apply(add.apply(0,1),2),3);
        System.out.println(result);
    }

    @Test
    public void testSum() {
        Stream<Integer> integerStream = Stream.of(1,2,3,4,5);
        int result = integerStream.reduce(0,Integer::sum).intValue();
        System.out.println(result);
    }

    @Test
    public void testPerson() {
        List<Person> persons = asList(new Person(1, "josh", 25, "US"), new Person(2, "cc", 28, "CN"), new Person(3, "hika", 26, "JP"));
        List<String> personInfos =persons.stream().map(person -> person.getName()+" com from "+person.getCountry()).collect(toList());
        Assert.assertEquals(3, personInfos.size());
    }

    @Test
    public void testPerson1() {
        List<Person> persons = asList(new Person(1, "josh", 25, "US"), new Person(2, "cc", 28, "CN"), new Person(3, "hika", 26, "JP"));
        List<Person> personInfos =persons.stream().filter(person -> person.getAge()>25).collect(toList());
        Assert.assertEquals(2, personInfos.size());
    }

    @Test
    public void testPerson2() {
        List<Person> persons = asList(new Person(1, "josh", 25, "US"), new Person(2, "cc", 28, "CN"), new Person(3, "hika", 26, "JP"));
        Integer s = persons.stream().map(person -> person.getAge()).reduce(0,Integer::sum).intValue();
        System.out.println(s);
    }

    @Test
    public void testStr() {
        String s = "ABCDdffes888!";
        long count =  s.chars().filter(Character::isLowerCase).count();

        Assert.assertEquals(5,count);
    }

    @Test
    public void testList() {
        List<String> strs = asList("aaaa", "ccccc", "xxx", "AAA", "ee1");

        String s = strs.stream().max(Comparator.comparing(ss -> ss.chars().filter(Character::isLowerCase).count())).get();
        System.out.println(s);
    }

    @Test
    public void testList2() {
        List<Integer> numbers = asList(1, 2, 3, 4);
        String nbs = numbers.stream().map(x -> x + 1).map(a -> a.toString()).collect(Collectors.joining(",","[","]"));
        System.out.print(nbs);
    }

    @Test
    public void testMap() {
        List<String> words = Arrays.asList("aa","bb","Cc","dD");
        words.stream().map(word -> word.toUpperCase()).forEach(w -> System.out.println(w));
    }

    @Test
    public void testFilter() {
        Stream<String> names = Stream.of("John Lennon", "Paul McCartney",
                "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
        String s = "a";
        String longestName = names.reduce((a,b) -> a=s.length()>b.length() ? s : b).get();
        System.out.println(longestName);
    }

    @Test
    public void testWordCount() {
        Stream<String> names = Stream.of("John", "Paul", "George", "John",
                "Paul", "John");
       Map<String,Long> map =names.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
       System.out.println(map);
    }

    @Test
    public void testParallel() {
        Stream<Integer> numbers = Stream.of(1,2,3,4,5);
        //int result1 = numbers.mapToInt(i -> i*i).sum();
        int result = numbers.parallel().reduce((a,b) -> a = a +b * b).get();
        Assert.assertEquals(55,result);
    }

    @Test
    public void testMutiple() {
        Stream<Integer> numbers = Stream.of(1,2,3,4,5);
        //int result = numbers.reduce(5,(a,b) -> a*b);
        int result = numbers.map(a -> a*a).reduce((a,b)->a+b).get();
        Assert.assertEquals(55,result);
    }
}
