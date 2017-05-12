import java.awt.geom.Point2D;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.lang.Comparable;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Write a description of class UMeR here.
 */

public class UMeR {

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

    public HashMap<String, User> allUsers(){
        HashMap<String, User> allUsers = new HashMap<>();
        for (Client client : this.clients.values())
            allUsers.put(client.getEmail(), client.clone());
        for (Driver driver : this.driversP.values())
            allUsers.put(driver.getEmail(), driver.clone());
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
     * Faz um pedido para o taxi mais próximo disponível
     * @param client  Cliente que pretende o taxi
     * @return Driver mais próximo (null se estiverem todos ocupados)
     */
    public String closestAvailableTaxi(Client client){
        double min = Integer.MAX_VALUE;
        String closestTaxi = null;
        for (Vehicle vehicle : this.allVehicles.values()){
            if (vehicle.isAvailable() == true) {
                if (vehicle.getPosition().distance(client.getPosition()) < min){
                    min = vehicle.getPosition().distance(client.getPosition());
                    closestTaxi = vehicle.getLicencePlate();
                }
            }
        }
        System.out.println(closestTaxi);
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
            vehicle.addClient(client);
            return null;
        }

        LocalDate date = LocalDate.now();
        Trip trip;

        //Times
        double etaToDest = estimatedTime(client.getPosition(), destination, vehicle.getSpeed());
        double realTimeToDest = realTime(client.getPosition(), destination, driver, vehicle);
        double timeDiffPercentage =  realTimeToDest / etaToDest ;

        //Price
        double price = 2;
        double distanceToDest = client.getPosition().distance(destination);
        if (timeDiffPercentage <= 1.25)
            price += vehicle.getPrice() * distanceToDest * timeDiffPercentage;
        else price = (vehicle.getPrice() * distanceToDest) - (vehicle.getPrice() * distanceToDest * (timeDiffPercentage-1));

        //TODO: Adicionar rating -->fazer diretamente na GUI
        trip = new Trip(this.tripID++, client.getPosition(), destination, realTimeToDest,
                price, date, vehicle.getLicencePlate(), driver, client, -1);

        return trip;
    }

    /**
     * Realiza uma viagem para um condutor especifico
     * @param c                 Cliente
     * @param driverEmail       Email do condutor
     * @param destination       Destino
     */
    public void newTripSpecific(Client c, String driverEmail, Point2D.Double destination){
        for (Driver driver : this.allDrivers.values()) {
            if (driver.getEmail() == driverEmail) {
                Vehicle vehicle = this.allVehicles.get(driver.getVehicle());
                if (driver.isAvailable() && vehicle.isAvailable()) {
                    newTrip(c, driver, vehicle, destination);
                }
                if (!driver.isAvailable());
                if (driver.isAvailable() && !vehicle.isAvailable()) {
                    vehicle.addClient(c);
                }
            }
        }
    }

    /**
     * Realiza uma viagem com o veiculo disponivel mais próximo
     * @param c                 Cliente
     * @param destination       Destino
     */
    public Trip newTripClosest(Client c, Point2D.Double destination){
        double min = Integer.MAX_VALUE;
        Vehicle closestTaxi = null;
        for (Vehicle vehicle : this.allVehicles.values()){
            if (vehicle.isAvailable() == true) {
                if (vehicle.getPosition().distance(c.getPosition()) < min){
                    min = vehicle.getPosition().distance(c.getPosition());
                    closestTaxi = vehicle;
                }
            }
        }
		Trip trip = newTrip(c, this.allDrivers.get(closestTaxi.getOwner()), closestTaxi, destination);
		return trip;
    }

    public void topClient(){
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

        printTop(top);

    }

    public void printTop (ArrayList<Client> top){
        for(Client c : top){
            System.out.print("Utilizador - " + c.getName() + " Gastou: " + c.getMoney() + " euros" + "\n");
        }

    }
}
