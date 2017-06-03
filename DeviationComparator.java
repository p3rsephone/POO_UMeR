import java.util.Comparator;

/**
* DeviationComparator
*/ 
public class DeviationComparator implements Comparator<Driver> {

	/**
	* Compara os desvios (de dinheiro) entre dois condutores
	* @param d1 Condutor 1
	* @param d2 Condutor 2
	*/
    public int compare(Driver d1, Driver d2){
        if (d1.getDeviation() > d2.getDeviation()) return -1;
        if (d2.getDeviation() < d2.getDeviation()) return 1;
        return 0;
    }
}
