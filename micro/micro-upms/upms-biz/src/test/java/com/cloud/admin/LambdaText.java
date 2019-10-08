package com.cloud.admin;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wengshij
 * @Date Created in 2019/3/8 9:07
 * Description:
 * Modified By:
 */
public class LambdaText {

    public static void main(String[] args) {
        List<String> list= Arrays.asList("a","b","c");
        
       // list.forEach(System.out::println);
        String tem="a,b,c,d,e,f";
       String bbc= Arrays.stream(tem.split(",")).map(s->"'"+s+"'").collect(Collectors.joining(","));
        System.out.println(bbc);
       List<String> bb =list.stream().filter(str->str.equals("a")||str.equals("b")).map(map->map+"123").collect(Collectors.toList());

  new Thread(()-> System.out.println("12312")).start();

    }




}
