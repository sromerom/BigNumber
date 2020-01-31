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
                } else if (operacio > 9 && j < s1.length()) {
                    String seperaDigits = Integer.toString(operacio);
                    int desena = Character.getNumericValue(seperaDigits.charAt(0));
                    int unitat = Character.getNumericValue(seperaDigits.charAt(1));

                    //sb.append(unitat);
                    res = res + unitat;
                    aux = desena;
                } else if (operacio > 9 && j == s1.length()) {
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
        String s1 = llevaZeros(this.s);
        String s2 = llevaZeros(other.s);
        System.out.println(s1);
        System.out.println(s2);

        if (s1.length() == s2.length()) {
            return new BigNumber("1");
        }

        if (s1.length() < s2.length()) {
            return new BigNumber("0");
        }

        ////////////////BigNumber dividend = new BigNumber(Character.toString(s1.charAt(0)));
        BigNumber dividend = new BigNumber(s1);
        BigNumber divisor = new BigNumber(s2);
        BigNumber resultatDivisio = new BigNumber("");
        BigNumber partDividend = new BigNumber("");
        int posicionament = 0;

        for (int j = 0; j < s2.length() + 1; j++) {
            posicionament++;
            partDividend.s = partDividend.s + Integer.toString(Character.getNumericValue(s1.charAt(j)));
            if (partDividend.compareTo(divisor) == 1) {
                break;
            }
        }

        while (posicionament <= s1.length()) {
            /*
            System.out.println(dividend.compareTo(divisor));
            while (dividend.compareTo(divisor) == -1) {
                if (i == 0) {
                    i++;
                    continue;
                }
                dividend.s += Character.getNumericValue(s1.charAt(i));
                i++;
            }
             */
            System.out.println(partDividend.s);
            if (partDividend.compareTo(divisor) == -1) {
                System.out.println("cero al quocient i baixam la xifra següent");
                resultatDivisio.s = resultatDivisio.s + 0;
                if (posicionament != s1.length()) {
                    partDividend.s = partDividend.s + Character.toString(s1.charAt(posicionament));
                    posicionament++;
                } else {
                    posicionament++;
                }
                continue;
            }

            int quocient = 1;
            BigNumber resultatQuocient = new BigNumber("1");

            System.out.println(resultatQuocient);
            System.out.println(partDividend);
            while (resultatQuocient.compareTo(partDividend) == -1) {
                resultatQuocient = divisor.mult(new BigNumber(Integer.toString(quocient)));
                System.out.println(resultatQuocient);
                if (divisor.mult(new BigNumber(Integer.toString(quocient + 1))).compareTo(partDividend) == 1) {
                    break;
                }
                quocient++;
            }

            BigNumber resta = new BigNumber("");
            resta = partDividend.sub(resultatQuocient);
            //resta.s = llevaZeros(resta.s); //OJO!!!!!! ESTA FUNCION QUITA TODOS LOS CEROS SIEMPRE, LO QUE QUIERE DECIR QUE EN RESIDUOS EXACTOS ELIMINA ESOS CEROS!!


            resultatDivisio.s = resultatDivisio.s + quocient;
            //resta.s = resta.s + Integer.toString(Character.getNumericValue(s1.charAt(i)));
            if (posicionament == s1.length()) {
                break;
            } else {
                partDividend.s = resta.s + Character.toString(s1.charAt(posicionament));
            }
            partDividend.s = llevaZeros(partDividend.s);
            System.out.println(partDividend.s);
            posicionament++;
        }
        return resultatDivisio;
    }

    BigNumber sqrt() {
        String s1 = this.s;
        System.out.println(s1);
        int posicionament = 0;
        String quocient = "";
        BigNumber xifraActual = new BigNumber("");
        boolean afegirNumParells = true;
        boolean mesPetit = false;

        if (s1.length() == 1) {
            xifraActual.s = Character.toString(s1.charAt(0));
        } else {

            if (s1.length() % 2 == 0) {
                xifraActual.s = Character.toString(s1.charAt(posicionament)) + Character.toString(s1.charAt(posicionament + 1));
            } else {
                xifraActual.s = Character.toString(s1.charAt(posicionament));
                afegirNumParells = false;
            }
        }
        BigNumber res = new BigNumber(xifraActual.s);
        BigNumber resQuocient = new BigNumber("");
        while (posicionament < s1.length()) {
            if (posicionament != 0) {
                xifraActual = new BigNumber(Character.toString(s1.charAt(posicionament)) + Character.toString(s1.charAt(posicionament + 1)));
                //xifraActual.s = giraResultat(xifraActual.s);
                //res.s = giraResultat(res.s);

                //xifraActual.s = xifraActual.s + res.s;
                xifraActual.s = res.s + xifraActual;
                afegirNumParells = true;
            }
            BigNumber resta = new BigNumber("");
            if (posicionament == 0) {
                quocient = comprovarQuocient(xifraActual.s);
                resQuocient.s = resQuocient + quocient;
                resta = new BigNumber(quocient);
            } else {
                resta = new BigNumber(resQuocient.mult(new BigNumber("2")));

                if (new BigNumber(resta.s + "1").compareTo(xifraActual) == -1) {
                    int aux = 1;
                    //BigNumber aux = new BigNumber("1");
                    BigNumber auxResult = new BigNumber(resta.s);
                    while (auxResult.compareTo(xifraActual) == -1) {
                        //resta.s = resta.s + aux.s;
                        //resta = resta.mult(aux);
                        //aux.add(new BigNumber("1"));
                        auxResult.s = resta.s;
                        auxResult.s = auxResult.s + Integer.toString(aux);
                        auxResult = auxResult.mult(new BigNumber(Integer.toString(aux)));
                        if (new BigNumber(resta.s + Integer.toString(aux + 1)).mult(new BigNumber(Integer.toString(aux + 1))).compareTo(xifraActual) == 1) {
                            break;
                        }
                        aux++;
                    }
                    quocient = quocient + Integer.toString(aux);
                    resta.s = auxResult.s;
                } else {
                    quocient = quocient + "0";
                    resQuocient.s = quocient;
                    res.s = xifraActual.s;
                    if (afegirNumParells) { //Codigo Bastante repetido!!
                        posicionament += 2;
                    } else {
                        posicionament++;
                    }
                    continue;
                }
            }

            System.out.println(xifraActual + " - " + resta);

            if (posicionament == 0) {
                res = xifraActual.sub(resta.mult(resta));
            } else {
                res = xifraActual.sub(resta);
            }

            /*
            if (res.compareTo(new BigNumber("0")) == 0) {
                quocient = quocient + "0";
                return new BigNumber(quocient);
            }
             */
            if (afegirNumParells) {
                posicionament += 2;
            } else {
                posicionament++;
            }
            res.s = llevaZeros(res.s);
            resQuocient.s = quocient;
        }
        return new BigNumber(quocient);
    }

    BigNumber power(int n) {
        BigNumber res = new BigNumber(this.s);
        BigNumber base = new BigNumber(this.s);
        BigNumber exponent = new BigNumber(Integer.toString(n));

        if (n == 2) {
            res = res.mult(res);
        } else {
            for (int i = 1; i < n; i++) {
                res = res.mult(base);
            }
        }
        return res;
    }

    BigNumber factorial() {
        BigNumber res = new BigNumber("1");
        BigNumber base = new BigNumber(this.s);
        BigNumber aux = new BigNumber("");

        for (int i = Integer.parseInt(base.s); i > 0; i--) {
            aux.s = Integer.toString(i);
            res = res.mult(aux);
        }
        return res;
    }

    BigNumber mcd(BigNumber other) {
        BigNumber s1 = new BigNumber(this.s);
        BigNumber s2 = new BigNumber(other.s);

        while (s1.compareTo(s2) != 0) {
            if (s1.compareTo(s2) == -1) {
                //n2 -= n1;
                s2 = s2.sub(s1);
            } else {
                //n1 -= n2;
                s1 = s1.sub(s2);
            }
        }

        return s1;
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

    public String comprovarQuocient(String s) {
        BigNumber xifraComprovar = new BigNumber(s);
        BigNumber result = new BigNumber("1");

        int quocient = 1;
        BigNumber resultatQuocient = new BigNumber("1");

        System.out.println(resultatQuocient);
        System.out.println(xifraComprovar);
        while (resultatQuocient.compareTo(xifraComprovar) == -1) {
            resultatQuocient = result.mult(result);


            if (new BigNumber(Integer.toString(quocient + 1)).mult(new BigNumber(Integer.toString(quocient + 1))).compareTo(xifraComprovar) == 1) {
                break;
            }
            quocient++;
            result.s = Integer.toString(quocient);
        }
        return Integer.toString(quocient);
    }

}