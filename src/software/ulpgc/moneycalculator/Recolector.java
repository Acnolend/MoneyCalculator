package software.ulpgc.moneycalculator;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class Recolector {

    public static String getURL(String urlWillVisit) throws Exception {
        StringBuilder resultado = new StringBuilder();
        URL url = new URL(urlWillVisit);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String linea;

        while ((linea = rd.readLine()) != null) {
            resultado.append(linea);
        }

        rd.close();
        return resultado.toString();
    }
}