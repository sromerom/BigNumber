public class BigNumber {
    private String number;

    // Constructor 1
    public BigNumber(String number) {
        this.number = number;
    }

    // Constructor 2
    public BigNumber(BigNumber b) {
        this.number = b.number;
    }

    // Suma
    BigNumber add(BigNumber other) {
        String res = "";

        //Comprovam si els numeros introduits son correctes (si son nomes numeros)
        //Ho farem amb el metode numberCorrecte()
        if (!numberCorrecte(this.number) || !numberCorrecte(other.number)) {
            return new BigNumber("0");
        }


        //Assignam el string number dels dos objectes en dos variables diferents per tal de treballar millor amb ells i quedi mes clar. Si es dona el cas que aquest numero tengui
        // zero davant, els llevarem per poder fer l'operacio. Ho farem amb el metode llevarZeros()
        String s1 = llevaZeros(this.number);
        String s2 = llevaZeros(other.number);

        //Si es dona el cas que els dos numeros a sumar no son d'igual tamany, cridarem a la funcio preparaSuma que ens afegira zeros al numero mes petit, fent que els numeros
        // a sumar siguin exactament d'igual tamany sempre, a més de no alterar la suma final
        if (s1.length() > s2.length()) {
            s2 = preparaOperacio(s2, s1.length());
        } else if (s1.length() < s2.length()) {
            s1 = preparaOperacio(s1, s2.length());
        }


        int ensLlevam = 0;
        //Començam a operar
        for (int i = 1; i < s1.length() + 1; i++) {

            //Aconsguim els numeros de la columna a sumar (Podem emprar int perque sempre sera un numero d'un digit)
            int actualXifraS1 = Character.getNumericValue(s1.charAt(s1.length() - i));
            int actualXifraS2 = Character.getNumericValue(s2.charAt(s2.length() - i));

            //Una vegada tenim els dos numeros començar a operar
            int sumaActual = actualXifraS1 + actualXifraS2 + ensLlevam;
            ensLlevam = 0;

            // Si la suma dels dos numeros anteriors es menor a 10 voldra dir que no ens llevarem cap i no fara sumar un extra a l'operacio següent.
            if (sumaActual < 10) {
                res = res + sumaActual;

                // Si la suma es major a 10 i no es l'ultim digit (o columna) de l'operacio, voldra dir que haurem de guardar el que ens llevam per sumar a la següent suma de numeros.
            } else if (i < s1.length()) {

                //Començam a separar el digit resultant en unitat i desenes. Aquest digit sempre sera un digit entre 10 i 99
                String separament = Integer.toString(sumaActual);
                int desena = Character.getNumericValue(separament.charAt(0));
                int unitat = Character.getNumericValue(separament.charAt(1));

                //La unitat la assignarem al resultat final i la desena sera el numero que sumarem en la següent operació. (ens llevam)
                res = res + unitat;
                ensLlevam = desena;

                // Si la suma es major a 10 i es final de la suma (l'ultima columna), simplement assignarem el resultat de la suma complet. Aquest resultat ho haurem de girar i ho farem
                // amb el metode giraResultat()
            } else if (i == s1.length()) {
                res = res + giraResultat(Integer.toString(sumaActual));
            }
        }

        return new BigNumber(llevaZeros(giraResultat(res)));
    }

    // Resta
    BigNumber sub(BigNumber other) {
        String res = "";

        //Comprovam els numeros...
        if (!numberCorrecte(this.number) || !numberCorrecte(other.number)) {
            return new BigNumber("0");
        }


        // A igual que la suma, assignam el strings a s1 i s2. També llevarem el zeros si es que n'hi ha
        String s1 = llevaZeros(this.number);
        String s2 = llevaZeros(other.number);

        //Feim exactament igual que a la suma, si qualsevol numero no es d'igual tamany, afegirem zeros fins que ho sigui i poguem operar
        if (s1.length() > s2.length()) {
            s2 = preparaOperacio(s2, s1.length());
        } else if (s1.length() < s2.length()) {
            s1 = preparaOperacio(s1, s2.length());
        }

        int restarem = 0;
        for (int i = 1; i < s1.length() + 1; i++) {

            //Aconsguirem els dos numeros que operarem. Com en la suma, aquest numeros no mes serà d'una xifra i no passa res si treballam amb int
            int actualXifraS1 = Character.getNumericValue(s1.charAt(s1.length() - i));
            int actualXifraS2 = Character.getNumericValue(s2.charAt(s2.length() - i));

            int operacio = 0;

            //Si el numero d'adalt es major o igual al numero d'abaix, simplement farem un resta normal i corrent
            if (actualXifraS1 == actualXifraS2 || actualXifraS1 > actualXifraS2) {

                //Si es dona el cas que hem de restar  a causa d'una operacio anterior, i el resultat de la resta es igual a 0 (o els dos numeros a restar son iguals)
                // doncs haurem de sumar 10 per poder restar els que ens llevam. En aquest cas ens llevarem 1 per restar posterior
                if (actualXifraS1 == actualXifraS2 && restarem == 1) {
                    operacio = (actualXifraS1 + 10 - restarem) - actualXifraS2;
                    restarem = 1;

                    //Si no ens hem portat per restar, farem l'operacio resta normal (tenint en compte si hem de restar un extra a causa de l'operacio anterior)
                    // Quan fa l'operacio, seguidament posam a 0 "restarem"
                } else {
                    operacio = (actualXifraS1 - restarem) - actualXifraS2;
                    restarem = 0;
                }

                //Si es numero d'abaix es major al d'adalt, si o si haurem de llevar-nos 1 per restar. I per poder fer l'operacio sumarem 10
            } else {
                operacio = (actualXifraS1 + 10 - restarem) - actualXifraS2;
                restarem = 1;

            }

            // I sumam a la variable res l'operacio que s'ha fet
            res = res + operacio;
        }

        return new BigNumber(llevaZeros(giraResultat(res)));
    }

    // Multiplica
    BigNumber mult(BigNumber other) {
        BigNumber resultatMultiplicacio = new BigNumber("");
        BigNumber res = new BigNumber("0");

        //Comprovam els numeros...
        if (!numberCorrecte(this.number) || !numberCorrecte(other.number)) {
            return new BigNumber("0");
        }


        //Com fins ara, assignam els numeros a s1 i s2, llevant-li els possibles zeros
        String s1 = llevaZeros(this.number);
        String s2 = llevaZeros(other.number);

        //Comprovam els numeros...
        if (!numberCorrecte(s1) || !numberCorrecte(s2)) {
            return new BigNumber("0");
        }

        //Variable "operacio" per guardar l'operacio resultat, variable "ensLlevam" per saber quan hem de sumar al posterior operació i el BigNumber "res"
        // per guardar totes sumes dels resultats de les multiplicacions
        int operacio = 0;
        int ensLlevam = 0;


        for (int i = 1; i < s2.length() + 1; i++) {
            //Cada vegada que canviam de numero a la fila d'abaix (si es que hi ha més d'un), posam el resultat buit i aconseguim el numero el qual el multiplicarem amb
            // la fila d'adalt
            resultatMultiplicacio.number = "";
            String multiplicarS2 = Character.toString(s2.charAt(s2.length() - i));

            //Recorrem la fila d'adalt, per anant multiplicant per cada un dels numeros de d'adalt per numero que hem aconseguit abans
            for (int j = 1; j < s1.length() + 1; j++) {
                //Anam conseguint el numero per cual es multiplicara, fins que acabi la fila d'adalt (s1)
                String multiplicarS1 = Character.toString(s1.charAt(s1.length() - j));

                //I multiplicam els dos numeros
                operacio = Integer.parseInt(multiplicarS1) * Integer.parseInt(multiplicarS2) + ensLlevam;

                //Una vegada s'ha multiplicat s'ha de posar la variable ensLlevam a 0
                ensLlevam = 0;

                //Si l'operacio resultant es menor a 10, voldra dir que no hem de llevar-nos resultatMultiplicacio. Simplement assignam l'operacio resultant a la variable "resultatMultiplicacio"
                if (operacio < 10) {
                    resultatMultiplicacio.number = resultatMultiplicacio.number + operacio;

                    //mentres j sigui menor al tamany de s1 (fila d'adalt), haurem de aconsguir la desena i la unitat, i anar llevant-nos la desena i guardar la unitat a resultatMultiplicacio
                } else if (j < s1.length()) {
                    String seperaDigits = Integer.toString(operacio);
                    int desena = Character.getNumericValue(seperaDigits.charAt(0));
                    int unitat = Character.getNumericValue(seperaDigits.charAt(1));

                    resultatMultiplicacio.number = resultatMultiplicacio.number + unitat;
                    ensLlevam = desena;
                } else { // Si es dona el cas que l'operacio es major a 10 pero és l'ultim numero, posarem el resultat le l'operacio tal i com esta (ja que es el final
                    // i no hem de seguir operant)
                    resultatMultiplicacio.number = resultatMultiplicacio.number + giraResultat(Integer.toString(operacio));
                }
            }

            //Per cada multiplicació que feim, hem d'anar afegint zeros. Per cada operacio que feim afegirem un 0. A més, com hem estat afegint directament el resultat a un string
            // toca invertir el resultat i ho farem amb giraResultat()
            resultatMultiplicacio.number = afegeixZeros(giraResultat(resultatMultiplicacio.number), i - 1);

            //I anam guardant els resultat de les sumes en res
            res = resultatMultiplicacio.add(res);
        }

        return res;
    }

    // Divideix
    BigNumber div(BigNumber other) {

        //Comprovam els numeros...
        if (!numberCorrecte(this.number) || !numberCorrecte(other.number)) {
            return new BigNumber("0");
        }


        //Tornam a treballar amb s1 i s2...
        String s1 = llevaZeros(this.number);
        String s2 = llevaZeros(other.number);

        //Comprovam els numeros...
        if (!numberCorrecte(s1) || !numberCorrecte(s2)) {
            return new BigNumber("0");
        }

        // Si el dos numeros a dividir son exactament igual, el resultat sempre sera 1
        if (s1.equals(s2)) {
            return new BigNumber("1");
        }

        // En canvi si s1 es mes petit que s1, donara decimal. Es limitara ens retornar 0
        if (s1.length() < s2.length()) {
            return new BigNumber("0");
        }


        BigNumber divisor = new BigNumber(s2);
        BigNumber resultatDivisio = new BigNumber("");
        BigNumber partDividend = new BigNumber("");

        //La variable posicionament es la variable mes important de l'algoritme. Ens permet saber en quin numero del dividend i determina quan s'acaba el bucle
        int posicionament = 0;

        // Aquest bucle ens permet saber quin sera la part del dividend que començarem a operar. Aquesta part sempre sera del mateix tamany del dividor o tamany + 1
        for (int j = 0; j < s2.length() + 1; j++) {
            posicionament++;
            partDividend.number = partDividend.number + Integer.toString(Character.getNumericValue(s1.charAt(j)));
            if (partDividend.compareTo(divisor) == 1) {
                break;
            }
        }

        while (posicionament <= s1.length()) {

            // Si la part del dividend que hem d'operar es mes petit que el numero del dividor, haurem de posar un zero quocient i baixar la xifra següent
            if (partDividend.compareTo(divisor) == -1) {
                //Afegim 0 al quocient
                resultatDivisio.number = resultatDivisio.number + 0;

                //Mentres el posicionament sigui mes petit al tamany del dividend baixarem el següent numero i augmantarem el posicionament en 1
                if (posicionament < s1.length()) {
                    partDividend.number = partDividend.number + Character.toString(s1.charAt(posicionament));
                    posicionament++;
                } else { // Si es igual o major al dividend, voldra dir que no hi ha mes numeros per baixar i per tant, simplement augmentarem el posicionament
                    posicionament++;
                }
                continue;
            }

            //Cridam a la funcio calculaQuocient() per a que ens calculi l'actual quocient. Com que aquesta funcio pot calcular dos coses diferents, farem que
            // calculi el quocient d'una divisio especificant al parametre boolean com true
            int quocient = calculaQuocient(divisor, partDividend, true);

            //Feim la resta entre l'actual partDividend i el resultat que ens ha donat el quocient
            BigNumber resultatResta = new BigNumber("");
            resultatResta = partDividend.sub(divisor.mult(new BigNumber(Integer.toString(quocient))));

            //I guardam el quocient que hem aconseguit al BigNumber resultatDivisio
            resultatDivisio.number = resultatDivisio.number + quocient;

            //Si el posicionament es menor, voldra dir que encara podem baixar numeros
            if (posicionament < s1.length()) {
                //Baixam el següent numero que apunta el posicionament. Si baixam un numero, haurem de incrementar el posicionament
                partDividend.number = resultatResta.number + Character.toString(s1.charAt(posicionament));
                posicionament++;
            } else { // Si fos el cas que no es menor, voldra dir que no hi ha mes numeros i que hem de terminar el bucle
                break;
            }

        }
        return resultatDivisio;
    }

    //ArrelQuadrada
    BigNumber sqrt() {

        //Comprovam el numero...
        if (!numberCorrecte(this.number)) {
            return new BigNumber("0");
        }

        String s1 = this.number;

        int posicionament = 0;
        BigNumber xifraActual = new BigNumber("");
        BigNumber res = new BigNumber(xifraActual.number);
        BigNumber resQuocient = new BigNumber("");

        // Si el numero que hem fer l'arrel es 0, retornarem automaticament 0
        if (s1.equals("0")) {
            return new BigNumber("0");
        }

        // Si el tamany del numero es d'un digit, nomes farem un charAt de 0
        if (s1.length() == 1) {
            xifraActual.number = Character.toString(s1.charAt(0));
        } else { // Si el tamany es major a 1, haurem de determinar si es parell o senar

            if (s1.length() % 2 == 0) { // Si es parell, el numero inicial constara de dos numeros, per tant, de 0 i 1
                xifraActual.number = Character.toString(s1.charAt(0)) + Character.toString(s1.charAt(1));
            } else { // I si es senar, només agafarem un, charAt(0)
                xifraActual.number = Character.toString(s1.charAt(0));
            }

        }

        String quocient = "";
        while (posicionament < s1.length()) {
            BigNumber operacioResta = new BigNumber("");


            // Si el posicionament es igual a zero, voldra dir que es la primera vegada. Per calcular arrels, la primera vegada simplement hem de encontrar un numero que multplicat
            // per si mateix s'aproximi al maxim a la xifraActual. Aixo només es fa la primera vegada. Desprès l'algoritme canvia un poc
            if (posicionament == 0) {

                //Calculam el quocient que mes s'aproximi i el guardam en resQuocient (el resultat final)
                //Com que no volem calcular el quocient d'una divisio, especificarem que calcularem el quocient d'una arrel i ho farem assignant com a false el parametre boolea
                // El primer parametre no ho s'utilitza
                quocient = Integer.toString(calculaQuocient(new BigNumber(""), xifraActual, false));
                resQuocient.number = resQuocient + quocient;

                //El numero que restarem la primera vegada sempre sera el quocient multiplicat per si mateix
                operacioResta = new BigNumber(resQuocient.mult(resQuocient));

            } else {
                // Per conseguir la xifraActual i poder començar operar, haurem de aconseguir els dos numeros següents i sumar aquest dos numeros al resultat de la resta anterior.
                xifraActual = new BigNumber(Character.toString(s1.charAt(posicionament)) + Character.toString(s1.charAt(posicionament + 1)));
                xifraActual.number = res.number + xifraActual;

                //Una vegada ja es te la xifraActual, ja es pot començar a operar i començar quin sera el numero que més s'aproximi a aquest numero

                //Al començar, sempre hem de multiplicar per dos el resultat que es te actualment
                operacioResta = new BigNumber(resQuocient.mult(new BigNumber("2")));

                //Aquest condicional comprova que la operacio minima que es fara sigui sempre menor a la xifraActual. Si el minim numero
                // que es pot aconseguir provant (operacioResta + 1) es mes gran a la xifraActual, no es podra calcular i s'haura d'anar cap un altre cami
                if (new BigNumber(operacioResta.number + "1").compareTo(xifraActual) == -1) {
                    int aux = 1;
                    BigNumber auxResult = new BigNumber(operacioResta.number);
                    while (auxResult.compareTo(xifraActual) == -1) {

                        auxResult.number = operacioResta.number;
                        auxResult.number = auxResult.number + Integer.toString(aux);
                        auxResult = auxResult.mult(new BigNumber(Integer.toString(aux)));
                        if (new BigNumber(operacioResta.number + Integer.toString(aux + 1)).mult(new BigNumber(Integer.toString(aux + 1))).compareTo(xifraActual) == 1) {
                            break;
                        }
                        aux++;
                    }
                    quocient = quocient + Integer.toString(aux);
                    operacioResta.number = auxResult.number;
                } else { // Si la operacio minima que es pot fer es mes gran que el propi xifraActual, es posara un zero al quocient i assignarem la xifraActual al res.
                    quocient = quocient + "0";
                    resQuocient.number = quocient;

                    res.number = xifraActual.number;
                    posicionament += 2;
                    continue;
                }
            }

            //Restam la xifraActual amb el resultat que ens ha donat operacioResta
            res = xifraActual.sub(operacioResta);

            //Si al principi (posicionament == 0), la xifraActual es només un (a causa de que el numero), augmentarem el posicionament en un, sino, augmentarem en dos el posicionament
            if (xifraActual.number.length() % 2 != 0 && posicionament == 0) {
                posicionament++;
            } else {
                posicionament += 2;
            }

            //Important afegir el resultat que ens ha donat el quocient al BigNumber resQuocient
            resQuocient.number = quocient;
        }
        return resQuocient;
    }

    //Potencia
    BigNumber power(int n) {

        //Comprovam el numero...
        if (!numberCorrecte(this.number)) {
            return new BigNumber("0");
        }

        BigNumber res = new BigNumber(this.number);
        BigNumber base = new BigNumber(this.number);

        //Feim un bucle que vagi multiplicant el BigNumber tantes vegades com n indiqui
        for (int i = 1; i < n; i++) {
            res = res.mult(base);
        }

        return res;
    }

    //Factorial
    BigNumber factorial() {

        //Comprovam el numero...
        if (!numberCorrecte(this.number)) {
            return new BigNumber("0");
        }

        BigNumber res = new BigNumber("1");
        BigNumber base = new BigNumber(this.number);

        //Feim un altre bucle que vagi multiplicant res per la variable i. Quan acabi el bucle, la variable i haura passat per tots els numeros necessaris per fer el factorial
        // d'aquell numero
        for (int i = 1; i < Integer.parseInt(base.number) + 1; i++) {
            res = res.mult(new BigNumber(Integer.toString(i)));
        }

        return res;
    }

    //MCD. Torna el Maxim Comu Divisor
    BigNumber mcd(BigNumber other) {

        //Comprovam els numeros...
        if (!numberCorrecte(this.number) || !numberCorrecte(other.number)) {
            return new BigNumber("0");
        }

        BigNumber s1 = new BigNumber(this.number);
        BigNumber s2 = new BigNumber(other.number);

        // Per fer el calcul del maxim comu divisor s'ha fet servir l'algoritme d'euclides. Es aquest cas s'ha fet restant pero tambe es pot fer dividint
        while (s1.compareTo(s2) != 0) {
            if (s1.compareTo(s2) == -1) {
                s2 = s2.sub(s1);
            } else {
                s1 = s1.sub(s2);
            }
        }

        return s1;
    }

    // Compara dos BigNumber. Torna 0 si són iguals, -1 si és menor i torna 1 si es major
    public int compareTo(BigNumber other) {

        //Treballam amb s1 i s2...
        String s1 = llevaZeros(this.number);
        String s2 = llevaZeros(other.number);

        //Comprovam els numeros... Torna 2 si el numero no es correcte!!
        if (!numberCorrecte(s1) || !numberCorrecte(s2)) {
            return 2;
        }

        //Si s1 es major a s2 directament retornam 1
        if (s1.length() > s2.length()) {
            return 1;
        } else if (s1.length() < s2.length()) { //En el cas contrari, retornam -1
            return -1;
        } else { //En el cas que son exactament del mateix tamany...

            for (int i = 0; i < s1.length(); i++) {
                //El moment que el numero de s1 es diferent a s2, determinarem quin dels dos es el major o el menor. Si es dona aquest cas, ja no podra retorna 0 (iguals)
                if (s1.charAt(i) != s2.charAt(i)) {
                    if (s1.charAt(i) > s2.charAt(i)) {
                        return 1; // Si el numero actual de s1 es major retornarem 1
                    } else {
                        return -1; // si s1 no ho es, retornarem -1
                    }
                }
            }

        }

        //Si ha arribat fins aqui, voldra dir que el numero sera exactament igual
        return 0;
    }

    // Torna un String representant el número
    public String toString() {
        return this.getNumber();
    }

    // Mira si dos objectes BigNumber són iguals
    public boolean equals(Object other) {

        //Despres de comprovar que l'objecte other es un objecte BigNumber...
        if (other instanceof BigNumber) {
            BigNumber b = (BigNumber) other;

            //Comprovam els dos Strings dels dos objectes (del que ha siguit casteat i l'actual) son iguals
            if (llevaZeros(this.number).equals(llevaZeros(b.number))) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    //Getters and Setters
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    //Metodes propis

    //Comprova que el numero introduit sigui correcte. Retorn false si no lo es i true si ho es
    public boolean numberCorrecte(String s) {

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < 47 || s.charAt(i) > 57) {
                return false;
            }
        }

        return true;
    }

    //Torna un string amb tots el zeros inicial llevats
    public String llevaZeros(String s) {

        StringBuilder res = new StringBuilder();
        boolean principi = true;

        //Recorrem el numero sencer
        for (int i = 0; i < s.length(); i++) {
            int actualDigit = Character.getNumericValue(s.charAt(i));

            // si l'actual digit es diferent a 0 i no es el principi, començar a afegir l'actual Digit
            if (actualDigit != 0 || !principi) {
                principi = false;
                res.append(actualDigit);
            }
        }
        return res.toString();
    }

    //Torna un string amb la quantitat de zeros que l'hi indiquem. Aquest zeros s'afegeixen al final del string passat
    public String afegeixZeros(String s, int quantitatZeros) {
        StringBuilder res = new StringBuilder(s);
        res.append("0".repeat(Math.max(0, quantitatZeros)));

        return res.toString();
    }

    //Torna un string amb la quantitat de zeros necessaris per fer que les operacions tenguin el mateix tamany. Util per suma i resta
    public String preparaOperacio(String s, int maxim) {
        StringBuilder res = new StringBuilder();
        int afegir = maxim - s.length();
        res.append("0".repeat(Math.max(0, afegir)));

        for (int i = 0; i < s.length(); i++) {
            String actualXifra = Character.toString(s.charAt(i));
            res.append(actualXifra);
        }
        return res.toString();
    }

    //Torna un string amb el string que l'hi passam per parametre totalment girat
    public String giraResultat(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < s.length() + 1; i++) {
            String actualXifra = Character.toString(s.charAt(s.length() - i));
            res.append(actualXifra);
        }
        return res.toString();
    }

    //Funcio que ens determina quin es el quocient d'un numero. Funciona tant per divisions com per aconseguir el primer quocient dels arrels
    //Si la volem fer servir per divisio posarem el boolean divisio true. Si volem calcular el quocient de l'arrel el divisor ho posarem a 0 i el boolean divisio a false
    public int calculaQuocient(BigNumber divisor, BigNumber part, boolean divisio) {
        int quocient = 1;

        while(true) {
            //Si el resultat del següent quocient es passa feim un break i en quedam amb l'actual quocient (es fa un predicio)

            if (divisio) {
                if (divisor.mult(new BigNumber(Integer.toString(quocient + 1))).compareTo(part) == 1) {
                    break;
                }
            } else {
                if (new BigNumber(Integer.toString(quocient + 1)).mult(new BigNumber(Integer.toString(quocient + 1))).compareTo(part) == 1) {
                    break;
                }
            }
            quocient++;
        }

        return quocient;
    }
}