import java.awt.geom.Point2D;
/**
 * Vehicle class for UMeR.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Vehicle {

    private String registration;
    private double speed;
    private double price;
    private double reliable;
    private boolean available;
    private int seats;
    private Point2D.Double position;


    /** CONSTRUTORES*/

    /**
     * Constroi um veiculo
     */
    public Vehicle() {
        this.registration = null;
        this.speed = 0;
        this.price = 0;
        this.reliable = 0;
        this.available = true;
        this.seats = 0;
        this.position = new Point2D.Double(0, 0);
    }

    /**
     * Constroi um veiculo passado os parâmetros
     *
     * @param registration  Matricula
     * @param speed Velocidade média por km
     * @param price Preço médio por km
     * @param reliable Fator de fiabilidade
     * @param available Disponibilidade
     * @param seats Numeros de lugares
     * @param position Posição
     */
    public Vehicle(String registration, double speed, double price, double reliable, boolean available, int seats, Point2D.Double position) {
        this.registration = registration;
        this.speed = speed;
        this.price = price;
        this.reliable = reliable;
        this.available = available;
        this.seats = seats;
        this.position = new Point2D.Double(position.getX(), position.getY());

    }

    /**
     * Constroi um veiculo a partir de um já existente
     *
     * @param v Veiculo
     */
    public Vehicle(Vehicle v) {
        this.registration = v.getRegistration();
        this.speed = v.getSpeed();
        this.price = v.getPrice();
        this.reliable = v.getReliable();
        this.available = v.isAvailable();
        this.seats = v.getSeats();
        this.position = v.getPosition();
    }

    /**
     * Retorna a matricula de um veiculo
     *
     * @return registration Matricula de Veiculo
     */
    public String getRegistration() {
        return this.registration;
    }

    /**
     * Retorna a velocidade média por km de um veiculo
     *
     * @return speed Velocidade média do Veiculo
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Retorna o preço médio por km de um veiculo
     *
     * @return price Preço do Veiculo
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Retorna o fator de fiabilidade de um veiculo
     *
     * @return reliable Fator de fiabilidade do veiculo
     */
    public double getReliable() {
        return this.reliable;
    }

    /**
     * Retorna a disponibilidade de um veiculo
     *
     * @return available Disponibilidade do veiculo
     */
    public boolean isAvailable() {
        return this.available;
    }

    /**
     * Retorna o numero de lugares de um veiculo
     *
     * @return seats Numeros de lugares do veiculo
     */
    public int getSeats() {
        return this.seats;
    }

    /**
     * Retorna a posição de um veiculo
     *
     * @return position Posiçao do veiculo
     */
    public Point2D.Double getPosition() {
        return new Point2D.Double(this.position.getX(), this.position.getY());
    }


    /**SETTERS*/

    /**
     * Altera a matricula de um veiculo
     *
     * @param registration Nova matricula
     */
    public void setRegistration(String registration) {
        this.registration = registration;
    }

    /**
     * Altera a velocidade de um veiculo
     *
     * @param speed Nova velocidade
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Altera o preço medio por km de um veiculo
     *
     * @param price Nova preço
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Altera o fator de fiabilidade de um veiculo
     *
     * @param reliable Novo fator de fiabilidade
     */
    public void setReliable(double reliable) {
        this.reliable = reliable;
    }

    /**
     * Altera a disponibilidade de um veiculo
     *
     * @param available Nova disponibilidade
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Altera o numero de lugares de um veiculo
     *
     * @param seats Novo numero de lugares
     */
    public void setSeats(int seats) {
        this.seats = seats;
    }

    /**
     * Altera a posição de um veiculo
     *
     * @param position Nova posição
     */
    public void setPosition(Point2D.Double position) {
        this.position = new Point2D.Double(position.getX(), position.getY());
    }


    /**MÉTODOS DE INSTÂNCIA*/

    /**
     * Compara dois veiculos
     *
     * @param v Veiculo
     * @return É o mesmo veiculo (true) ou não (false)
     */
    public boolean equals(Vehicle v) {
        return this.registration.equals(v.getRegistration());
    }


    /**
     * Faz a cópia de um veiculo
     *
     * @return Cópia de Veiculo
     */
    public abstract Vehicle clone();

	/**
     * Calcula o trânsito à volta de um veículo
     *
     * @return Nível de trânsito
     */
	public abstract int calculateTraffic(); //Só vai ajudar a calcular o tempo real


    /**
     * toString de um veiculo
     * @return String
     */
    public String toString(){
        return 	"Matricula - " 		+ this.registration 		+ "\n" +
                "Velocidade média por km : " 				+ this.speed 		+ "\n" +
                "Preço médio por km : " 			+ this.price 	+ "\n" +
                "Fator de fiabilidade : " 			+ this.reliable 		+ "\n" +
                "Disponibilidade : " + this.available 	+ "\n" +
                "Numero de lugares" + this.seats + "\n" +
                "Posição : " 			+ "(X - " + this.position.getX() + ", Y - " + this.position.getY() + ")\n";
    }
}
