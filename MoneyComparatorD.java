import java.util.Comparator;

/**
 * MoneyComparatorD
 */
public class MoneyComparatorD implements Comparator<Driver> {

	/**
	* Compara o dinheiro entre dois condutores
	* @param d1 Condutor 1
	* @param d2 Condutor 2
	*/
    public int compare(Driver d1, Driver d2){
        if (d1.getMoney() > d2.getMoney()) return -1;
        if (d1.getMoney() < d2.getMoney()) return 1;
        return 0;
    }
}
