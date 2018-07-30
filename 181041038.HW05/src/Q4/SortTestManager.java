package Q4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class SortTestManager {
    ArrayList<TestResult> AverageResultInsertionSort;
    ArrayList<TestResult> AverageResultMergeSort;
    ArrayList<TestResult> AverageResultMergeSortDoubleLinkedList;
    ArrayList<TestResult> AverageResultHeapSort;
    ArrayList<TestResult> AverageResultQuickSort;


    int[] arraysizes;
    private  Random generator;

    public SortTestManager() {
        //default
        arraysizes = new int[]{100, 200, 500, 1000, 2000, 5000, 10000, 20000};
    }
    public SortTestManager(int[] arraysizes) {
        // Parametre olarak gelen dizi eleman sayıları  testlerde kullanılacak
        this.arraysizes =arraysizes;
    }

    public int[] getArraysizes() {
        return arraysizes;
    }

    public void setArraysizes(int[] arraysizes) {
        this.arraysizes = arraysizes;
    }

    private  void runTest(String testtype) {

        boolean printNumbers = false;
        boolean printStatus = false;
        generator = new Random();
        Integer[] numbers=null;

        // Test sonuçlarınını ortalamalarının saklanacağı listeler oluşturuluyor
        AverageResultInsertionSort = new ArrayList<>();
        AverageResultMergeSort = new ArrayList<>();
        AverageResultMergeSortDoubleLinkedList = new ArrayList<>();
        AverageResultHeapSort = new ArrayList<>();
        AverageResultQuickSort = new ArrayList<>();

        // Ortalaması alınacak test sonuçlarının saklanacağı ara listeler oluşturuluyor.
        // Farklı dizi boyutları için test sonucları oluşturulmadan önce algoritma içinde temizlenecek
        // bu listeleri ortalamaları yukarıda tanımlanan listelerde saklanacak
        ArrayList<TestResult> ResultInsertionSort = new ArrayList<>();
        ArrayList<TestResult> ResultMergeSort = new ArrayList<>();
        ArrayList<TestResult> ResultMergeSortDoubleLinkedList = new ArrayList<>();
        ArrayList<TestResult> ResultHeapSort = new ArrayList<>();
        ArrayList<TestResult> ResultQuickSort = new ArrayList<>();

        System.out.println("Test Results");
        System.out.println();

        // Sıralama yapılacak yöntemlerin değişkenleri tanımlanıyor
        SortTestInsertion insertiontest;
        SortTestMerge mergetest;
        SortTestMergeDoubleLinkedList mergedoublelinkedlist;
        SortTestHeap heaptest;
        SortTestQuick quicktest;


        long averageelapsedtime;
        LinkedList<Integer> listnumbers;
        for (int i = 0; i < arraysizes.length; i++) {
            System.out.print("Size of Arrays : " + arraysizes[i] + " test ");
            for (int j = 0; j < 10; j++) {
                // Test yöndetminde kullanılacak sayıların rast gelemi yoksa en kötü durum senaryosuna göre mi üretileceğine karar veriliyor.
                // testByRandomValues() veya testByWorstCase() metodu ile testtype runtest metodunun çağırılması esnasında belirlenir

                if ( testtype.equals("random") ) {
                    numbers = createRandomArray(arraysizes[i]);
                }
                else if(testtype.equals("worstcase")) {
                    numbers = createWorstCaseArray(arraysizes[i]);
                }
                else  {
                    throw new IllegalArgumentException("Test type invalid");
                }
                // SortTestMergeDoubleLinkedList sınıfının aynı test sayılarının kullanabilmesi için numbers dizisi linkliste dönüştürülüyor
                listnumbers = ConvertToLinkedList(numbers);

                // Test metodlarının objectleri oluşturuluyor
                insertiontest = new SortTestInsertion(numbers, printNumbers, printStatus);
                mergetest = new SortTestMerge(numbers, printNumbers, printStatus);
                mergedoublelinkedlist = new SortTestMergeDoubleLinkedList(listnumbers, printNumbers, printStatus);
                heaptest = new SortTestHeap(numbers, printNumbers, printStatus);
                quicktest = new SortTestQuick(numbers, printNumbers, printStatus);

                // Her test objectinin  testandgetElapsedtime() metodu çağrılara test sonuçların elde ediliyor
                // Ve sonuçlar listelere koyuluyor
                ResultInsertionSort.add(new TestResult("Insertion", arraysizes[i], insertiontest.testandgetElapsedtime()));

                ResultMergeSort.add(new TestResult("Merge", arraysizes[i], mergetest.testandgetElapsedtime()));

                ResultMergeSortDoubleLinkedList.add(new TestResult("MergeDouble", arraysizes[i], mergedoublelinkedlist.testandgetElapsedtime()));

                ResultHeapSort.add(new TestResult("Heap", arraysizes[i], heaptest.testandgetElapsedtime()));

                ResultQuickSort.add(new TestResult("Quick", arraysizes[i], quicktest.testandgetElapsedtime()));


            }

            // Test sonuçlarının ortalamaları alınırak ortalamalar listelerine koyuluyor
            averageelapsedtime = getAverageElapsedTime(ResultInsertionSort);
            AverageResultInsertionSort.add(new TestResult("InsertionSort", arraysizes[i],averageelapsedtime));

            averageelapsedtime = getAverageElapsedTime(ResultMergeSort);
            AverageResultMergeSort.add(new TestResult("Merge", arraysizes[i],averageelapsedtime));

            averageelapsedtime = getAverageElapsedTime(ResultMergeSortDoubleLinkedList);
            AverageResultMergeSortDoubleLinkedList.add(new TestResult("MergeDouble", arraysizes[i],averageelapsedtime));

            averageelapsedtime = getAverageElapsedTime(ResultHeapSort);
            AverageResultHeapSort.add(new TestResult("Heap", arraysizes[i],averageelapsedtime));

            averageelapsedtime = getAverageElapsedTime(ResultQuickSort);
            AverageResultQuickSort.add(new TestResult("Quick", arraysizes[i],averageelapsedtime));



            // Farklı eleman sayısındaki test sonuçlarını saklamak üzere test sonuçları listeleri temizleniyor
            ResultInsertionSort.clear();
            ResultMergeSort.clear();
            ResultMergeSortDoubleLinkedList.clear();
            ResultHeapSort.clear();
            ResultQuickSort.clear();


            System.out.println(" completed");
        }


    }

    public void testByRandomValues() {

        runTest("random");
    }

    public void testByWorstCase() {
        runTest("worstcase");
    }

    private  Integer[] createRandomArray(int count) {

        Integer[] numbers = new Integer[count];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = generator.nextInt(count);
        }

        return numbers;
    }

    private  Integer[] createWorstCaseArray(int count) {

        Integer[] numbers = new Integer[count];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] =count-i;
        }

        return numbers;
    }

    private  long getAverageElapsedTime(ArrayList<TestResult> testresults) {
        long total = 0;

        for (TestResult t : testresults) {
            total = total + t.getElapsedtime();
        }
        if ( testresults.size()>0) {
            return total / testresults.size();
        }
        else {
            return 0;
        }
    }

    private  void printTestResults(ArrayList<TestResult> testresults) {


        for (TestResult t : testresults) {
            System.out.println(t.getElapsedtime());
        }

    }


    private  void printSortResults(ArrayList<TestResult> testresults, String label) {

        String strElapsedTimes=String.format("%12s sort : ;",label);
        for( int i=0; i<testresults.size(); i++) {
            strElapsedTimes = strElapsedTimes + String.format("%15d ;", testresults.get(i).getElapsedtime());
        }
        System.out.println(strElapsedTimes);

    }

    private  void printResultHeader(ArrayList<TestResult> testresults) {
        System.out.print("Size of Arrays      ;");
        String strSize="";
        for( int i=0; i<testresults.size(); i++) {
            strSize = strSize + String.format("%15d; ", testresults.get(i).getArraysize());
        }
        System.out.println(strSize);

        System.out.print("--------------------;");
        String strline="";
        for( int i=0; i<testresults.size(); i++) {
            strline = strline + String.format("%15s; ", "---------------");
        }
        System.out.println(strline);

    }

    public void printAverageTestResults() {
        System.out.println();
        printResultHeader(AverageResultInsertionSort);

        printSortResults(AverageResultInsertionSort,"Insertion");
        printSortResults(AverageResultMergeSort,"Merge");
        printSortResults(AverageResultMergeSortDoubleLinkedList,"MergeDouble");
        printSortResults(AverageResultHeapSort,"Heap");
        printSortResults(AverageResultQuickSort,"Quick");
    }

    private LinkedList<Integer> ConvertToLinkedList(Integer[] numbers) {
        LinkedList<Integer> listnumbers = new LinkedList<>();
        for( Integer number : numbers) {
            listnumbers.add(number);
        }

        return listnumbers;
    }

}
