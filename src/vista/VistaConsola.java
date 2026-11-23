package vista;

import java.util.Scanner;
import modelo.Paciente;

public class VistaConsola {

    private final Scanner teclado = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println();
        System.out.println("CLÍNICA UNIVERSITARIA: CATÁLOGO DE EXPEDIENTES");
        System.out.println("1. Registrar expediente");
        System.out.println("2. Cargar expedientes de ejemplo");
        System.out.println("3. Mostrar catálogo");
        System.out.println("4. Ordenar catálogo");
        System.out.println("5. Comparar los tres algoritmos");
        System.out.println("0. Salir");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public int leerEntero(String mensaje, int minimo, int maximo) {
        while (true) {
            System.out.print(mensaje + ": ");
            String texto = teclado.nextLine().trim();
            try {
                int valor = Integer.parseInt(texto);
                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }
                System.out.println("Error: el valor debe estar entre " + minimo + " y " + maximo + ".");
            } catch (NumberFormatException e) {
                System.out.println("Error: debe escribir un número entero.");
            }
        }
    }

    public String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje + ": ");
            String texto = teclado.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Error: este dato no puede quedar vacío.");
        }
    }

    public int seleccionarCriterio() {
        System.out.println("Criterio de ordenamiento:");
        System.out.println("1. Número de expediente");
        System.out.println("2. Nombre del paciente");
        System.out.println("3. Edad");
        System.out.println("4. Número de consultas");
        return leerEntero("Seleccione el criterio", 1, 4);
    }

    public int seleccionarOrden() {
        System.out.println("Tipo de orden:");
        System.out.println("1. Ascendente");
        System.out.println("2. Descendente");
        return leerEntero("Seleccione el orden", 1, 2);
    }

    public int seleccionarAlgoritmo() {
        System.out.println("Algoritmo de ordenamiento:");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Selection Sort");
        System.out.println("3. Insertion Sort");
        return leerEntero("Seleccione el algoritmo", 1, 3);
    }

    public void mostrarCatalogo(String titulo, Paciente[] lista, int cantidad) {
        System.out.println();
        System.out.println(titulo);
        System.out.printf("%-12s %-20s %-6s %-16s %-10s%n",
                "Expediente", "Paciente", "Edad", "Especialidad", "Consultas");
        for (int i = 0; i < cantidad; i++) {
            Paciente p = lista[i];
            System.out.printf("%-12d %-20s %-6d %-16s %-10d%n", p.getNumeroExpediente(),
                    p.getNombre(), p.getEdad(), p.getEspecialidad(), p.getNumeroConsultas());
        }
    }

    public void mostrarResultado(String algoritmo, int comparaciones, int movimientos,
                                 long nanosegundos) {
        System.out.println();
        System.out.println("Algoritmo utilizado: " + algoritmo);
        System.out.println("Comparaciones realizadas: " + comparaciones);
        System.out.println("Movimientos realizados: " + movimientos);
        System.out.printf("Tiempo aproximado: %.2f microsegundos%n", nanosegundos / 1000.0);
    }

    public void mostrarComparativo(String[] nombres, int[] comparaciones, int[] movimientos,
                                   long[] tiempos) {
        System.out.println();
        System.out.println("COMPARACIÓN DE DESEMPEÑO");
        System.out.printf("%-16s %-14s %-12s %-12s%n",
                "Algoritmo", "Comparaciones", "Movimientos", "Tiempo (µs)");
        for (int i = 0; i < nombres.length; i++) {
            System.out.printf("%-16s %-14d %-12d %-12.2f%n",
                    nombres[i], comparaciones[i], movimientos[i], tiempos[i] / 1000.0);
        }
    }
}
