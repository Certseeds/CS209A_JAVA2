package com.nanoseeds.week05;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Github: https://github.com/Certseeds
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-03-17 15:35:46
 * @LastEditors : nanoseeds
 * @LastEditTime : 2020-03-17 15:35:46
 */
public class practice_week05 {
    public static void main(String[] args){
    List<String> stringList = Arrays.asList("aa","bb","","d","","f","g","h","j");
    List<String> emptys = stringList.stream()
            .filter(string->!string.isEmpty())
            .collect(Collectors.toList());
    for (String s: emptys){
        System.out.println(s);
    }
    }
}
