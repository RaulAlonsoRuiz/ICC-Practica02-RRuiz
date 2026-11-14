import java.util.Scanner;

/**
 * Programa que simula una inversion de dinero. Pide el nombre del
 * inversionista, el monto y las fechas de inicio y de fin, y muestra
 * cuantos dias dura la inversion y el rendimiento que daria al 12% anual.
 * Al terminar se pregunta si se desea hacer otra simulacion.
 * @author Raul Alonso Ruiz D santillana
 * @version 13/noviembre/2026
 */
public class TiempoDeInversion {
    /**
     * Metodo principal: dirige la simulacion de la inversion.
     * @param pps - argumentos de la linea de comandos; no se utilizan
     */
    public static void main(String[] pps) {
        // Constantes
        final double TASA = 0.12;        // rendimiento anual: 12%
        final int DIAS_POR_ANIO = 365;

        // Objetos: el lector del teclado y las dos fechas de la inversion
        Scanner in = new Scanner(System.in);
        Fecha inicio = new Fecha();
        Fecha fin = new Fecha();

        // Variables
        String nombre, respuesta;
        double monto, rendimiento;
        int dia, mes, anio;       // datos de la fecha que se esta leyendo
        int dias;                 // dias que dura la inversion
        boolean valida;           // indica si el dato leido es correcto
        long centavos;            // rendimiento redondeado, en centavos
        String textoRendimiento;  // rendimiento escrito con dos decimales

        // Pasos (a) y (b): bienvenida y nombre del inversionista.
        // Se repite la pregunta si solo se tecleo Enter o espacios.
        System.out.println("Bienvenido al simulador de inversiones.");
        do {
            System.out.print("Escribe tu nombre: ");
            nombre = in.nextLine();
            nombre = nombre.trim();
        } while (nombre.length() == 0);

        // Cada vuelta de este ciclo es una simulacion completa
        do {
            // Pasos (c) y (d): saludo y monto a invertir (debe ser positivo)
            System.out.println();
            System.out.println("Hola, " + nombre + ".");
            do {
                System.out.print("Cuanto dinero deseas invertir? ");
                monto = in.nextDouble();
                valida = monto > 0;
                if (!valida) {
                    System.out.println("El monto debe ser mayor que cero.");
                }
            } while (!valida);

            // Pasos (e) y (f): fecha de inicio. Se repite hasta que exista.
            System.out.println("Fecha de inicio de la inversion:");
            do {
                System.out.print("  Dia: ");
                dia = in.nextInt();
                System.out.print("  Mes (numero del 1 al 12): ");
                mes = in.nextInt();
                System.out.print("  Anio: ");
                anio = in.nextInt();
                valida = inicio.esFechaValida(dia, mes, anio);
                if (!valida) {
                    System.out.println("Esa fecha no existe o el anio no esta entre "
                            + Fecha.ANIO_MINIMO + " y " + Fecha.ANIO_MAXIMO
                            + ". Intenta de nuevo.");
                }
            } while (!valida);
            inicio.asignarFecha(dia, mes, anio);

            // Pasos (g) y (h): fecha de fin. Debe existir y no ser anterior
            // a la fecha de inicio.
            System.out.println("Fecha de fin de la inversion:");
            do {
                System.out.print("  Dia: ");
                dia = in.nextInt();
                System.out.print("  Mes (numero del 1 al 12): ");
                mes = in.nextInt();
                System.out.print("  Anio: ");
                anio = in.nextInt();
                valida = fin.esFechaValida(dia, mes, anio);
                if (!valida) {
                    System.out.println("Esa fecha no existe o el anio no esta entre "
                            + Fecha.ANIO_MINIMO + " y " + Fecha.ANIO_MAXIMO
                            + ". Intenta de nuevo.");
                } else {
                    fin.asignarFecha(dia, mes, anio);
                    valida = fin.diferenciaEnDias(inicio) >= 0;
                    if (!valida) {
                        System.out.println("La fecha de fin no puede ser anterior "
                                + "a la de inicio. Intenta de nuevo.");
                    }
                }
            } while (!valida);

            // Paso (i): dias de inversion y rendimiento al 12%.
            dias = fin.diferenciaEnDias(inicio);
            rendimiento = monto * TASA * dias / DIAS_POR_ANIO;

            // Se redondea a centavos y se escribe con dos decimales
            // (por ejemplo 98.6 se muestra como 98.60).
            centavos = (long) (rendimiento * 100 + 0.5);
            textoRendimiento = "$" + centavos / 100 + ".";
            if (centavos % 100 < 10) {
                textoRendimiento = textoRendimiento + "0";
            }
            textoRendimiento = textoRendimiento + centavos % 100;

            System.out.println();
            System.out.println("Inicio de la inversion: " + inicio);
            System.out.println("Fin de la inversion: " + fin);
            System.out.println("Tu inversion durara " + dias + " dias.");
            System.out.println("Con un rendimiento anual del 12%, ganarias "
                    + textoRendimiento + ".");

            // Paso (j): otra simulacion?
            System.out.println();
            System.out.print("Deseas hacer otra simulacion? (si/no): ");
            in.nextLine();    // descarta el salto de linea que dejo nextInt
            respuesta = in.nextLine();
            respuesta = respuesta.toLowerCase();
            respuesta = respuesta.trim();
        } while (respuesta.equals("si"));

        System.out.println("Hasta luego, " + nombre + ".");
    }
}
