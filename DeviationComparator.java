import java.util.Comparator;

public class DeviationComparator implements Comparator<Driver> {

    public int compare(Driver d1, Driver d2){
        if (d1.getDeviation() > d2.getDeviation()) return -1;
        if (d2.getDeviation() < d2.getDeviation()) return 1;
        return 0;
    }
}
