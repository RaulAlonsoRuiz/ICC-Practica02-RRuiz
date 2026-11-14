/**
 * Clase para crear y manejar fechas formadas por dia, mes y anio.
 * Permite validar una fecha, sumarle dias, calcular la diferencia en dias
 * entre dos fechas, compararlas y mostrarlas en el formato dd/mes/aaaa.
 * @author Raul Alonso Ruiz D santillana
 * @version 13/noviembre/2026
 */
public class Fecha {

    // ------------------------------------------------------------------
    // Constantes
    // ------------------------------------------------------------------
    /** Primer anio permitido. */
    public static final int ANIO_MINIMO = 1000;

    /** Ultimo anio permitido. */
    public static final int ANIO_MAXIMO = 9999;

    // ------------------------------------------------------------------
    // Atributos: el estado de cada fecha. Son privados (encapsulacion);
    // solo se cambian con los metodos asignar... para que la fecha
    // siempre sea valida.
    // ------------------------------------------------------------------
    private int dia;
    private int mes;    // numero del 1 al 12
    private int anio;

    // ------------------------------------------------------------------
    // Constructores
    // ------------------------------------------------------------------

    /**
     * Constructor por omision. Crea la fecha 01/01/2000.
     */
    public Fecha() {
        dia = 1;
        mes = 1;
        anio = 2000;
    }

    /**
     * Constructor a partir del dia, el mes (como numero) y el anio.
     * Si la fecha no es valida avisa con un mensaje y la fecha se queda
     * en 01/01/2000.
     * @param dia - dia del mes
     * @param mes - numero del mes, de 1 a 12
     * @param anio - anio, de 1000 a 9999
     */
    public Fecha(int dia, int mes, int anio) {
        this();                         // primero queda la fecha por omision
        asignarFecha(dia, mes, anio);   // luego se intenta cambiar a la pedida
    }

    /**
     * Constructor a partir del dia, el anio y el nombre del mes. No importa
     * si el nombre viene en mayusculas o con espacios a los lados. Si el
     * mes no existe o la fecha no es valida avisa con un mensaje y la fecha
     * se queda en 01/01/2000.
     * @param dia - dia del mes
     * @param anio - anio, de 1000 a 9999
     * @param nombreMes - nombre del mes, por ejemplo "marzo"
     */
    public Fecha(int dia, int anio, String nombreMes) {
        this();
        int numeroMes = numeroDeMes(nombreMes);
        if (numeroMes == 0) {
            System.out.println("El mes \"" + nombreMes + "\" no existe. "
                    + "No se hizo ningun cambio.");
        } else {
            asignarFecha(dia, numeroMes, anio);
        }
    }

    // ------------------------------------------------------------------
    // Metodos de acceso (obtener los campos)
    // ------------------------------------------------------------------

    /**
     * Devuelve el dia del mes.
     * @return int - el dia de la fecha
     */
    public int obtenerDia() {
        return dia;
    }

    /**
     * Devuelve el numero del mes.
     * @return int - el mes de la fecha, de 1 a 12
     */
    public int obtenerMes() {
        return mes;
    }

    /**
     * Devuelve el anio.
     * @return int - el anio de la fecha
     */
    public int obtenerAnio() {
        return anio;
    }

    // ------------------------------------------------------------------
    // Metodos modificadores (asignar los campos)
    // ------------------------------------------------------------------

    /**
     * Cambia el dia, el mes y el anio a la vez. Solo lo hace si la fecha
     * nueva es valida; si no, avisa con un mensaje y deja la fecha como
     * estaba.
     * @param nuevoDia - nuevo dia del mes
     * @param nuevoMes - nuevo numero de mes, de 1 a 12
     * @param nuevoAnio - nuevo anio, de 1000 a 9999
     */
    public void asignarFecha(int nuevoDia, int nuevoMes, int nuevoAnio) {
        if (esFechaValida(nuevoDia, nuevoMes, nuevoAnio)) {
            dia = nuevoDia;
            mes = nuevoMes;
            anio = nuevoAnio;
        } else {
            System.out.println("La fecha " + nuevoDia + "/" + nuevoMes + "/"
                    + nuevoAnio + " no es valida. No se hizo ningun cambio.");
        }
    }

    /**
     * Cambia solo el dia. Se rechaza, con un aviso, si la fecha resultante
     * no existe.
     * @param nuevoDia - nuevo dia del mes
     */
    public void asignarDia(int nuevoDia) {
        asignarFecha(nuevoDia, mes, anio);
    }

    /**
     * Cambia solo el mes. Se rechaza, con un aviso, si la fecha resultante
     * no existe; por ejemplo, pasar del 31/enero al mes 4.
     * @param nuevoMes - nuevo numero de mes, de 1 a 12
     */
    public void asignarMes(int nuevoMes) {
        asignarFecha(dia, nuevoMes, anio);
    }

    /**
     * Cambia solo el anio. Se rechaza, con un aviso, si la fecha resultante
     * no existe; por ejemplo, 29/febrero en un anio que no es bisiesto.
     * @param nuevoAnio - nuevo anio, de 1000 a 9999
     */
    public void asignarAnio(int nuevoAnio) {
        asignarFecha(dia, mes, nuevoAnio);
    }

    // ------------------------------------------------------------------
    // Validacion
    // ------------------------------------------------------------------

    /**
     * Determina si los datos forman una fecha que existe y cuyo anio esta
     * en el rango permitido. No cambia la fecha del objeto; solo revisa los
     * datos recibidos.
     * @param d - dia del mes
     * @param m - numero del mes
     * @param a - anio
     * @return boolean - true si la fecha existe y el anio esta entre 1000 y
     *         9999, false en otro caso
     */
    public boolean esFechaValida(int d, int m, int a) {
        if (a < ANIO_MINIMO || a > ANIO_MAXIMO) {
            return false;
        }
        if (m < 1 || m > 12) {
            return false;
        }
        return d >= 1 && d <= diasDelMes(m, a);
    }

    /* Dice si el anio a es bisiesto: divisible entre 4, salvo los
     * divisibles entre 100 que no lo sean tambien entre 400. */
    private boolean esBisiesto(int a) {
        return (a % 4 == 0 && a % 100 != 0) || a % 400 == 0;
    }

    /* Devuelve cuantos dias tiene el mes m del anio a. */
    private int diasDelMes(int m, int a) {
        if (m == 2) {
            if (esBisiesto(a)) {
                return 29;
            }
            return 28;
        }
        if (m == 4 || m == 6 || m == 9 || m == 11) {
            return 30;
        }
        return 31;
    }

    // ------------------------------------------------------------------
    // Meses: nombre <-> numero
    // ------------------------------------------------------------------

    /**
     * Devuelve el numero del mes cuyo nombre se recibe. No importa si el
     * nombre viene en mayusculas o con espacios a los lados.
     * @param nombreMes - nombre del mes, por ejemplo "Marzo"
     * @return int - numero del mes (1 a 12), o 0 si el nombre no
     *         corresponde a ningun mes
     */
    public int numeroDeMes(String nombreMes) {
        if (nombreMes == null) {
            return 0;
        }
        String nombre = nombreMes.toLowerCase();
        nombre = nombre.trim();

        if (nombre.equals("enero")) {
            return 1;
        }
        if (nombre.equals("febrero")) {
            return 2;
        }
        if (nombre.equals("marzo")) {
            return 3;
        }
        if (nombre.equals("abril")) {
            return 4;
        }
        if (nombre.equals("mayo")) {
            return 5;
        }
        if (nombre.equals("junio")) {
            return 6;
        }
        if (nombre.equals("julio")) {
            return 7;
        }
        if (nombre.equals("agosto")) {
            return 8;
        }
        if (nombre.equals("septiembre")) {
            return 9;
        }
        if (nombre.equals("octubre")) {
            return 10;
        }
        if (nombre.equals("noviembre")) {
            return 11;
        }
        if (nombre.equals("diciembre")) {
            return 12;
        }
        return 0;   // no es un mes
    }

    /**
     * Devuelve el nombre del mes cuyo numero se recibe.
     * @param numero - numero del mes, de 1 a 12
     * @return String - nombre del mes en minusculas, o "desconocido" si el
     *         numero no esta entre 1 y 12
     */
    public String nombreDeMes(int numero) {
        if (numero == 1) {
            return "enero";
        }
        if (numero == 2) {
            return "febrero";
        }
        if (numero == 3) {
            return "marzo";
        }
        if (numero == 4) {
            return "abril";
        }
        if (numero == 5) {
            return "mayo";
        }
        if (numero == 6) {
            return "junio";
        }
        if (numero == 7) {
            return "julio";
        }
        if (numero == 8) {
            return "agosto";
        }
        if (numero == 9) {
            return "septiembre";
        }
        if (numero == 10) {
            return "octubre";
        }
        if (numero == 11) {
            return "noviembre";
        }
        if (numero == 12) {
            return "diciembre";
        }
        return "desconocido";
    }

    // ------------------------------------------------------------------
    // Calculos con dias
    // ------------------------------------------------------------------

    /**
     * Calcula cuantos dias han transcurrido en el anio hasta esta fecha,
     * contando el dia actual. El 1 de enero es el dia 1; el 30 de diciembre
     * de 2013 es el dia 364.
     * @return int - dias transcurridos del anio, contando este dia
     */
    public int diasTranscurridos() {
        int total = dia;     // dias del mes en curso
        int m = 1;
        if (mes > 1) {       // do-while entra siempre una vez; por eso el if
            do {
                total += diasDelMes(m, anio);   // suma cada mes completo anterior
                m++;
            } while (m < mes);
        }
        return total;
    }

    /**
     * Calcula la diferencia en dias entre esta fecha y otra, es decir,
     * esta fecha menos otra.
     * @param otra - fecha con la que se compara
     * @return int - dias de diferencia; es positiva si esta fecha es
     *         posterior a la otra, negativa si es anterior y 0 si son el
     *         mismo dia
     */
    public int diferenciaEnDias(Fecha otra) {
        return enDias() - otra.enDias();
    }

    /**
     * Calcula la fecha que queda n dias despues de esta (o antes, si n es
     * negativo). La fecha original no cambia.
     * @param n - cantidad de dias a sumar; un valor negativo resta dias
     * @return Fecha - fecha nueva; si el resultado se sale de los anios
     *         permitidos avisa con un mensaje y devuelve una copia de la
     *         fecha original
     */
    public Fecha sumarDias(int n) {
        long destino = (long) enDias() + n;   // long para que la suma no se desborde
        long primero = diasAntesDelAnio(ANIO_MINIMO) + 1;
        long ultimo = diasAntesDelAnio(ANIO_MAXIMO + 1);
        if (destino < primero || destino > ultimo) {
            System.out.println("El resultado queda fuera de los anios permitidos ("
                    + ANIO_MINIMO + " a " + ANIO_MAXIMO + ").");
            return new Fecha(dia, mes, anio);
        }
        return enFecha((int) destino);
    }

    /* Cuenta cuantos dias hay desde el 1/1/0001 hasta el 31/12 del anio
     * anterior a a. Cada anio aporta 365 dias, mas un dia por cada anio
     * bisiesto anterior (multiplos de 4, menos los de 100, mas los de 400). */
    private int diasAntesDelAnio(int a) {
        int previos = a - 1;   // anios completos anteriores
        return previos * 365 + previos / 4 - previos / 100 + previos / 400;
    }

    /* Convierte esta fecha en un numero entero: la cantidad de dias desde
     * el 1/1/0001 (que es el dia 1). Con enteros, sumar o restar dias y
     * comparar fechas es muy facil. */
    private int enDias() {
        return diasAntesDelAnio(anio) + diasTranscurridos();
    }

    /* Hace lo contrario de enDias: convierte el numero de dia n en una
     * fecha. Se recorren anios y meses solo para ajustar una estimacion,
     * no dia por dia. */
    private Fecha enFecha(int n) {
        boolean avanzar;

        // 1) Buscar el anio. La estimacion inicial nunca se pasa del anio
        //    real, asi que solo hay que avanzar mientras n caiga despues
        //    del final del anio a.
        int a = (n - 1) / 366 + 1;
        do {
            avanzar = n > diasAntesDelAnio(a + 1);
            if (avanzar) {
                a++;
            }
        } while (avanzar);

        // 2) Buscar el mes: de los dias que quedan dentro del anio se van
        //    restando meses completos mientras sobren dias.
        int restantes = n - diasAntesDelAnio(a);   // numero de dia dentro del anio
        int m = 1;
        do {
            avanzar = restantes > diasDelMes(m, a);
            if (avanzar) {
                restantes -= diasDelMes(m, a);
                m++;
            }
        } while (avanzar);

        // 3) Lo que queda es el dia del mes.
        return new Fecha(restantes, m, a);
    }

    // ------------------------------------------------------------------
    // Metodos especiales
    // ------------------------------------------------------------------

    /**
     * Determina si esta fecha es igual a otra, es decir, si tienen el mismo
     * dia, mes y anio. Se compara el estado de los objetos, no sus
     * referencias.
     * @param otra - objeto con el que se compara; debe ser una Fecha
     * @return boolean - true si las fechas son iguales, false en otro caso
     */
    public boolean equals(Object otra) {
        Fecha f = (Fecha) otra;
        return dia == f.dia && mes == f.mes && anio == f.anio;
    }

    /**
     * Convierte la fecha en texto con el formato dd/mes/aaaa, donde dd es
     * el dia con dos digitos, mes es el nombre del mes y aaaa es el anio.
     * Ejemplo: 03/septiembre/2013.
     * @return String - la fecha en formato de cadena
     */
    public String toString() {
        String texto = "";
        if (dia < 10) {
            texto = "0";     // rellena con cero para tener dos digitos
        }
        return texto + dia + "/" + nombreDeMes(mes) + "/" + anio;
    }
}
