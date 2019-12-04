package com.company;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static RegisterResponse registerRequest(String nickname) throws IOException{
        String data, set_server_url = "http://194.176.114.21:8050";
        URL url;
        HttpURLConnection urlConnection;
        OutputStream out;
        Gson gson;
        RegisterResponse response;

        url = new URL(set_server_url);

        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setDoOutput(true); // setting POST method

        // creating stream for writing request
        out = urlConnection.getOutputStream();
        data = "{\"action\": \"register\", \"nickname\": \"" + nickname +"\"}"; // Your JSON request here
        out.write(data.getBytes());

        // reading response
        gson = new Gson();

        // RegisterResponse - класс, созданный для структоры данных как в JSON-е
        response = gson.fromJson(new InputStreamReader(urlConnection.getInputStream()), RegisterResponse.class);

        urlConnection.disconnect();

        return response;
    }

    public static CardResponse cardsRequest(int token) throws IOException {
        String data, set_server_url = "http://194.176.114.21:8050";
        URL url;
        HttpURLConnection urlConnection;
        OutputStream out;
        Gson gson;
        CardResponse response;

        url = new URL(set_server_url);

        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setDoOutput(true); // setting POST method

        // creating stream for writing request
        out = urlConnection.getOutputStream();
        data = "{\"action\": \"fetch_cards\", \"token\": \"" + Integer.toString(token)+ "\"}"; // Your JSON request here
        out.write(data.getBytes());

        // reading response
        gson = new Gson();

        // RegisterResponse - класс, созданный для структоры данных как в JSON-е
        response = gson.fromJson(new InputStreamReader(urlConnection.getInputStream()), CardResponse.class);

        urlConnection.disconnect();

        return response;
    }

    public static void main(String[] args) throws IOException {
        Scanner in;
        String nickname, status;
        int token;
        RegisterResponse registerResponse;

        in = new Scanner(System.in);

        System.out.print("Input nickname: ");
        nickname = in.nextLine();

        registerResponse = registerRequest(nickname);

        status = registerResponse.status;
        token = registerResponse.token;

        System.out.println("Status: " + status + "\n Token: " + token);

        for (Card card : cardsRequest(token).cards) System.out.println(card);

    }
}
