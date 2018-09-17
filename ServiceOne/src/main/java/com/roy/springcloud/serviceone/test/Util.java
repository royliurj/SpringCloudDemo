package com.roy.springcloud.serviceone.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {

    public static void main(String[] args) {
        f1();
        wf2();
    }

    public static <T extends Comparable<T>> int countGraterThen(T[] array, T elem){
        int count = 0;
        for (T item: array) {
            if(elem.compareTo(item) > 0){
                ++count;
            }
        }
        return count;
    }

    static List<Apple> apples = new ArrayList<Apple>();
    static List<Fruit> fruit = new ArrayList<Fruit>();

    static class Reader<T>{
        T readExact(List<T> list){
            return list.get(0);
        }
    }

//    //错误代码
//    static void f1(){
//        Reader<Fruit> fruitReader = new Reader<>();
//        //Fruit f = fruitReader.readExact(apples);
//    }

    static class CovariantReader<T>{
        T readCovariant(List<? extends T> list){
            return list.get(0);
        }
    }

    static void f2(){
        CovariantReader<Fruit> fruitCovariantReader = new CovariantReader<>();
        Fruit f= fruitCovariantReader.readCovariant(fruit);
        Fruit a= fruitCovariantReader.readCovariant(apples);
    }

    static <T> void writeExact(List<T> list, T item){
        list.add(item);
    }

    static void f1(){
        writeExact(apples,new Apple());
        writeExact(fruit,new Apple());
    }
    static <T> void writeWithWildcard(List<? super T> list, T item) {
        list.add(item);
    }
    static void wf2() {
        writeWithWildcard(apples, new Apple());
        writeWithWildcard(fruit, new Apple());
    }
}
