import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.TreeMap;

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

                out = new PrintWriter(socket.getOutputStream());
                is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            readHeader();
                            writeText();


                        }
                    }
                }).start();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void writeText() {
        StringBuilder sb = new StringBuilder("HTTP/1.1 ");
        //      sb.append(getcode()).
        sb.append("HTTP/1.1 200 OK").
          append("Content-Type: text/html; charset=utf-8");
        //out.println(getbody());
        out.write(sb.toString());
        System.out.println(sb.toString());
        //  out.flush();
    }

    public static String readHeader(){
    String request;
    try {
        request = is.readLine();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
     String metod = request.split(" ")[0];
       System.out.println(metod);
    System.out.println(request);
    System.out.println(metod);
return metod;
}



}


