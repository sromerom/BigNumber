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
        String s1 = llevaZeros(this.s);
        String s2 = llevaZeros(other.s);

        if (s1.length() > s2.length()) {
            return 1;
        } else if (s1.length() < s2.length()) {
            return -1;
        } else { //Son d'igual tamany
            for (int i = 0; i < s1.length(); i++) {
                int actualXifraS1 = Character.getNumericValue(s1.charAt(i));
                int actualXifraS2 = Character.getNumericValue(s2.charAt(i));
                //System.out.println("Son iguales? " + actualXifraS1 + " --> " + actualXifraS2);
                if (actualXifraS1 != actualXifraS2) {
                    if (actualXifraS1 > actualXifraS2) {
                        return 1;
                    } else if (actualXifraS1 < actualXifraS2) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            }
        }
        return 0;
    }

    // Torna un String representant el número
    public String toString() {
        return this.getS();
    }

    // Mira si dos objectes BigNumber són iguals
    public boolean equals(Object other) {

        if (other instanceof BigNumber) {
            BigNumber b = (BigNumber) other;
            if (llevaZeros(this.s).equals(llevaZeros(b.s))) {
                return true;
            }
        }
        return false;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String llevaZeros(String s) {

        String res = "";
        boolean principi = true;
        for (int i = 0; i < s.length(); i++) {
            int actualDigit = Character.getNumericValue(s.charAt(i));
            //System.out.println(actualDigit);
            if (actualDigit == 0 && principi) {

            } else {
                principi = false;
                res = res + actualDigit;
            }

        }
        return res;
    }
}