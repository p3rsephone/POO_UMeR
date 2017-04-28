
/**
 * Ponto 2D.
 *
 * @author prsphon
 * @version 28.4.17
 */
public class Ponto
{
    // Instance Variables
    private double x;
	private double y;

    /**
     * Constructors
     */
    public Ponto()
    {
        x=0;
		y=0;
    }

	public Ponto(double x, double y) {
			this.x=x;
			this.y=y;
	}

	public Ponto(Ponto pto) {
		this.x=pto.getX();
		this.y=pto.getY();
	}

	public 

    /**
     * Obter a localização
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}
