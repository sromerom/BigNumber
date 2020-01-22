public class BigNumber {
    private String s;
    // Constructor 1
    public BigNumber(String s) {
        this.s = s;
    }

    // Constructor 2
    public BigNumber(BigNumber b) {

    }

    // Suma
    BigNumber add(BigNumber other) {
        return new BigNumber("");
    }

    // Resta
    BigNumber sub(BigNumber other) {
        return new BigNumber("");
    }

    // Multiplica
    BigNumber mult(BigNumber other) {
        return new BigNumber("");
    }

    // Divideix
    BigNumber div(BigNumber other) {
        return new BigNumber("");
    }

    BigNumber sqrt() {
        return new BigNumber("");
    }

    BigNumber power(int other) {
        return new BigNumber("");
    }

    BigNumber factorial() {
        return new BigNumber("");
    }

    BigNumber mcd (BigNumber other) {
        return new BigNumber("");
    }

    // Compara dos BigNumber. Torna 0 si són iguals, -1 si el primer és menor
// i torna 1 si el segon és menor
    public int compareTo(BigNumber other) {
        return 0;
    }

    // Torna un String representant el número
    public String toString() {
        return this.s;
    }

    // Mira si dos objectes BigNumber són iguals
    public boolean equals(Object other) {

        return false;
    }
}