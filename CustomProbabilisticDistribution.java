import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Uma distribuição de probabilidades
 */

public class CustomProbabilisticDistribution {

    /** Variáveis de instância */
    private int size;
    private int values[];
    private int currentPos;

    /** Contrutores */

    /**
     * Constroi um nova distrinuição de probabilidades
     */
    public CustomProbabilisticDistribution(){
        this.size = 100;
        this.values = new int[this.size];
        this.currentPos = 0;
    }

    /**
     * Adiciona valores inteiros de acordo com a probabilidade fornecida
     * @param value         Valor a adicionar
     * @param probability   Probabilidade desse valor
     */
    public void addValues(int value, double probability){
        int n = (int) probability * 100;
        while (n > 0 &&  this.currentPos < this.size){
            this.values[this.currentPos] = value;
            this.currentPos++;
            n--;
        }
    }

    /**
     * Retorna um número de acordo com a probabilidade fornecida
     * @return Número escolhido
     */
    public int pickNumber(){
        int random = ThreadLocalRandom.current().nextInt(0, this.size);
        return this.values[random];
    }
}
