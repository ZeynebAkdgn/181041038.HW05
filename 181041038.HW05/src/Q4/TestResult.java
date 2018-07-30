package Q4;

public class TestResult {
    private String sortname;
    private  int arraysize;
    private long elapsedtime;

    public  TestResult(String sortname, int arraysize, long elapsedtime) {
        this.sortname = sortname;
        this.arraysize = arraysize;
        this.elapsedtime = elapsedtime;
    }

    public String getSortname() {
        return sortname;
    }

    public void setSortname(String sortname) {
        this.sortname = sortname;
    }

    public int getArraysize() {
        return arraysize;
    }

    public void setArraysize(int arraysize) {
        this.arraysize = arraysize;
    }

    public long getElapsedtime() {
        return elapsedtime;
    }

    public void setElapsedtime(long elapsedtime) {
        this.elapsedtime = elapsedtime;
    }
}
