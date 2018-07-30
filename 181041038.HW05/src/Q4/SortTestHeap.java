package Q4;

import Q4.HeapSort;
import Q4.SortTestAbstract;

public class SortTestHeap extends SortTestAbstract {


    public SortTestHeap(Integer[] unorderednumbers, boolean printnumbers, boolean printstatus) {
        this.setUnorderednumbers(unorderednumbers);
        this.setPrintnumbers(printnumbers);
        this.setPrintstatus(printstatus);
    }

    @Override
    public long testandgetElapsedtime() {
        // Sıralanacak sayılardan clone oluşturularak sayılar sıralanıyor. Böylece orjinal sayılar diğer sıralama yöntemlerinde de kullanılabiliyor
        Integer[] numbers = getUnorderednumbers().clone();

        if ( isPrintstatus()) {
            System.out.println("Heap Sort Test for  " + numbers.length + "  number");
        }
        if (isPrintnumbers()) {
            System.out.println("Before sort");
            printArray(numbers);
        }
        // Başlangıç zamanı kaydediliyor
        long starttime = System.nanoTime();
        // Heap sort sınıfının sort metodu ile sayılar sıralanıyor
        HeapSort.sort(numbers);

        // Bitiş zamanı kaydediliyor
        long endtime = System.nanoTime();
        // Geçen süre hesaplanıyor
        long elapsedtime = endtime - starttime;
        if ( isPrintstatus() ) {
            System.out.println("elapsed time(nano time) : " + elapsedtime);
        }
        if (isPrintnumbers()) {
            System.out.println("After Sort");
            printArray(numbers);
        }

        return elapsedtime;
    }

}