import java.util.Comparator;

public class MoneyComparatorD implements Comparator<Driver> {

    public int compare(Driver d1, Driver d2){
        if (d1.getMoney() > d2.getMoney()) return -1;
        if (d1.getMoney() < d2.getMoney()) return 1;
        return 0;
    }
}
