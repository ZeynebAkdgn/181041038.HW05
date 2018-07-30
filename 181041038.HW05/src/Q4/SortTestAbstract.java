package Q4;

import java.util.LinkedList;

public abstract  class SortTestAbstract {

    private Integer[] unorderednumbers;
    private LinkedList<Integer> unorderednumberslist;
    private boolean printnumbers;
    private boolean printstatus;


    public abstract long testandgetElapsedtime() ;




    public Integer[] getUnorderednumbers() {
        return unorderednumbers;
    }

    public void setUnorderednumbers(Integer[] unorderednumbers) {
        this.unorderednumbers = unorderednumbers;
    }

    public LinkedList<Integer> getUnorderednumberslist() {
        return unorderednumberslist;
    }

    public void setUnorderednumberslist(LinkedList<Integer> unorderednumberslist) {
        this.unorderednumberslist = unorderednumberslist;
    }




    public boolean isPrintnumbers() {
        return printnumbers;
    }

    public void setPrintnumbers(boolean printnumbers) {
        this.printnumbers = printnumbers;
    }

    public boolean isPrintstatus() {
        return printstatus;
    }

    public void setPrintstatus(boolean printstatus) {
        this.printstatus = printstatus;
    }

    public  void printArray(Integer[] numbers) {


        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }


    }

    public  void printLinkedList(LinkedList<Integer> numbers) {

        for (Integer number : numbers) {
            System.out.println(number);
        }


    }


}
