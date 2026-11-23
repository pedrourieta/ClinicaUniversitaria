package modelo;

public class Ordenador {

    public static final int POR_EXPEDIENTE = 1;
    public static final int POR_NOMBRE = 2;
    public static final int POR_EDAD = 3;
    public static final int POR_CONSULTAS = 4;

    private int comparaciones;
    private int movimientos;

    public int getComparaciones() {
        return comparaciones;
    }

    public int getMovimientos() {
        return movimientos;
    }

    private void reiniciarContadores() {
        comparaciones = 0;
        movimientos = 0;
    }

    private int comparar(Paciente a, Paciente b, int criterio) {
        switch (criterio) {
            case POR_EXPEDIENTE:
                return Integer.compare(a.getNumeroExpediente(), b.getNumeroExpediente());
            case POR_NOMBRE:
                return a.getNombre().compareToIgnoreCase(b.getNombre());
            case POR_EDAD:
                return Integer.compare(a.getEdad(), b.getEdad());
            default:
                return Integer.compare(a.getNumeroConsultas(), b.getNumeroConsultas());
        }
    }

    // Indica si "a" debe ir después de "b" según el orden elegido
    private boolean debeIrDespues(Paciente a, Paciente b, int criterio, boolean ascendente) {
        int resultado = comparar(a, b, criterio);
        if (ascendente) {
            return resultado > 0;
        }
        return resultado < 0;
    }

    private void intercambiar(Paciente[] datos, int i, int j) {
        Paciente auxiliar = datos[i];
        datos[i] = datos[j];
        datos[j] = auxiliar;
        movimientos++;
    }

    // Bubble Sort: intercambia vecinos desordenados
    public void burbuja(Paciente[] datos, int criterio, boolean ascendente) {
        reiniciarContadores();
        int total = datos.length;
        for (int pasada = 0; pasada < total - 1; pasada++) {
            boolean huboIntercambio = false;
            for (int i = 0; i < total - 1 - pasada; i++) {
                comparaciones++;
                if (debeIrDespues(datos[i], datos[i + 1], criterio, ascendente)) {
                    intercambiar(datos, i, i + 1);
                    huboIntercambio = true;
                }
            }
            if (!huboIntercambio) {
                break;
            }
        }
    }

    // Selection Sort: busca el elemento que corresponde a cada posición
    public void seleccion(Paciente[] datos, int criterio, boolean ascendente) {
        reiniciarContadores();
        int total = datos.length;
        for (int i = 0; i < total - 1; i++) {
            int posicionElegida = i;
            for (int j = i + 1; j < total; j++) {
                comparaciones++;
                if (debeIrDespues(datos[posicionElegida], datos[j], criterio, ascendente)) {
                    posicionElegida = j;
                }
            }
            if (posicionElegida != i) {
                intercambiar(datos, i, posicionElegida);
            }
        }
    }

    // Insertion Sort: inserta cada elemento en la parte ya ordenada
    public void insercion(Paciente[] datos, int criterio, boolean ascendente) {
        reiniciarContadores();
        int total = datos.length;
        for (int i = 1; i < total; i++) {
            Paciente actual = datos[i];
            int j = i - 1;
            while (j >= 0) {
                comparaciones++;
                if (!debeIrDespues(datos[j], actual, criterio, ascendente)) {
                    break;
                }
                datos[j + 1] = datos[j];
                movimientos++;
                j--;
            }
            if (j + 1 != i) {
                datos[j + 1] = actual;
                movimientos++;
            }
        }
    }
}
