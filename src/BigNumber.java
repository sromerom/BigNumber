import javafx.scene.effect.Blend;

public class BigNumber {
    private String s;
    // Constructor 1
    public BigNumber(String s) {
        this.s = s;
    }

    // Constructor 2
    public BigNumber(BigNumber b) {
        this.s = b.s;
    }

    // Suma
    BigNumber add(BigNumber other) {
        String res = "";
        //StringBuilder sb = new StringBuilder();
        String s1 = llevaZeros(this.s);
        String s2 = llevaZeros(other.s);

        //Preparam la suma
        if (s1.length() > s2.length()) {
            s2 = preparaSuma(s2, s1.length());
        } else if (s1.length() < s2.length()) {
            s1 = preparaSuma(s1, s2.length());
        }

        int aux = 0;
        //Començam a operar
        for (int i = 1; i < s1.length() + 1; i++) {
            int actualXifraS1 = Character.getNumericValue(s1.charAt(s1.length() - i));
            int actualXifraS2 = Character.getNumericValue(s2.charAt(s2.length() - i));
            System.out.println("s1: " + actualXifraS1);
            System.out.println("s2: " +  actualXifraS2);

            //Començam a operar
            int sumaActual = actualXifraS1 + actualXifraS2 + aux;
            aux = 0;
            if (sumaActual < 10) {
                //sb.append(sumaActual);
                res = res + sumaActual;
            } else if (sumaActual > 9 && i < s1.length()) {
                String seperaDigits = Integer.toString(sumaActual);
                int desena = Character.getNumericValue(seperaDigits.charAt(0));
                int unitat = Character.getNumericValue(seperaDigits.charAt(1));

                //sb.append(unitat);
                res = res + unitat;
                aux = desena;
            } else if (sumaActual > 9 && i == s1.length()) {
                //sb.append(giraResultat(Integer.toString(sumaActual)));
                res = res + giraResultat(Integer.toString(sumaActual));
            }
        }

        return new BigNumber(giraResultat(res));
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

    public String preparaSuma(String s, int maxim) {
        String res = "";
        int afegir = maxim - s.length();
        for (int i = 0; i < afegir; i++) {
            res = res + "0";
        }

        for (int i = 0; i < s.length(); i++) {
            String actualXifra = Character.toString(s.charAt(i));
            res = res + actualXifra;
        }
        return res;
    }

    public String giraResultat(String s) {
        String res = "";
        for (int i = 1; i < s.length() + 1; i++) {
            String actualXifra = Character.toString(s.charAt(s.length() - i));
            res = res + actualXifra;
        }
        System.out.println(res);
        return res;
    }
}