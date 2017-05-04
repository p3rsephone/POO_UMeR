import java.util.*;
import java.util.ArrayList;


/**
 * Write a description of class UMeR here.
 */

public class UMeR{
    /** Variáveis de instância */
    private HashMap<String, Driver> drivers;
    private HashMap<String, Client> clients;
    private HashMap<String, Vehicle> vehicles;
    private ArrayList<Trip> trips;
    private int tripID;
    private int weather;


    /** Construtores */
    public UMeR(){
        this.drivers  = new HashMap<>();
        this.clients  = new HashMap<>();
        this.vehicles = new HashMap<>();
        this.trips    = new ArrayList<>();
        this.tripID   = 0;
        this.weather  = 0;
    }


    /** Métodos de instância */
    /**
     * Retorna uma cópia com o Map de drivers
     * @return Map drivers
     */
    public HashMap<String, Driver> getDrivers() {
        HashMap<String,Driver> newDrivers = new HashMap<>();
        for (Driver d: this.drivers.values())
            drivers.put(d.getEmail(), d);
        return drivers;
    }

    /**
     * Altera o Map de drivers
     * @param drivers Novo Map de drivers
     */
    public void setDrivers(HashMap<String, Driver> drivers) {
        this.drivers = drivers;
    }

    /**
     * Retornar uma cópia do Map de clients
     * @return Map de clients
     */
    public HashMap<String, Client> getClients() {
        HashMap<String,Client> newClients = new HashMap<>();
        for (Client c: this.clients.values())
            newClients.put(c.getEmail(), c);
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
        for (Vehicle v: this.vehicles.values())
            newVehicles.put(v.getRegistration(), v);
        return vehicles;
    }

    /**
     * Altera o Map de vehicles
     * @param vehicles Novo Map de Vehicles
     */
    public void setVehicles(HashMap<String, Vehicle> vehicles) {
        this.vehicles = vehicles;
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
     * Regista um novo cliente, se ele não existir
     * @param c Cliente a registar
     * @return Registou o cliente com sucesso (true) ou ele já existe (false)
     */
    public boolean registerClient(Client c){
        if (clients.get(c.getEmail()) == null && drivers.get(c.getEmail()) == null) {
            clients.put(c.getEmail(), c);
            return true;
        }
        else return false;
    }

    /**
     * Regista um novo condutor, se ele não existir
     * @param d Condutor a registar
     * @return Registou o condutor com sucesso (true) ou ele já existe (false)
     */
    public boolean registerDriver(Driver d){
        if (drivers.get(d.getEmail()) == null && clients.get(d.getEmail()) == null) {
            drivers.put(d.getEmail(), d);
            return true;
        }
        return false;
    }

    /**
     * Regista um novo veículo, se ele não existir
     * @param v Veículo a registar
     * @return Registou o veículo com sucesso (true) ou ele já existe (false)
     */
    public boolean registerVehicle(Vehicle v){
        if (vehicles.get(v.getRegistration()) == null){
            vehicles.put(v.getRegistration(), v);
            return true;
        }
        else return false;
    }
}
