package software.ulpgc.moneycalculator.api;

import software.ulpgc.moneycalculator.Command;
import software.ulpgc.moneycalculator.ExchangeRate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiGet implements Command {

    private final ExchangeRate exchangeRate;

    public ApiGet(ExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public ExchangeRate getExchangeRate() {
        return exchangeRate;
    }

    @Override
    public String execute() throws IOException {
        try {
            URL url = new URL("https://v6.exchangerate-api.com/v6/0d36e3f2d88ea6b941227aac/latest/"+exchangeRate.getFrom().getCode());
            return execute(url);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String execute(URL url) throws IOException {
        StringBuilder json = new StringBuilder();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String linea;
        while ((linea = rd.readLine()) != null) {
            json.append(linea);
        }

        rd.close();
        return json.toString();
    }

}
