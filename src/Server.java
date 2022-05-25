import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    private final static int PORT = 8180;
    private static BufferedReader is;
    private static PrintWriter out;
    private static List<Person> persons;

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream());
                while (!is.ready()) ;
                // while (is.ready()) {
                String request = is.readLine();
                System.out.println(request);
                String metod = request.split(" ")[0];
                System.out.println(metod);
                //  }
                writeText("<html><body><h1>Hello</h1></body></html>");
//                new Thread(() -> {
//                    while (true) {
//                        readHeader();
//                        writeText("<html><body><h1>Hello</h1></body></html>");
//
//
//                    }
//                }).start();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void writeText(String s) {
        String response = "HTTP/1.1 200 OK\r\n" +
                "Server: YarServer/2009-09-09\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: " + s.length() + "\r\n" +
                "Connection: close\r\n\r\n";
        String result = response + s;

        out.println(result);
        out.flush();


    }

    public static String readHeader() {
        String request;
        try {
            request = is.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String metod = request.split(" ")[0];
        System.out.println(request);
        return metod;
    }


}


