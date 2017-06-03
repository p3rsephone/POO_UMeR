import java.util.Comparator;

/**
 * RatingComparator
 */
public class RatingComparator implements Comparator<Driver> {

	/**
	* Compara as classificações entre dois condutores
	* @param d1 Condutor 1
	* @param d2 Condutor 2
	*/
    public int compare(Driver d1, Driver d2){
        if (d1.getRating() > d2.getRating()) return -1;
        if (d1.getRating() < d2.getRating()) return 1;
        return 0;
    }
}
