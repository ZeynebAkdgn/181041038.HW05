package Q4;

import Q3.MergeSortDoubleLinkedList;
import Q4.SortTestAbstract;

import java.util.LinkedList;

public class SortTestMergeDoubleLinkedList extends SortTestAbstract {


    public SortTestMergeDoubleLinkedList(LinkedList<Integer> unorderednumberslist, boolean printnumbers, boolean printstatus) {
        this.setUnorderednumberslist(unorderednumberslist);
        this.setPrintnumbers(printnumbers);
        this.setPrintstatus(printstatus);
    }

    @Override
    public long testandgetElapsedtime() {
        // Sıralanacak sayılardan clone oluşturularak sayılar sıralanıyor. Böylece orjinal sayılar diğer sıralama yöntemlerinde de kullanılabiliyor
        LinkedList<Integer> numbers =(LinkedList<Integer>) getUnorderednumberslist().clone();
        if ( isPrintstatus() ) {
            System.out.println("Merge Sort Test for  " + numbers.size() + "  number");
        }
        if (isPrintnumbers()) {
            System.out.println("Before sort");
            printLinkedList(numbers);
        }

        // Başlangıç zamanı kaydediliyor
        long starttime = System.nanoTime();
        // MergesortDoubleLinkedList  sınıfının sort metodu ile sayılar sıralanıyor
        MergeSortDoubleLinkedList.sort(numbers);

        // Bitiş zamanı kaydediliyor
        long endtime = System.nanoTime();

        // Geçen süre hesaplanıyor
        long elapsedtime = endtime - starttime;
        if ( isPrintstatus() ) {
            System.out.println("elapsed time(nano time) : " + elapsedtime);
        }

        if (isPrintnumbers()) {
            System.out.println("After Sort");
            printLinkedList(numbers);
        }

        return elapsedtime;
    }
}
