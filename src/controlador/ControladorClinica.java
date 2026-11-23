package controlador;

import modelo.Ordenador;
import modelo.Paciente;
import vista.VistaConsola;

public class ControladorClinica {

    private static final int CAPACIDAD = 50;
    private static final String[] NOMBRES_ALGORITMOS =
            {"Bubble Sort", "Selection Sort", "Insertion Sort"};

    private final Paciente[] expedientes = new Paciente[CAPACIDAD];
    private int total = 0;
    private final Ordenador ordenador = new Ordenador();
    private final VistaConsola vista = new VistaConsola();

    public void iniciar() {
        int opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.leerEntero("Seleccione una opción", 0, 5);
            switch (opcion) {
                case 1:
                    registrarExpediente();
                    break;
                case 2:
                    cargarEjemplo();
                    break;
                case 3:
                    mostrarCatalogo();
                    break;
                case 4:
                    ordenarCatalogo();
                    break;
                case 5:
                    compararAlgoritmos();
                    break;
                default:
                    vista.mostrarMensaje("Fin del programa.");
            }
        } while (opcion != 0);
    }

    private void registrarExpediente() {
        if (total == CAPACIDAD) {
            vista.mostrarMensaje("Error: el catálogo está lleno.");
            return;
        }
        int numero = vista.leerEntero("Número de expediente", 1, 999999);
        if (existeExpediente(numero)) {
            vista.mostrarMensaje("Error: el expediente " + numero + " ya está registrado.");
            return;
        }
        String nombre = vista.leerTexto("Nombre del paciente");
        int edad = vista.leerEntero("Edad", 0, 120);
        String especialidad = vista.leerTexto("Especialidad médica");
        int consultas = vista.leerEntero("Número de consultas realizadas", 0, 1000);

        expedientes[total] = new Paciente(numero, nombre, edad, especialidad, consultas);
        total++;
        vista.mostrarMensaje("Expediente registrado correctamente.");
    }

    private boolean existeExpediente(int numero) {
        for (int i = 0; i < total; i++) {
            if (expedientes[i].getNumeroExpediente() == numero) {
                return true;
            }
        }
        return false;
    }

    private void cargarEjemplo() {
        if (total + 8 > CAPACIDAD) {
            vista.mostrarMensaje("Error: no hay espacio suficiente en el catálogo.");
            return;
        }
        Paciente[] ejemplo = {
            new Paciente(1042, "Carlos Mendoza", 45, "Cardiología", 12),
            new Paciente(1015, "Ana Torres", 8, "Pediatría", 5),
            new Paciente(1088, "Luis Ramírez", 63, "Traumatología", 9),
            new Paciente(1003, "Sofía Vargas", 29, "Ginecología", 3),
            new Paciente(1067, "Jorge Castillo", 52, "Neurología", 12),
            new Paciente(1029, "María López", 34, "Dermatología", 7),
            new Paciente(1051, "Diego Navarro", 8, "Pediatría", 4),
            new Paciente(1076, "Elena Ruiz", 71, "Oftalmología", 15)
        };
        for (int i = 0; i < ejemplo.length; i++) {
            if (!existeExpediente(ejemplo[i].getNumeroExpediente())) {
                expedientes[total] = ejemplo[i];
                total++;
            }
        }
        vista.mostrarMensaje("Expedientes de ejemplo cargados.");
    }

    private void mostrarCatalogo() {
        if (total == 0) {
            vista.mostrarMensaje("El catálogo está vacío. Registre expedientes primero.");
            return;
        }
        vista.mostrarCatalogo("CATÁLOGO DE EXPEDIENTES", expedientes, total);
    }

    private void ordenarCatalogo() {
        if (total < 2) {
            vista.mostrarMensaje("Error: se necesitan al menos 2 expedientes para ordenar.");
            return;
        }
        int criterio = vista.seleccionarCriterio();
        boolean ascendente = vista.seleccionarOrden() == 1;
        int algoritmo = vista.seleccionarAlgoritmo();

        vista.mostrarCatalogo("CATÁLOGO ANTES DEL ORDENAMIENTO", expedientes, total);

        Paciente[] copia = copiarCatalogo();
        long inicio = System.nanoTime();
        ejecutarAlgoritmo(algoritmo, copia, criterio, ascendente);
        long duracion = System.nanoTime() - inicio;

        vista.mostrarCatalogo("CATÁLOGO DESPUÉS DEL ORDENAMIENTO", copia, copia.length);
        vista.mostrarResultado(NOMBRES_ALGORITMOS[algoritmo - 1],
                ordenador.getComparaciones(), ordenador.getMovimientos(), duracion);
    }

    private void compararAlgoritmos() {
        if (total < 2) {
            vista.mostrarMensaje("Error: se necesitan al menos 2 expedientes para comparar.");
            return;
        }
        int criterio = vista.seleccionarCriterio();
        boolean ascendente = vista.seleccionarOrden() == 1;

        int[] comparaciones = new int[3];
        int[] movimientos = new int[3];
        long[] tiempos = new long[3];

        for (int i = 0; i < 3; i++) {
            Paciente[] copia = copiarCatalogo();
            long inicio = System.nanoTime();
            ejecutarAlgoritmo(i + 1, copia, criterio, ascendente);
            tiempos[i] = System.nanoTime() - inicio;
            comparaciones[i] = ordenador.getComparaciones();
            movimientos[i] = ordenador.getMovimientos();
        }
        vista.mostrarComparativo(NOMBRES_ALGORITMOS, comparaciones, movimientos, tiempos);
        vista.mostrarMensaje("Menos comparaciones: "
                + NOMBRES_ALGORITMOS[posicionMenor(comparaciones)]);
        vista.mostrarMensaje("Menos movimientos: "
                + NOMBRES_ALGORITMOS[posicionMenor(movimientos)]);
    }

    private void ejecutarAlgoritmo(int algoritmo, Paciente[] datos, int criterio,
                                   boolean ascendente) {
        if (algoritmo == 1) {
            ordenador.burbuja(datos, criterio, ascendente);
        } else if (algoritmo == 2) {
            ordenador.seleccion(datos, criterio, ascendente);
        } else {
            ordenador.insercion(datos, criterio, ascendente);
        }
    }

    // Se ordena una copia para conservar el catálogo original
    private Paciente[] copiarCatalogo() {
        Paciente[] copia = new Paciente[total];
        for (int i = 0; i < total; i++) {
            copia[i] = expedientes[i];
        }
        return copia;
    }

    private int posicionMenor(int[] valores) {
        int posicion = 0;
        for (int i = 1; i < valores.length; i++) {
            if (valores[i] < valores[posicion]) {
                posicion = i;
            }
        }
        return posicion;
    }
}
