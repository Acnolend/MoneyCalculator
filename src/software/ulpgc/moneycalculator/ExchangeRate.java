package software.ulpgc.moneycalculator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExchangeRate {
    private final Currency from;
    private final Currency to;

    public ExchangeRate(Currency from, Currency to) {
        this.from = from;
        this.to = to;
    }

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }

    public float getURL() throws Exception {
        StringBuilder resultado = new StringBuilder();
        URL url = new URL("https://v6.exchangerate-api.com/v6/0d36e3f2d88ea6b941227aac/latest/"+from.getCode());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String linea;

        while ((linea = rd.readLine()) != null) {
            resultado.append(linea);
        }

        rd.close();
        return getURL(resultado.toString());
    }

    public float getURL(String result) {
        int initialS = result.indexOf(to.getCode(), 364);
        int finalS = result.indexOf(",", initialS);
        String subcadena = result.substring(initialS,finalS);
        initialS = subcadena.indexOf(":")+1;
        subcadena = subcadena.substring(initialS);
        return Float.parseFloat(subcadena);
    }
}
