import java.awt.geom.Point2D;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Nuno on 02/05/2017.
 */
public class Test {

    public static void testUser(){
        System.out.println("Test User" + "\n" + "-----------------");
        Point2D.Double pos = new Point2D.Double(14.2, 8);
        LocalDate birth = LocalDate.of(1965, 5, 21);
        User u = new User("as31sd@ashjdas.com", "asdjasd", "ahdja3r1rf", "asdjas", birth, pos, 1200, null, 0);
        System.out.println(u);
    }

    public static void testTrip(){
        System.out.println("\nTest Trip" + "\n" + "-----------------");
        Point2D.Double start = new Point2D.Double(-42.0, 20.1);
        Point2D.Double end = new Point2D.Double(105.9, 20.4);
        double time = 30.4;
        double price = 12.6;
        LocalDateTime date = LocalDateTime.of(2017, 05, 02, 14, 40, 12);
        String carPlate = "GX-12-24";

        Point2D.Double pos1 = new Point2D.Double(14.2, 8);
        LocalDate birth1 = LocalDate.of(1965, 5, 21);
        Client u1 = new Client("asdasd@ashjdas.com", "Kashjgdsad", "ghsjdsad", "nsjfdka", birth1, pos1, 100, null, 12, 0.2, 1, 20);

        Point2D.Double pos2 = new Point2D.Double(14.2, 8);
        LocalDate birth2 = LocalDate.of(1965, 5, 21);
        Driver u2 = new Driver("dghsdad@ashjdas.com", "Fhshfbaas", "adfsadsasd", "asdjas", birth2, pos2, 100, 3, true, 0.9, 13, null ,13);

        Trip t = new Trip(1, start, end, time, price, date ,carPlate, u2, u1, 8);
        System.out.println("Antes viagem \n");

        System.out.println(u2);
        System.out.println("\n" + "Trip " + "\n" + t + "\n");
        System.out.println("Depois viagem \n");
        u2.addTrip(t);
        u1.addTrip(t);
        System.out.println(u2);
    }

    public static void main(String[] args){
        testUser();
        testTrip();
    }
}
