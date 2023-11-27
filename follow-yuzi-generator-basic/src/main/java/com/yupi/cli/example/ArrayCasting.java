package com.yupi.cli.example;

import java.util.Arrays;

/***
 * 测试数组强转，本质是对象强转
 */
public class ArrayCasting {
    public static String[] downCasting(Object[] arrayObject) {
        return (String[]) arrayObject;
    }

    public static void main(String[] args) {
        Object[] arrayString = new String[] {"Hello", "String"};
        Object[] arrayObject = new Object[] {"Hello", "String"};
        System.out.println(Arrays.toString(downCasting(arrayString)));  //初始化时，类型为String，向下强转为String
        System.out.println(Arrays.toString(downCasting(arrayObject)));  //初始化时，类型为Object，无法强转为String
    }
}
