package software.ulpgc.moneycalculator;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Seleccione una divisa de referencia (EN MAYUSCULA): ");
        String divisa1 = sc.nextLine();
        String resultado = Recolector.getURL("https://v6.exchangerate-api.com/v6/0d36e3f2d88ea6b941227aac/latest/"+divisa1);
        Map mapa = Transformador.MapOfDivisa(resultado);
        System.out.println("Escoja una divisa (EN MAYUSCULA): ");
        String divisa2 = sc.nextLine();
        System.out.println("El "+divisa2+" tiene un valor de "+mapa.get(divisa2)+" comparado con "+divisa1);
    }
}
