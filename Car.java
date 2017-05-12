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
        this.setLicencePlate(null);
        this.setSpeed(80);
        this.setPrice(1.30);
        this.setReliable(0);
        this.setAvailable(true);
        this.setSeats(4);
        this.setPosition(new Point2D.Double(0, 0));
    }

    /**
     * Cria um Carro passado os parâmetros
     * @param licencePlate Matrícula
     * @param reliable     Fator de fiabilidade
     * @param position     Posição atual
     */
    public Car(String licencePlate, double reliable, Point2D.Double position){
        this.setLicencePlate(licencePlate);
        this.setReliable(reliable);
        this.setAvailable(true);
        this.setPosition(new Point2D.Double(position.getX(), position.getY()));
        this.setSeats(4);
        this.setSpeed(80);
        this.setPrice(1.10);
    }

    /**
     * Constroi um Carro a partir de um já existente
     * @param v Carro já definido
     */
    public Car(Car v) {
        this.setLicencePlate(v.getLicencePlate());
        this.setSpeed(v.getSpeed());
        this.setPrice(v.getPrice());
        this.setReliable(v.getReliable());
        this.setAvailable(v.isAvailable());
        this.setSeats(v.getSeats());
        this.setPosition(v.getPosition());
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
