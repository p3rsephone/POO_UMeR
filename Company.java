import java.util.HashMap;

/**
 * Company of UMeR
 */

public class Company {
    private String name;
    private HashMap<String, Driver> drivers;
    private HashMap<String, Vehicle> vehicles;
    private double moneyGenerated;
    private int totalTrips;

    /**
     * Criar uma nova empresa a partir do nome
     * @param name Nome da empresa
     */
    public Company(String name){
        this.name = name;
        this.moneyGenerated = 0;
        this.totalTrips = 0;
        this.drivers = new HashMap<>();
        this.vehicles = new HashMap<>();
    }

    /**
     *Cria uma nova empresa a partir de uma já definida
     * @param c Empresa já definida
     */
    public Company(Company c){
        this.name = c.getName();
        this.drivers = new HashMap<>(c.getDrivers());
        this.vehicles = new HashMap<>(c.getVehicles());
        this.moneyGenerated = moneyGenerated;
        this.totalTrips = totalTrips;
    }

    /**
     * Retorna o nome da empresa
     * @return Nome da empresa
     */
    public String getName(){
        return this.name;
    }

    /**
     * Retorna uma cópia do Map de condutores
     * @return Map de condutores
     */
    public HashMap<String, Driver> getDrivers(){
        HashMap<String, Driver> drivers = new HashMap<>();
        for (Driver d: this.drivers.values())
            drivers.put(d.getEmail(), d.clone());
        return drivers;
    }

    /**
     * Retorna uma cópia do Map de veículos
     * @return Map de veículos
     */
    public HashMap<String, Vehicle> getVehicles(){
        HashMap<String, Vehicle> vehicles = new HashMap<>();
        for (Vehicle v: this.vehicles.values())
            vehicles.put(v.getRegistration(), v.clone());
        return vehicles;
    }

    /**
     * Retorna a quantidade de dinehiro gerado
     * @return Dinheiro gerado
     */
    public double getMoneyGenerated(){
        return this.moneyGenerated;
    }

    /**
     * Retorna o número de viagens totais
     * @return Número de viagens
     */
    public int getTotalTrips() {
        return this.totalTrips;
    }

    /**
     * Imprime a informação de uma empresa
     * @return String com a informação da empresa
     */
    public String toString(){
        return "Empresa " + this.name + "\n" +
                "---Condutores " + "\n" + printDrivers() + "\n" +
                "---Veículos + " + "\n" + printVehicles() + "\n" +
                "Número de viagens : " + this.totalTrips + "\n" +
                "Dinheiro gerado : " + this.moneyGenerated + "\n";
    }

    /**
     * Verifica se duas empresas são iguais
     * @param c Empresa a comparar
     * @return São iguais (true) ou não (false)
     */
    public boolean equals(Company c){
        return c.getName().equals(this.getName());
    }

    public Company clone(){
        return new Company(this);
    }

    /**
     * Imprime o email de todos os condutores da empresa
     * @return String com o email dos condutores
     */
    public String printDrivers(){
        String s = null;
        for (Driver d: this.drivers.values())
            s += d.getEmail() + "\n";
        return s;
    }

    /**
     * Imprime a matrícula de todos os veículos da empresa
     * @return String com a matrícula dos veículos
     */
    public String printVehicles(){
        String s = null;
        for (Vehicle d: this.vehicles.values())
            s += d.getRegistration() + "\n";
        return s;
    }

    /**
     * Adiciona um condutor à empresa
     * @param d Condutor a adicionar
     * @return O condutor foi adicionado (true) ou já existia (false)
     */
    public boolean addDriver(Driver d){
        int size = this.drivers.size();
        this.drivers.put(d.getEmail(), d);

        if (size != this.drivers.size())
            return true;
        else return false;
    }

    /**
     * Adiciona um veículo à empresa
     * @param d Veículo a adicionar
     * @return O veículo foi adicionado (true) ou já existia (false)
     */
    public boolean addVehicle(Vehicle v){
        int size = this.vehicles.size();
        this.vehicles.put(v.getRegistration(), v);

        if (size != this.vehicles.size())
            return true;
        else return false;
    }

    /**
     * Incrementa o número total de viagens
     */
    public void incrementTotalTrips(){
        this.totalTrips++;
    }

    /**
     * Adiciona um quantia ao número total de dinheiro gerado
     * @param money
     */
    public void addMoney(double money){
        this.moneyGenerated += money;
    }
}
