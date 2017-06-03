import java.util.Comparator;

/**
 * MoneyComparatorC
 */
public class MoneyComparatorC implements Comparator<Client> {

	/**
	* Compara o dinheiro entre dois cliente
	* @param c1 Cliente 1
	* @param c2 Cliente 2
	*/
    public int compare(Client c1, Client c2){
        if (c1.getMoney() > c2.getMoney()) return -1;
        if (c1.getMoney() < c2.getMoney()) return 1;
        return (c1.getEmail().compareTo(c2.getEmail()));
    }
}
