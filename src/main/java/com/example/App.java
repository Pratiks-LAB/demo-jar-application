package com.example;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class App {

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(
                new InetSocketAddress(8080), 0);

        server.createContext("/", exchange -> {

            InputStream input = App.class
                    .getClassLoader()
                    .getResourceAsStream("index.html");

            if (input == null) {
                String error = "index.html not found";
                exchange.sendResponseHeaders(500, error.length());

                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(error.getBytes(StandardCharsets.UTF_8));
                }
                return;
            }

            byte[] response = input.readAllBytes();

            exchange.getResponseHeaders()
                    .set("Content-Type", "text/html");

            exchange.sendResponseHeaders(200, response.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response);
            }
        });

        server.start();

        System.out.println("Application started on port 8080");
    }
}
