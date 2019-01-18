package br.com.teste.agenda;

import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WebClient {
    public String post(String json){

        try {
            URL url = new URL("https://www.caelum.com.br/mobile");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-type", "application/json");

            connection.setDoInput(true);
            connection.setDoOutput(true);

            PrintStream saida = new PrintStream(connection.getOutputStream());
            saida.println(json);

            connection.connect();

            String resposta = new Scanner(connection.getInputStream()).next();

            return resposta;


        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
