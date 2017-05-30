import java.util.Comparator;

public class MoneyComparatorC implements Comparator<Client> {

    public int compare(Client c1, Client c2){
        if (c1.getMoney() > c2.getMoney()) return -1;
        if (c1.getMoney() < c2.getMoney()) return 1;
        return 0;
    }
}
