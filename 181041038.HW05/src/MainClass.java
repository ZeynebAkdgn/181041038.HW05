import Q1.DoubleHashingMap;
import Q4.SortTestManager;
import Q4.TestRandom;
import Q5.TestWorstCase;

import java.util.*;

public class MainClass {


    public static void main(String[] args) {

        DoubleHashingMapTest1();

        TestRandom testRandomValues = new TestRandom();
        testRandomValues.runTest();


        TestWorstCase testWorstCaseValues = new TestWorstCase();
        testWorstCaseValues.runTest();


    }


    public static void DoubleHashingMapTest1() {
        DoubleHashingMap<Integer, String> map = new DoubleHashingMap<>(23);

        map.put(35, "Izmir");
        map.put(34, "İstanbul");
        map.put(06, "Ankara");
        map.put(17, "Çanakkale");
        map.put(10, "Balıkesir");
        map.put(22, "Edirne");
        map.put(20, "Denizli");
        map.put(01, "Adana");
        map.put(07, "Antalya");
        map.put(41, "Kocaeli");
        map.put(67, "Zonguldak");
        map.put(43, "Kütahya");
        map.put(55, "Samsun");

        System.out.println("Count : " + map.size());

        Set<Integer> keys = map.keySet();

        for (Integer key : keys) {
            System.out.println(key + " -> " + map.get(key));
        }
        map.put(22, "EDİRNE");
        System.out.println();
        for (Integer key : keys) {
            System.out.println(key + " -> " + map.get(key));
        }
        System.out.println("Count : " + map.size());
        map.printHashTable();

        map.remove(20);
        map.printHashTable();
        map.put(20, "Denizli");
        map.printHashTable();


        DoubleHashingMap<Integer, String> map2 = new DoubleHashingMap<>();
        System.out.println("map2 ");
        map2.putAll(map);
        map2.printHashTable();
    }

    public static void DoubleHashingMapTest2() {
        DoubleHashingMap<Integer, String> map = new DoubleHashingMap<>(37);


        map.put(34, "İstanbul");
        map.put(35, "Izmir");
        map.put(17, "Çanakkale");
        map.put(06, "Ankara");
        map.put(10, "Balıkesir");
        map.put(20, "Denizli");
        map.put(22, "Edirne");
        map.put(43, "Kütahya");
        map.put(55, "Samsun");
        map.put(01, "Adana");
        map.put(07, "Antalya");
        map.put(41, "Kocaeli");
        map.put(67, "Zonguldak");




        System.out.println("Count : " + map.size());

        Set<Integer> keys = map.keySet();

        for (Integer key : keys) {
            System.out.println(key + " -> " + map.get(key));
        }
        map.put(22, "EDİRNE");
        System.out.println();
        for (Integer key : keys) {
            System.out.println(key + " -> " + map.get(key));
        }
        System.out.println("Count : " + map.size());
        map.printHashTable();

        map.remove(20);
        map.printHashTable();
        map.put(20, "DENİZLİ");
        map.printHashTable();


        DoubleHashingMap<Integer, String> map2 = new DoubleHashingMap<>();
        System.out.println("map2 ");
        map2.putAll(map);
        map2.printHashTable();
    }

}
