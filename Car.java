import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.*;

/**
 * Car for UMeR.
 */

public class Car extends Vehicle {

    /** CONSTRUTORES */

    /**
     * Cria um Carro sem parâmetros
     */
    public Car() {
        super();
        this.setSpeed(65);
        this.setPrice(1.5);
        this.setSeats(4);
    }

    /**
     * Constroi um Carro passado os parâmetros
     * @param licencePlate Matrícula
     * @param reliable     Fator de fiabilidade
     * @param position     Posição atual
     */
    public Car(String licencePlate, double reliable, Point2D.Double position, String owner) {
        super(licencePlate, reliable, position, true, null, null, null, owner);
        this.setSpeed(65);
        this.setPrice(1.5);
        this.setSeats(4);
    }

    /**
     * Constroi uma Carro a partir de uma já existente
     * @param v Carro
     */
    public Car(Car v) {
        super(v.getLicencePlate(), v.getReliable(), v.getPosition(), v.isAvailable(), v.getQueue(), v.getQueueInfo(), v.getTrips(), v.getOwner());
        this.setSpeed(65);
        this.setPrice(1.5);
        this.setSeats(4);
    }

    /**
     * Faz a cópia de um carro
     * @return Cópia de Carro
     */
    public Car clone(){
        return new Car(this);
    }

    /**
     * Calcula o trânsito à volta de um carro
     * @param vehicles Map de todos os veículos
     * @return Nível de trânsito
     */
    public int calculateTraffic(HashMap<String,Vehicle> vehicles){
        double minDist = 2;
        int traffic = 1;
        for (Vehicle v : vehicles.values())
            if (v.getPosition().distance(this.getPosition()) <= minDist);
                traffic++;

        return traffic;
    }
}
