package Q4;

public class TestRandom {



    public void runTest() {
        int[] arraysizes = new int[]{100, 200, 500, 1000, 2000, 5000, 10000, 20000, 50000, 100000};


        SortTestManager testManager = new SortTestManager(arraysizes);

        System.out.println("Random Test Results");
        testManager.testByRandomValues();
        testManager.printAverageTestResults();

    }
}
