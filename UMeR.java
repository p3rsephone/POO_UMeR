import java.awt.geom.Point2D;
import java.time.LocalDateTime;
import java.util.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Write a description of class UMeR here.
 */

public class UMeR{

    /** Variáveis de instância */
    private HashMap<String, Driver> driversP;
    private HashMap<String, Client> clients;
    private HashMap<String, Vehicle> vehiclesP;
    private HashMap<String, Vehicle> allVehicles;
    private HashMap<String, Company> companies;
    private ArrayList<Trip> trips;
    private int tripID;
    private int weather;


    /** Construtores */
    public UMeR(){
        this.driversP    = new HashMap<>();
        this.clients     = new HashMap<>();
        this.vehiclesP   = new HashMap<>();
        this.allVehicles = new HashMap<>();
        this.trips       = new ArrayList<>();
        this.companies   = new HashMap<>();
        this.tripID      = 0;
        this.weather     = 0;
    }


    /** Métodos de instância */
    /**
     * Retorna uma cópia com o Map de drivers
     * @return Map drivers
     */
    public HashMap<String, Driver> getDrivers() {
        HashMap<String,Driver> newDrivers = new HashMap<>();
        for (Driver d: this.driversP.values())
            driversP.put(d.getEmail(), d.clone());
        return driversP;
    }

    /**
     * Altera o Map de drivers
     * @param drivers Novo Map de drivers
     */
    public void setDrivers(HashMap<String, Driver> drivers) {
        this.driversP = drivers;
    }

    /**
     * Retornar uma cópia do Map de clients
     * @return Map de clients
     */
    public HashMap<String, Client> getClients() {
        HashMap<String,Client> newClients = new HashMap<>();
        for (Client c: this.clients.values())
            newClients.put(c.getEmail(), c.clone());
        return newClients;
    }

    /**
     * Altera o map de clients
     * @param clients Novo Map de clients
     */
    public void setClients(HashMap<String, Client> clients) {
        this.clients = clients;
    }

    /**
     * Retorna uma cópia do Map de vehicles
     * @return Map de vehicles
     */
    public HashMap<String, Vehicle> getVehicles() {
        HashMap<String,Vehicle> newVehicles = new HashMap<>();
        for (Vehicle v: this.vehiclesP.values())
            newVehicles.put(v.getRegistration(), v.clone());
        return vehiclesP;
    }

    /**
     * Altera o Map de vehicles
     * @param vehicles Novo Map de Vehicles
     */
    public void setVehicles(HashMap<String, Vehicle> vehicles) {
        this.vehiclesP = vehicles;
    }

    /**
     * Retorna uma cópia da List de trips
     * @return List de trips
     */
    public ArrayList<Trip> getTrips() {
        ArrayList<Trip> newTrips = new ArrayList<>();
        for (Trip t: this.trips)
            newTrips.add(t);
        return newTrips;
    }

    /**
     * Altera a List de viagens
     * @param trips Nova List de viagens
     */
    public void setTrips(ArrayList<Trip> trips) {
        this.trips = trips;
    }

    /**
     * Retorna o ID da viagem mais recente
     * @return ID
     */
    public int getTripID() {
        return tripID;
    }

    /**
     * Altera o ID da ultima viagem
     * @param tripID Novo ID
     */
    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    /**
     * Retorna o tempo atual
     * @return Tempo
     */
    public int getWeather() {
        return weather;
    }

    /**
     * Altera o tempo atual
     * @param weather Novo tempo
     */
    public void setWeather(int weather) {
        this.weather = weather;
    }

    /**
     * Imprime a informação da Empresa
     * @return String com a informação
     */
    public String toString(){
        return "----------------\nDrivers\n\n" + this.driversP +
                "\n----------------\nClients\n\n" + this.clients +
                "\n----------------\nVehicles\n\n" + this.vehiclesP +
                "\n----------------\nTrips\n\n" + this.trips;
    }

    /**
     * Regista um utilizador (Condutor ou Cliente). Caso seja para registar um condutor numa empresa, passar o nome desta como parámetro
     * @param u         User a registar
     * @param company   Empresa a registar (condutor). Caso seja cliente ou condutor privado tem que se passar null como argumento
     * @return Registou com sucesso (true) ou já existe (false)
     */
    public boolean registerUser(User u, String company){
        if (this.driversP.get(u.getEmail()) == null && this.clients.get(u.getEmail()) == null){
            if (u instanceof Client)
                this.clients.put(u.getEmail(), (Client) u.clone());
            else this.driversP.put(u.getEmail(), (Driver) u.clone());
            return true;
        }
        else return false;
    }

    /**
     * Regista um novo veículo, se ele não existir
     * @param v Veículo a registar
     * @return Registou o veículo com sucesso (true) ou ele já existe (false)
     */
    public boolean registerVehicle(Vehicle v){
        if (this.allVehicles.get(v.getRegistration()) == null){
            Vehicle vehicle = v.clone();
            this.vehiclesP.put(vehicle.getRegistration(), vehicle);
            this.allVehicles.put(vehicle.getRegistration(), vehicle);
            return true;
        }
        else return false;
    }

    /**
     * Regista um novo veículo a uma determinada empresa, se não existir
     * @param company Nome da empresa onde se quer registar
     * @param v       Veículo a registar
     * @return        Registou o veículo com sucesso (true) ou a empresa não existe / carro já existe (false)
     */
    public boolean registerCompanyVehicle(String company, Vehicle v){
        if (this.companies.get(company) != null && this.allVehicles.get(v.getRegistration()) == null){
            Vehicle vehicle = v.clone();
            this.allVehicles.put(vehicle.getRegistration(), vehicle);
            this.companies.get(company).addVehicle(vehicle);
            return true;
        }
        else return false;
    }

    /**
     * Remove um utilizador
     * @param u Utilizador a remover
     */
    public void removeUser(User u){
        if (u instanceof Client)
            this.clients.remove(u);
        else this.driversP.remove(u);
    }

    /**
     * Remove um veículo
     * @param v Veículo a remover
     */
    public void removeVehicle(Vehicle v){
        this.allVehicles.remove(v);
    }

    /**
     * Calcula o tempo estimado de um ponto a outro
     * @param start         Ponto de partida
     * @param end           Ponto de chegada
     * @param vehicleSpeed  Velocidade do veículo
     * @return Tempo esperado
     */
    public double estimatedTime(Point2D.Double start, Point2D.Double end, double vehicleSpeed){
        return (start.distance(end)) / vehicleSpeed;
    }

    /**
     * Calcula o nível de transito à volta de um veículo
     * @param v         Veículo
     * @param radius    Raio
     * @return Nível de transito
     */
    public int calculateTraffic(Vehicle v, double radius){
        if (v instanceof Helicopter) return 1;

        int traffic = 1;
        for (Vehicle vehicles : this.allVehicles.values())
            if (vehicles.getPosition().distance(v.getPosition()) <= radius);
        traffic++;

        return traffic;
    }


    /**
     * Calcula o tempo real de uma viagem, a partir de uma série de variáveis e de fatores aleatótios
     * @param start     Início da viagem
     * @param end       Fim da viagem
     * @param d         Condutor
     * @param v         Veículo
     * @return Duração real da viagem
     */
    public double realTime(Point2D.Double start, Point2D.Double end, Driver d, Vehicle v){
        CustomProbabilisticDistribution distD = new CustomProbabilisticDistribution();
        CustomProbabilisticDistribution distC = new CustomProbabilisticDistribution();
        CustomProbabilisticDistribution distS = new CustomProbabilisticDistribution();
        double eta = estimatedTime(start, end, v.getSpeed()) * 1.2;

        double radius = start.distance(end)/2;
        double traffic = (calculateTraffic(v, radius) * ThreadLocalRandom.current().nextInt(1, 100)) / radius;

        distD.addValues(0, d.getTimeCompliance()/100);
        distD.addValues(1, (1 - d.getTimeCompliance())/100);
        int driverSuccess = distD.pickNumber();

        distC.addValues(0, v.getReliable()/100);
        distC.addValues(1, (1 - v.getReliable())/100);
        int carSuccess = distC.pickNumber();

        double driverSkillChance = d.getTotalDistance() / d.getExp();
        distS.addValues(1, driverSkillChance);
        distS.addValues(0, 1 - driverSkillChance);
        int driverSkill = distS.pickNumber();

        double driverMultiplier = ThreadLocalRandom.current().nextDouble(0.1, 0.2);
        double carMultiplier = ThreadLocalRandom.current().nextDouble(0.1, 0.2);
        double driverSkillMultiplier = ThreadLocalRandom.current().nextDouble(0.1, 0.2);
        double weatherMultiplier = ThreadLocalRandom.current().nextDouble(0, 0.2);
        double trafficMultiplier = Math.abs(ThreadLocalRandom.current().nextGaussian());

        double trafficDelay = Math.min(traffic * trafficMultiplier / 100, 0.5);
        double weatherDelay = this.weather * (weatherMultiplier / 5);

        double multiplier = 0.9 + driverSuccess*driverMultiplier + carSuccess*carMultiplier
                            + trafficDelay + weatherDelay - driverSkill*driverMultiplier;

        double realTime = eta*multiplier;
        return realTime;
    }


    //TODO
    //public void newTrip(Client c, Driver d, Vehicle v, Point2D destination)
    //public boolean newTripClosest(Client c, Point2D.Double destination){
    //public void newTripSpecific(Client c, String driverEmail);
}
