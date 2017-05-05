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
        Client u = new Client("as31sd@ashjdas.com", "asdjasd", "ahdja3r1rf", "asdjas", birth, pos, 1200, null, 0, 0.2, 20);
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
        Client u1 = new Client("asdasd@ashjdas.com", "Kashjgdsad", "ghsjdsad", "nsjfdka", birth1, pos1, 100, null, 12, 0.2, 20);

        Point2D.Double pos2 = new Point2D.Double(14.2, 8);
        LocalDate birth2 = LocalDate.of(1965, 5, 21);
        Driver u2 = new Driver("dghsdad@ashjdas.com", "Fhshfbaas", "adfsadsasd", "asdjas", birth2, pos2, 100, 4.0, true, 0.9, 13, null ,13);

        Trip t = new Trip(1, start, end, time, price, date ,carPlate, u2, u1, 8);
        System.out.println("Antes viagem \n");

        System.out.println(u2);
        System.out.println("\n" + "Trip " + "\n" + t + "\n");
        System.out.println("Depois viagem \n");
        u2.addTrip(t);
        u1.addTrip(t);
        System.out.println(u2);
    }

    public static void testeUmer(){
        UMeR u = new UMeR();

        String email1 = "asdgasd@ashjvasd.com";
        String name1 = "Hshj asdvjasd";
        String password1 = "llsvdhshdcdf";
        String address1 = "12 hgdad d asjda";
        Point2D.Double pos1 = new Point2D.Double(14.2, 8);
        LocalDate birth1 = LocalDate.of(1965, 5, 21);
        Client c1 = new Client(email1, name1, password1, address1, birth1, pos1, 0, null, 0, 0.2, 0);

        String email2 = "jhgdsad@ashjvasd.com";
        String name2 = "KLaxsa hgsdashdh";
        String password2 = "12u4ufwqgfei";
        String address2 = "83 GHJAS asjda";
        Point2D.Double pos2 = new Point2D.Double(7, 1);
        LocalDate birth2 = LocalDate.of(1990, 8,9);
        Client c2 = new Client(email2, name2, password2, address2, birth2, pos2, 0, null, 0, 0.4, 0);

        String email3 = "yuigdcxv@ashjvasd.com";
        String name3 = "Ppoifoduy shjgdvash";
        String password3 = "fjhdvfsd";
        String address3 = "674 hacSVDA";
        Point2D.Double pos3 = new Point2D.Double(-2, 3);
        LocalDate birth3 = LocalDate.of(1975, 4, 1);
        Driver d1 = new Driver(email3, name3, password3, address3, birth3, pos3, 0, 0, true, 0.8, 0, null, 0);

        Car cr = new Car();
        cr.setRegistration("GS-23-34");
        cr.setSpeed(60);
        cr.setSeats(4);
        cr.setPrice(1.1);

        u.registerUser(c1);
        u.registerUser(c2);
        u.registerVehicle(cr);
        u.registerUser(d1);

        System.out.println(u);
    }

    public static void main(String[] args){
        //testUser();
        //testTrip();
        testeUmer();

    }
}
