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
            System.out.println("s2: " + actualXifraS2);

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

        System.out.println("s1 length: " + s1.length());
        System.out.println("s2 length: " + s2.length());
        int aux = 0;
        for (int i = 1; i < s1.length() + 1; i++) {
            int actualXifraS1 = Character.getNumericValue(s1.charAt(s1.length() - i));
            int actualXifraS2 = Character.getNumericValue(s2.charAt(s2.length() - i));
            System.out.println("s1: " + actualXifraS1);
            System.out.println("s2: " + actualXifraS2);

            int operacio = 0;
            if (actualXifraS1 == actualXifraS2 || actualXifraS1 > actualXifraS2) {
                if (actualXifraS1 == actualXifraS2 && aux == 1) {
                    operacio = (actualXifraS1 + 10 - aux) - actualXifraS2;
                    aux = 1;
                } else {
                    operacio = (actualXifraS1 - aux) - actualXifraS2;
                    aux = 0;
                }
            } else {
                operacio = (actualXifraS1 + 10 - aux) - actualXifraS2;
                aux = 1;

            }

            res = res + operacio;
        }

        System.out.println("resultat sense girar: " + res);
        return new BigNumber(giraResultat(res));
    }

    // Multiplica
    BigNumber mult(BigNumber other) {
        String s1 = llevaZeros(this.s);
        String s2 = llevaZeros(other.s);

        System.out.println(s1);
        System.out.println(s2);
        int operacio = 0;
        String res = "";
        int aux = 0;
        BigNumber sumaOperacionsTotals = new BigNumber("0");
        for (int i = 1; i < s2.length() + 1; i++) {
            res = "";
            String s2Xifra = Character.toString(s2.charAt(s2.length() - i));
            for (int j = 1; j < s1.length() + 1; j++) {
                String s1Xifra = Character.toString(s1.charAt(s1.length() - j));
                System.out.println(s2Xifra);
                System.out.println(s1Xifra);

                operacio = Integer.parseInt(s1Xifra) * Integer.parseInt(s2Xifra) + aux;
                aux = 0;
                if (operacio < 10) {
                    //sb.append(sumaActual);
                    res = res + operacio;
                } else if (operacio > 9 && i < s1.length()) {
                    String seperaDigits = Integer.toString(operacio);
                    int desena = Character.getNumericValue(seperaDigits.charAt(0));
                    int unitat = Character.getNumericValue(seperaDigits.charAt(1));

                    //sb.append(unitat);
                    res = res + unitat;
                    aux = desena;
                } else if (operacio > 9 && i == s1.length()) {
                    //sb.append(giraResultat(Integer.toString(sumaActual)));
                    res = res + giraResultat(Integer.toString(operacio));
                }
            }


            res = giraResultat(res);
            res = afegeixZeros(res, i - 1);
            BigNumber b = new BigNumber(res);
            sumaOperacionsTotals = b.add(sumaOperacionsTotals);
            System.out.println(sumaOperacionsTotals.toString());
        }
        System.out.println("Resultat: " + sumaOperacionsTotals);
        return sumaOperacionsTotals;
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

    BigNumber mcd(BigNumber other) {
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
                if (s1.charAt(i) != s2.charAt(i)) {
                    if (s1.charAt(i) > s2.charAt(i)) {
                        return 1;
                    } else {
                        return -1;
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

    public String afegeixZeros(String s, int quantitatZeros) {
        String res = s;
        for (int i = 0; i < quantitatZeros; i++) {
            res = res + 0;
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