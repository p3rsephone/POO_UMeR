import java.util.Comparator;

public class RatingComparator implements Comparator<Driver> {

    public int compare(Driver d1, Driver d2){
        if (d1.getRating() > d2.getRating()) return -1;
        if (d1.getRating() < d2.getRating()) return 1;
        return 0;
    }
}
