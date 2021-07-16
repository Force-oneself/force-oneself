package com.quan.demo.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: class StreamDemo
 * @Author Force丶Oneself
 * @Date 2021-05-01
 **/
public class StreamDemo {

    public static List<User> initData() {
        return Arrays.asList(
                new User("张三", 12, 1, Arrays.asList(new Hobby("体育", "篮球"), new Hobby("体育", "足球"))), 
                new User("李四", 23, 0, Arrays.asList(new Hobby("娱乐", "唱歌"), new Hobby("文学", "史记"), new Hobby("娱乐", "跳舞"))),
                new User("王五", 32, 0, Arrays.asList(new Hobby("体育", "足球"), new Hobby("体育", "跳远"), new Hobby("娱乐", "扭秧歌"))),
                new User("小明", 3, 1, Arrays.asList(new Hobby("体育", "铅球"), new Hobby("体育", "足球"))),
                new User("二明", 8, 0, Arrays.asList(new Hobby("体育", "篮球"), new Hobby("文学", "传记"))),
                new User("李青", 34, 1, Collections.singletonList(new Hobby("体育", "乒乓球"))),
                new User("亚索", 43, 0, Arrays.asList(new Hobby("娱乐", "听相声"), new Hobby("体育", "跳高"))),
                new User("德玛西亚", 23, 1, Arrays.asList(new Hobby("体育", "篮球"), new Hobby("体育", "马拉松"))),
                new User("王者荣耀", 19, 0, Arrays.asList(new Hobby("娱乐", "看电影"), new Hobby("体育", "排球"))));
    }


    public static void main(String[] args) {
        List<User> users = StreamDemo.initData();
//        // 按照性别分组
//        Map<Integer, List<User>> userMap = users.stream().collect(Collectors.groupingBy(User::getSex));
//        // 按年龄分区
//        Map<Boolean, List<User>> partitioningBy = users.stream().collect(Collectors.partitioningBy(obj -> obj.getAge() > 20));
//
//        Integer minAgeUser = users.stream().map(User::getAge).min(Integer::compareTo).orElse(null);
//        Integer maxAgeUser = users.stream().map(User::getAge).max(Integer::compareTo).orElse(null);
//
//        long countAge = users.stream().map(User::getAge).count();
//        List<User> girls = users.stream().filter(obj -> obj.getSex() == 1).collect(Collectors.toList());
//
//        // 获取到性别分组后的名字集合
//        Map<Integer, List<String>> nameGroupBySex = users.stream().collect(Collectors.groupingBy(User::getSex, Collectors.mapping(User::getName, Collectors.toList())));
//
        String hobbyString = users.stream().map(User::getHobbies).flatMap(Collection::stream).map(Hobby::getName).distinct().collect(Collectors.joining(",", "[", "]"));
//        System.out.println(hobbyString);
        Stream<User> userStream = users.stream();
        Stream<String> mapStream = userStream.map(User::getName);
        Stream<String> filterStream = mapStream.filter(Objects::nonNull);
        List<String> collect = filterStream.collect(Collectors.toList());
//        Stream<User> filterStream = userStream.filter(Objects::nonNull);
//        Stream<User> peekStream = userStream.peek(System.out::println);
//        Stream<Hobby> flatMap = userStream.map(User::getHobbies).flatMap(Collection::stream);

        System.out.println("debug .....");
    }

    public static class User {
        private String name;
        private Integer age;
        private Integer sex;
        private List<Hobby> hobbies;

        public User(String name, Integer age, Integer sex, List<Hobby> hobbies) {
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.hobbies = hobbies;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Integer getSex() {
            return sex;
        }

        public void setSex(Integer sex) {
            this.sex = sex;
        }

        public List<Hobby> getHobbies() {
            return hobbies;
        }

        public void setHobbies(List<Hobby> hobbies) {
            this.hobbies = hobbies;
        }
    }

    public static class Hobby {
        private String type;
        private String name;

        public Hobby(String type, String name) {
            this.type = type;
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
