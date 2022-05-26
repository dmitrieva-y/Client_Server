import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_OK;

public class Server {
    private final static int PORT = 8180;
    private static Handler handler;

    public Server() {
        handler = new Handler();
    }


    private void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("Client connected");
                readHeader(client);
                // new Thread(() -> readHeader(client)).start();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    public static void readHeader(Socket client) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(client.getOutputStream())) {
            List<String> arr = new ArrayList<>();
//            String line;
//            while (br.ready()) {
//                arr.add(br.readLine());
//            }



//            String line = arr.get(0);
           String line = br.readLine();
            String method = line.split(" ")[0];
            String URI = line.split(" ")[1];


            int statusCode = 200;
            String statusText = "OK";
            String text = "";
            if (method.trim().equals("GET")) {
                if (URI.trim().equals("/persons")) {
                    text = handler.getAllPerson();
       //             return Result.ok(HTTP_OK, text);
                } else {
                    statusCode = 404;
                    statusText = "NOT FOUND";
                }
            } else if (method.trim().equals("POST")) {
                if (URI.trim().startsWith("/delete")) {
                    handler.delete();
                } else if (URI.trim().startsWith("/update")) {
                    handler.update();
                } else if (URI.trim().startsWith("/insert")) {
                    handler.insert();
                } else {
                    statusCode = 404;
                    statusText = "NOT FOUND";
                }
            } else {
                statusCode = 400;
                statusText = "BAD REQUEST";
            }
            out.write(getResponse(statusCode, statusText, text));
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getResponse(int statusCode, String statusText, String text) {
        StringBuilder builder = new StringBuilder("HTTP/1.1 ");
        builder.append(statusCode).append(" ").append(statusText).append("\r\n");
        builder.append("Content-Type: text/html; charset=utf-8 \r\n\n");
        builder.append(text);
        return builder.toString();
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

}


