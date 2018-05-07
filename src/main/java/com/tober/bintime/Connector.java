package com.tober.bintime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class Connector {
    public ObjectNode[] getResponse() {
        return response;
    }

    private int responseCode;

    public int getResponseCode() {
        return this.responseCode;
    }

    private ObjectNode[] response;

    private Properties properties = new Properties();

    Connector() throws IOException {
        InputStream configFile = new FileInputStream("./src/main/resources/rest.properties");
        properties.load(configFile);
    }

    void sendGetRequest() throws IOException {
        URL url = new URL(properties.getProperty("url"));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Request method can be changed in config file or set right here;
        connection.setRequestMethod(properties.getProperty("requestMethod"));

        // Request header
        connection.setRequestProperty("User-Agent", "TOBER");

        System.out.print("Sending request to " + url + "... ");
        this.responseCode = connection.getResponseCode();
        System.out.println("Status: " + getResponseCode());

        setResponse(connection);
    }

    void setResponse(HttpURLConnection connection) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder responseBuilder = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            responseBuilder.append(inputLine);
        }
        in.close();

        String response = responseBuilder.toString();

        ObjectMapper mapper = new ObjectMapper();
        this.response = mapper.readValue(response, ObjectNode[].class);
    }


}
