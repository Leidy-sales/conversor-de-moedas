package br.com.alura.ConversorDeMoedas.Calculadora;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public record ConversorApp() {
    private static final String apiKey = "c12dd85048d72b9127733a45";

    public static double converter(String moedaAtual, String moedaDestino, double valor) {
        try {
            String urlStr = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + moedaAtual;

            URL url = new URL(urlStr);

            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            JsonObject json = JsonParser.parseReader(new InputStreamReader(request.getInputStream())).getAsJsonObject();

            if (!json.get("result").getAsString().equals("success")) {
                System.out.println("Erro da API: " + json.get("error-type").getAsString());
                return -1;
            }

            double taxa = json.getAsJsonObject("conversion_rates").get(moedaDestino).getAsDouble();

            return valor * taxa;

        } catch (Exception e) {
            System.out.println("Erro na requisição: " + e.getMessage());
            return  -1;
        }
    }


}
