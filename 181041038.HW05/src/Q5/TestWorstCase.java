package Q5;

import Q4.SortTestManager;

public class TestWorstCase {


    public void runTest() {


        int[] worstcasearraysizes = new int[]{100, 1000, 5000, 10000};

        SortTestManager testManager = new SortTestManager(worstcasearraysizes);

        System.out.println();
        System.out.println("WorstCase Test Results");
        testManager.testByWorstCase();
        testManager.printAverageTestResults();
    }
}
