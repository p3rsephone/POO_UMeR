import java.awt.geom.Point2D;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Nuno on 02/05/2017.
 */
public class Test {

    public static void testUser(){
        System.out.println("Test User" + "\n" + "-----------------");
        Point2D.Double pos = new Point2D.Double(14.2, 8);
        LocalDate birth = LocalDate.of(1965, 5, 21);
        Client u = new Client("as31sd@ashjdas.com", "asdjasd", "ahdja3r1rf", "asdjas", birth, pos, 1200, null, 0, 0.2, 20, false);
        System.out.println(u);
    }

    //-------------------------------------------------------------------------------//

    public static void testTrip(){
        System.out.println("\nTest Trip" + "\n" + "-----------------");
        Point2D.Double start = new Point2D.Double(-42.0, 20.1);
        Point2D.Double end = new Point2D.Double(105.9, 20.4);
        double time = 30.4;
        double price = 12.6;
        String carPlate = "GX-12-24";

        Point2D.Double pos1 = new Point2D.Double(14.2, 8);
        LocalDate birth1 = LocalDate.of(1965, 5, 21);
        Client u1 = new Client("asdasd@ashjdas.com", "Kashjgdsad", "ghsjdsad", "nsjfdka", birth1, pos1, 100, null, 12, 0.2, 20, false);

        LocalDate birth2 = LocalDate.of(1965, 5, 21);
        Driver u2 = new Driver("dghsdad@ashjdas.com", "Fhshfbaas", "adfsadsasd", "asdjas", birth2, 100, 4.0, true, 0.9, 13, null ,13);

        LocalDate date = LocalDate.now();

        Trip t = new Trip(1, start, end, time, price, date, carPlate, u2, u1, 8);
        System.out.println("Antes viagem \n");

        System.out.println(u2);
        System.out.println("\n" + "Trip " + "\n" + t + "\n");
        System.out.println("Depois viagem \n");
        u2.addTrip(t);
        u1.addTrip(t);
        System.out.println(u2);
    }

    //-------------------------------------------------------------------------------//

    public static void testeVehicle(){

        //CLIENTES
        String email1 = "asdgasd@ashjvasd.com";
        String name1 = "Hshj asdvjasd";
        String password1 = "llsvdhshdcdf";
        String address1 = "12 hgdad d asjda";
        Point2D.Double posx = new Point2D.Double(14.2, 8);
        LocalDate birth1 = LocalDate.of(1965, 5, 21);
        Client c1 = new Client(email1, name1, password1, address1, birth1, posx, 0, null, 0, 0.2, 0, false);

        String email2 = "jhgdsad@ashjvasd.com";
        String name2 = "KLaxsa hgsdashdh";
        String password2 = "12u4ufwqgfei";
        String address2 = "83 GHJAS asjda";
        Point2D.Double pos2 = new Point2D.Double(7, 1);
        LocalDate birth2 = LocalDate.of(1990, 8,9);
        Client c2 = new Client(email2, name2, password2, address2, birth2, pos2, 0, null, 0, 0.4, 0, false);
        //-----------------------------

        String matricula1 = "GS-23-34";
        double fator1 = 1;
        boolean disp1 = true;
        Point2D.Double pos1 = new Point2D.Double(14, 8.5);
        LinkedList<Client> queue1 = new LinkedList<Client>();


        Car c = new Car(matricula1, fator1, pos1);
        c.addClient(c1);
        c.addClient(c2);

        Van v = new Van();


        Bike b = new Bike();
        b.setRegistration("17-OP-42");
        b.setReliable(1);
        b.setAvailable(true);
        b.setPosition(new Point2D.Double(50, -30.72));
        b.setSeats(4);
        b.setSpeed(1000);
        b.setPrice(1.10);


        Helicopter h1 = new Helicopter("51-12-XD",1,pos1);
        h1.setAvailable(false);

        System.out.println(c);
        System.out.println(v);
        System.out.println(b);
        System.out.println(h1);
    }

    //-------------------------------------------------------------------------------//

    public static void testeUmer(){
        UMeR u = new UMeR();

        ThreadLocalRandom rand = ThreadLocalRandom.current();

        Point2D.Double pos1 = new Point2D.Double(rand.nextDouble(-10, 10), rand.nextDouble(-10, 10));
        Point2D.Double pos2 = new Point2D.Double(rand.nextDouble(-10, 10), rand.nextDouble(-10, 10));
        Point2D.Double pos3 = new Point2D.Double(rand.nextDouble(-10, 10), rand.nextDouble(-10, 10));
        Point2D.Double pos4 = new Point2D.Double(rand.nextDouble(-10, 10), rand.nextDouble(-10, 10));
        Point2D.Double pos5 = new Point2D.Double(rand.nextDouble(-10, 10), rand.nextDouble(-10, 10));
        Point2D.Double pos6 = new Point2D.Double(rand.nextDouble(-10, 10), rand.nextDouble(-10, 10));
        Point2D.Double pos7 = new Point2D.Double(rand.nextDouble(-10, 10), rand.nextDouble(-10, 10));
        Point2D.Double pos8 = new Point2D.Double(rand.nextDouble(-10, 10), rand.nextDouble(-10, 10));
        Point2D.Double pos9 = new Point2D.Double(rand.nextDouble(-10, 10), rand.nextDouble(-10, 10));

        LocalDate birth1 = LocalDate.of(1985, 12, 2);
        LocalDate birth2 = LocalDate.of(1995, 2, 8);
        LocalDate birth3 = LocalDate.of(1987, 8, 25);
        LocalDate birth4 = LocalDate.of(1992, 5, 24);
        LocalDate birth5 = LocalDate.of(1972, 7, 7);
        LocalDate birth6 = LocalDate.of(1954, 7, 14);
        LocalDate birth7 = LocalDate.of(1988, 2, 6);
        LocalDate birth8 = LocalDate.of(1987, 1, 14);
        LocalDate birth9 = LocalDate.of(1991, 10, 4);

        Client c1 = new Client("c1@abc.com", "c1", "ihpdha", "12 dass", birth1, pos1, 0, null, 0, 0.2, 0, false);
        Client c2 = new Client("c2@abc.com", "c2", "12SDFASF", "53 sd", birth2, pos2, 0, null, 0, 0.4, 0, true);
        Client c3 = new Client("c3@abc.com", "c3", "JBDFBJC", "98 djhgf", birth3, pos3, 0, null, 0, 0.4, 0, true);
        Client c4 = new Client("c4@abc.com", "c4", "nahdiuags", "91 iuas", birth4, pos4, 0, null, 0, 0.4, 0, true);
        Client c5 = new Client("c5@abc.com", "c5", "u296asd", "987 add", birth5, pos5, 0, null, 0, 0.4, 0, false);
        Client c6 = new Client("c6@abc.com", "c6", "6dcasg", "67 adsst", birth6, pos6, 0, null, 0, 0.4, 0, false);
        Driver d1 = new Driver("d1@abc.com", "d1", "nbsf2wd", "i9 jasd", birth7, 0, 0, true, 0.8, 0, null, 0);
        Driver d2 = new Driver("d2@abc.com", "d2", "iiasds12", "092dd cssda", birth8, 0, 0, true, 0.8, 0, null, 0);
        Driver d3 = new Driver("d3@abc.com", "d3", "oiuas01", "ua2 iqd", birth9, 0, 0, true, 0.8, 0, null, 0);


        Car v1 = new Car("12-GS-43", 85, pos7);
        Van v2 = new Van("85-KC-31", 70, pos8);
        Bike v3 = new Bike("05-DS-12", 92, pos9);

        v1.addOwner("d1@abc.com");
        v2.addOwner("Taxi Company");
        v3.addOwner("Taxi Company");

        Company company1 = new Company("Taxi Company");

        u.registerUser(c1, null);
        u.registerUser(c2, null);
        u.registerUser(c3, null);
        u.registerUser(c4, null);
        u.registerUser(c5, null);
        u.registerUser(c6, null);
        u.registerUser(d1, null);
        u.registerUser(d2, "Taxi Company");
        u.registerUser(d3, "Taxi Company");
        u.registerVehicle(v1);
        u.registerVehicle(v2);
        u.registerVehicle(v3);
        System.out.println(u);

        Point2D.Double dest = new Point2D.Double(5, 5);
        Trip t = u.newTrip(c1, d1, v1, dest);

        u.addTrip(t);
        c1.addTrip(t);
        d1.addTrip(t);
        v1.addTrip(t);

        System.out.println("\n-----Nova Viagem-----\n\n" + t);
        System.out.println("\n\n" + u);
    }

    //-------------------------------------------------------------------------------//

    public static void main(String[] args){
        //testUser();
        //testTrip();
        //testeVehicle();
        testeUmer();
    }
}
