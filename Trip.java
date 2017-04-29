import java.awt.geom.Point2D;
/**
 * Trip.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Trip {
    // instance variables
    private Point2D.Double start;
	private Point2D.Double end;
	private Double time;
	private Double price;
	private String carPlate;
	private String driverName;


    /**
     * Constructors
     */
    public Trip (Point2D.Double start, Point2D.Double end, Double time, Double price, String carPlate, String driverName) {
        this.start = start;
		this.end = end;
		this.time = time;
		this.price = price;
		this.carPlate = carPlate;
		this.driverName = driverName;
    }

	public Trip (Trip t) {
		this(t.getStart(), t.getEnd(), t.getTime(), t.getPrice(), t.getCarPlate(), t.getDriverName());
	}

	/**
     * Getters
     */

	public Point2D.Double getStart() {
		return this.start;
	}

	public Point2D.Double getEnd() {
		return this.end;
	}

	public Double getTime() {
		return this.time;
	}

	public Double getPrice() {
		return this.price;
	}

	public String getCarPlate() {
		return this.carPlate;
	}

	public String getDriverName() {
		return this.driverName;
	}

	/**
     * Clone method for Trip
     *
     * @return     A copy of the Trip
     */
	public Trip clone () {
		return new Trip (this);
	}
}
