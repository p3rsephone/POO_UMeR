import java.awt.geom.Point2D;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.lang.Comparable;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.io.*;

/**
 * Write a description of class UMeR here.
 */

public class UMeR implements Serializable {

    /** Variáveis de instância */
    private HashMap<String, Driver> driversP;
    private HashMap<String, Driver> allDrivers;
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
        this.allDrivers  = new HashMap<>();
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
    public HashMap<String, Driver> getDriversP() {
        HashMap<String,Driver> newDrivers = new HashMap<>();
        for (Driver d: this.driversP.values())
            newDrivers.put(d.getEmail(), d.clone());
        return newDrivers;
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
        for (Client c: this.clients.values()) {
            newClients.put(c.getEmail(), c.clone());
        }
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
     * Retorna uma cópia do Map de vehicles privados
     * @return Map de vehicles
     */
    public HashMap<String, Vehicle> getVehiclesP() {
        HashMap<String,Vehicle> newVehicles = new HashMap<>();
        for (Vehicle v: this.vehiclesP.values()) {
            newVehicles.put(v.getLicencePlate(), v.clone());
        }
        return newVehicles;
    }

    /**
     * Altera o Map de vehicles privados
     * @param vehicles Novo Map de Vehicles
     */
    public void setVehiclesP(HashMap<String, Vehicle> vehicles) {
        this.vehiclesP = vehicles;
    }

    /**
     * Retorna uma cópia do Map de todos os drivers
     * @return Map de drivers
     */
    public HashMap<String, Driver> getAllDrivers(){
        int i = 0;
        HashMap<String,Driver> newDrivers = new HashMap<>();
        for (Driver d: this.allDrivers.values()) {
            newDrivers.put(d.getEmail(), d.clone());
        }
        return newDrivers;
    }

    /**
     * Altera o Map de todos os drivers
     * @param alldrivers Novo Map de drivers
     */
    public void setAllDrivers(HashMap<String, Driver> alldrivers){
        this.allDrivers = alldrivers;
    }

    /**
     * Retorna uma cópia do Map de todos dos vehicles
     * @return Map de vehicles
     */
    public HashMap<String, Vehicle> getAllVehicles(){
        HashMap<String,Vehicle> newAllVehicles = new HashMap<>();
        for (Vehicle v: this.allVehicles.values()) {
            newAllVehicles.put(v.getLicencePlate(), v.clone());
        }
        return newAllVehicles;
    }

    /**
     * Retorna uma cópia da List de trips
     * @return List de trips
     */
    public ArrayList<Trip> getTrips() {
        ArrayList<Trip> newTrips = new ArrayList<>();
        for (Trip t: this.trips) {
            newTrips.add(t);
        }
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
     * Retorna uma cópia das empresas
     * @return Empresas
     */
    public HashMap<String, Company> getCompanies() {
        HashMap<String,Company> newCompanies = new HashMap<>();
        for (Company c: this.companies.values())
            newCompanies.put(c.getName(), c.clone());
        return newCompanies;
    }

    /**
     * Altera o estado de um condutor
     * @param driver        Condutor
     * @param availability  Novo estado
     */
    public void changeDriverAvailability(String driver, boolean availability){
        this.allDrivers.get(driver).setAvailability(availability);
        if (this.allDrivers.get(driver).getVehicle() != null)
            this.allVehicles.get(this.allDrivers.get(driver).getVehicle()).setAvailable(availability);
    }

    /**
     * Altera o carro de um condutor
     * @param driver        Condutor
     * @param licencePlate  Nova matrícula
     */
    public void changeDriverVehicle(String driver, String licencePlate){
        this.allDrivers.get(driver).setVehicle(licencePlate);
    }

    /**
     * Imprime a informação da Empresa
     * @return String com a informação
     */
    public String toString(){
        return "---Drivers---\n" + this.driversP.keySet() +
        "\n---Clients---\n" + this.clients.keySet() +
        "\n---Vehicles---\n" + this.vehiclesP.keySet() +
        "\n---Number of trips---\n" + this.tripID;
    }

    /**
     * Retonar o map de todos os utilizadores
     * @return Map de utilizadores
     */
    public HashMap<String, User> allUsers(){
        HashMap<String, User> allUsers = new HashMap<>();
        for (Client client : this.clients.values()) {
            allUsers.put(client.getEmail(), client.clone());
        }
        for (Driver driver : this.driversP.values()) {
            allUsers.put(driver.getEmail(), driver.clone());
        }
        for (Company company : this.companies.values())
            for (Driver driver : company.getDrivers().values())
                allUsers.put(driver.getEmail(), driver.clone());

        return allUsers;
    }

    /**
     * Regista um utilizador (Condutor ou Cliente). Caso seja para registar um condutor numa empresa, passar o nome desta como parámetro
     * @param u         User a registar
     * @param company   Empresa a registar (condutor). Caso seja cliente ou condutor privado tem que se passar null como argumento
     * @return Registou com sucesso (true) ou já existe (false)
     */
    public boolean registerUser(User u, String company){
        if (this.driversP.get(u.getEmail()) == null && this.clients.get(u.getEmail()) == null
                && this.companies.get(u.getName()) == null){
            if (u instanceof Client) {
                this.clients.put(u.getEmail(), (Client) u.clone());
            }
            else {
                Driver d = (Driver) u.clone();
                if (company == null)
                    this.driversP.put(u.getEmail(), d);
                else this.companies.get(company).addDriver(d);

                this.allDrivers.put(u.getEmail(), d);
            }
            return true;
        }
        else return false;
    }

    /**
     * Regista um novo veículo, se ele não existir
     * @param v Veículo a registar
     * @return Registou o veículo com sucesso (true) ou ele já existe (false)
     */
    public boolean registerVehicleP(Vehicle v){
        if (this.allVehicles.get(v.getLicencePlate()) == null){
            Vehicle vehicle = v.clone();
            this.vehiclesP.put(vehicle.getLicencePlate(), vehicle);
            this.allVehicles.put(vehicle.getLicencePlate(), vehicle);
            return true;
        }
        else return false;
    }

    /**
     * Regista uma empresa
     * @param name Nome da empresa
     * @return Registou com sucesso (true) ou empresa já existe (false)
     */
    public boolean registerCompany(String name, String password){
        if (this.companies.get(name) == null && this.allUsers().get(name) == null){
            Company c = new Company(name, password);
            this.companies.put(name, c.clone());
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
        if (this.companies.get(company) != null && this.allVehicles.get(v.getLicencePlate()) == null){
            Vehicle vehicle = v.clone();
            vehicle.setOwner(company);
            this.allVehicles.put(vehicle.getLicencePlate(), vehicle);
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
        if (u instanceof Client) {
            this.clients.remove(u);
        }
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
     * Adiciona uma viagem ao conjunto de viagens totais
     * @param t Viagem a adicionar
     */
    public void addTrip(Trip t){
        this.trips.add(t);
    }

    /**
     * Cancela uma viagem (fila de espera) de um cliente
     * @param client Cliente
     */
    public void cancelTripQueue(String client){
        this.allVehicles.get(this.clients.get(client).getQueue()).removeClient(client);
        this.clients.get(client).setQueue(null);
    }

    /**
     * Retonar o tax o taxi mais próximo disponível
     * @param client  Cliente que pretende o taxi
     * @return Vehicle mais próximo (null se estiverem todos ocupados)
     */
    public String closestAvailableTaxi(Client client){
        double min = Integer.MAX_VALUE;
        String closestTaxi = null;
        for (Vehicle vehicle : this.allVehicles.values()){
            if (vehicle.isAvailable()) {
                if (vehicle.getPosition().distance(client.getPosition()) < min){
                    min = vehicle.getPosition().distance(client.getPosition());
                    closestTaxi = vehicle.getLicencePlate();
                }
            }
        }
        return closestTaxi;
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
        for (Vehicle vehicles : this.allVehicles.values()) {
            if (vehicles.getPosition().distance(v.getPosition()) <= radius) {
                traffic++;
            }
        }
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
        distD.addValues(1, (100 - d.getTimeCompliance())/100);
        int driverSuccess = distD.pickNumber();

        distC.addValues(0, v.getReliable()/100);
        distC.addValues(1, (100 - v.getReliable())/100);
        int carSuccess = distC.pickNumber();

        double driverSkillChance = d.getTotalDistance() / d.getExp();
        distS.addValues(1, driverSkillChance);
        distS.addValues(0, 1 - driverSkillChance);
        int driverSkill = distS.pickNumber();

        ThreadLocalRandom random = ThreadLocalRandom.current();

        double driverMultiplier       = random.nextDouble(0.1, 0.2);
        double carMultiplier          = random.nextDouble(0.1, 0.2);
        double driverSkillMultiplier  = random.nextDouble(0.1, 0.2);
        double weatherMultiplier      = random.nextDouble(0, 0.2);
        double trafficMultiplier      = Math.abs(random.nextGaussian());

        double trafficDelay = Math.min(traffic * trafficMultiplier / 100, 0.5);
        double weatherDelay = this.weather * (weatherMultiplier / 5);

        double multiplier = 0.9 + driverSuccess*driverMultiplier + carSuccess*carMultiplier
            + trafficDelay + weatherDelay - driverSkill*driverMultiplier;

        double realTime = eta*multiplier;
        return realTime;
    }

    /**
     * Realiza uma viagem
     * @param client        Cliente
     * @param driver        Condutor
     * @param vehicle       Veículo
     * @param destination   Destino
     * @return Viagem realizada
     */
    public Trip newTrip(Client client, Driver driver, Vehicle vehicle, Point2D.Double destination){
        if (!driver.isAvailable()){
            vehicle.addClient(client.getEmail(), client.getPosition(), destination);
            return null;
        }

        LocalDate date = LocalDate.now();
        Trip trip;

        //Times
        double etaToClient = estimatedTime(vehicle.getPosition(), client.getPosition(), vehicle.getSpeed());
        double realTimeToClient = realTime(vehicle.getPosition(), client.getPosition(), driver, vehicle);
        double etaToDest = estimatedTime(client.getPosition(), destination, vehicle.getSpeed());
        double realTimeToDest = realTime(client.getPosition(), destination, driver, vehicle);

        double timeDiffPercentage =  (realTimeToClient + realTimeToDest)/ (etaToClient + etaToDest) ;

        //Price
        double price = 2;
        double distanceToDest = client.getPosition().distance(destination);

        if (timeDiffPercentage <= 1.25)
            price += vehicle.getPrice() * distanceToDest * timeDiffPercentage;
        else price += (vehicle.getPrice() * distanceToDest) - (vehicle.getPrice() * (distanceToDest/2) * (timeDiffPercentage-1));

        trip = new Trip(this.tripID++, client.getPosition(), destination, realTimeToDest,
                price, date, vehicle.getLicencePlate(), driver.getEmail(), client.getEmail(), -1, etaToDest, vehicle.getPosition(), etaToClient, realTimeToClient);

        return trip;
    }

    /**
     ** Realiza uma viagem para um condutor especifico
     * @param client            Cliente
     * @param driverEmail       Email do condutor
     * @param destination       Destino
     */
    public Trip newTripSpecific(Client client, String driverEmail, Point2D.Double destination){
        Driver driver = this.allDrivers.get(driverEmail);
        if (driver != null){
            Vehicle vehicle = this.allVehicles.get(driver.getVehicle());
            if (vehicle.isAvailable())
                return newTrip(client, driver, vehicle, destination);
            else {
                vehicle.addClient(client.getEmail(), client.getPosition(), destination);
                this.clients.get(client.getEmail()).setQueue(vehicle.getLicencePlate());
            }
        }
        return null;
    }

    public void addQueue(String c, String d, Point2D.Double start, Point2D.Double end){
        String v = this.allDrivers.get(d).getVehicle();
        this.allVehicles.get(v).addClient(c, start, end);
        clients.get(c).setQueue(v);
    }

    /**
     * Realiza uma viagem com o veiculo disponivel mais próximo
     * @param client            Cliente
     * @param destination       Destino
     */
    public Trip newTripClosest(Client client, Point2D.Double destination){
        Vehicle vehicle = null;

        vehicle = allVehicles.get(closestAvailableTaxi(client));

        if (vehicle != null) {
            Driver d = allDrivers.get(vehicle.getOwner());
            if (d != null)
                return newTrip(client, allDrivers.get(vehicle.getOwner()), vehicle, destination);
            else {
                Company c = companies.get(vehicle.getOwner());
                d = allDrivers.get(c.pickDriver());
                if (d != null)
                    return newTrip(client, d, vehicle, destination);
                else return null;
            }
        }

        else return null;
    }

    /**
     * Adiciona uma viagem a todos os intervenientes
     * @param c Cliente
     * @param d Condutor
     * @param v Veículo
     * @param t Viagem
     */
    public void addTrip(String c, String d, String v, Trip t){
        this.addTrip(t);
        this.clients.get(c).addTrip(t);
        this.allDrivers.get(d).addTrip(t);
        this.allVehicles.get(v).addTrip(t);

        String company = allDrivers.get(d).getCompany();
        if (company != null)
            this.companies.get(company).addTrip(t);
    }

    /**
     * Adiciona uma classificação a um condutor
     * @param d         Condutor
     * @param rating    Classificação
     */
    public void addRating(String d, int rating, int tripID){
        this.allDrivers.get(d).addRating(rating);
        this.trips.get(tripID).setRating(rating);
    }

    public ArrayList<Client> topClient(){
        List<Client> list = new ArrayList<Client>(this.clients.values());
        Collections.sort(list,new Comparator<Client>(){
            @Override
            public int compare(Client c1, Client c2) {
                if(c1.getMoney() < c2.getMoney()) return 1;
                else return -1;
            }
        });

        int n = Math.min(list.size(), 10);

        ArrayList<Client> top = new ArrayList<>(n);
        for(int i=0; i<n; i++){
            top.add(i,list.get(i));
        }

        return top;
    }

    /**
     * Irá executar a primeira viagem da fila de espera de um condutor
     * @param d Email do condutor
     * @return Realizou a viagem com sucesso (true) ou não existem clientes na fila (false)
     */
    public boolean doTripQueue(String d){
        Trip t = null;
        Driver driver = this.allDrivers.get(d);
        Vehicle vehicle = this.allVehicles.get(driver.getVehicle());

        if (vehicle.getQueue().size() > 0) {
            Client client = this.clients.get(vehicle.getQueue().getFirst());
            client.setPosition(vehicle.getQueueInfo().get(client.getEmail()).get(0));
            t = newTrip(client, driver, vehicle, vehicle.getQueueInfo().get(client.getEmail()).get(1));
            addTrip(client.getEmail(), driver.getEmail(), vehicle.getLicencePlate(), t);
            vehicle.removeClient(client.getEmail());
            client.setQueue(null);
            return true;
        }
        else return false;
    }

    /**
     * Realiza todas as viagens numa queue
     * @param d Condutor
     */
    public void doAllTripsQueue(String d){
        while(doTripQueue(d));
    }


    /**
     * Métodos que asseguram persistência.
     *
     */

    /**
     * Método que guarda o estado de uma instância num ficheiro de texto.
     *
     * @param nome do ficheiro
     */

    public void escreveEmFicheiroTxt(String nomeFicheiro) throws IOException {
        PrintWriter fich = new PrintWriter(nomeFicheiro);
        fich.println("------- UMeR --------");
        fich.println(this.toString()); // ou fich.println(this);
        fich.flush();
        fich.close();
    }



    /**
     * Método que guarda em ficheiro de objectos o objecto que recebe a mensagem.
     */


    public void guardaEstado(String nomeFicheiro) throws FileNotFoundException,IOException {
        FileOutputStream fos = new FileOutputStream(nomeFicheiro);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this); //guarda-se todo o objecto de uma só vez
        oos.flush();
        oos.close();
    }


    /**
     * Método que recupera uma instância de UMeR de um ficheiro de objectos.
     * Este método tem de ser um método de classe que devolva uma instância já construída de
     * UMeR.
     *
     * @param nome do ficheiro onde está guardado um objecto do tipo UMeR
     * @return objecto UMeR inicializado
     */

    public static UMeR carregaEstado(String nomeFicheiro) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(nomeFicheiro);
        ObjectInputStream ois = new ObjectInputStream(fis);
        UMeR u = (UMeR) ois.readObject();
        ois.close();
        return u;
    }

}
