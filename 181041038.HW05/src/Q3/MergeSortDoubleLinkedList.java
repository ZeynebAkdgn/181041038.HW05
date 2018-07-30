package Q3;

import java.util.LinkedList;
import java.util.ListIterator;

public class MergeSortDoubleLinkedList {

    public static <T extends  Comparable<T>> void sort(LinkedList<T> table) {
        if ( table.size()>1) {
            int halfSize = table.size()/2;
            LinkedList<T> leftTable = new LinkedList<>();
            LinkedList<T> rightTable = new LinkedList<>();

            ListIterator<T> iter = table.listIterator();
            T item;
            // iterator kullanarak table yarısının solunda kalanlar lefttable linklistine koyuluyor
            for ( int i=0; i<halfSize; i++) {
                item= iter.next();
                leftTable.add(item);
            }
            // iterator kullanarak table yarısının sağında kalanlar rightTable linklistine koyuluyor
            for ( int i=0; i<table.size()-halfSize; i++) {
                item= iter.next();
                rightTable.add(item);
            }

            sort(leftTable);
            sort(rightTable);
            // sıralanmış olam sol ve sağ tablelar birleştiriliyor
            merge(table,leftTable,rightTable);

        }
    }

    private static <T extends  Comparable<T>> void merge(LinkedList<T> outputSequence, LinkedList<T> leftSequence, LinkedList<T> rightSequence) {
        int i=0;
        int j=0;
        int k=0;
        ListIterator<T> leftiter= leftSequence.listIterator();
        ListIterator<T> rightiter= rightSequence.listIterator();
        T leftitem;
        T rigthitem;
        while ( leftiter.hasNext() && rightiter.hasNext()) {
            leftitem = leftiter.next();
            rigthitem = rightiter.next();
            if ( leftitem.compareTo(rigthitem)<0) {
                outputSequence.set(k++,leftitem);
                rightiter.previous();
            }
            else {
                outputSequence.set(k++,rigthitem);
                leftiter.previous();
            }
        }
        while( leftiter.hasNext() ) {
            leftitem = leftiter.next();
            outputSequence.set(k++,leftitem);
        }

        while( rightiter.hasNext()) {
            rigthitem = rightiter.next();
            outputSequence.set(k++,rigthitem);

        }
    }
}
