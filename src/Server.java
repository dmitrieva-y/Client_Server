import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class Server {
    private final static int PORT = 8180;
    private  static final Controller controller = new Controller();
    private void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started");
            while (true) {
                //Socket client = serverSocket.accept();
                System.out.println("Client connected");
                readHeader();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void readHeader() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String line;
        while (!(line = br.readLine()).equals("ex")) {
            String response;
            if (line.isEmpty()) {
                response = Result.error(StatusCode.BAD_REQUEST);
            } else {
                response = getResponse(line);
            }

            out.write(response);
            out.write("\r\n");
            out.flush();
        }
        System.err.println("client decconnect");
    }

    private static String getResponse(String line) {
        String[] param = line.split("\\s+");

        // желательно создать 1 контроллер, не пересоздавать его
        // параметр в контроллер не передаём
        Command command;

        try {
            command = Command.fromString(param[0].trim());
        } catch (IllegalArgumentException e) {
            return Result.error(StatusCode.BAD_REQUEST);
        }

        switch (command) {
            case GET_ALL_PERSON:
                return controller.getAllPerson();
            case GET_PERSON:
                return controller.getPerson(param);
            case CREATE_PERSON:
                return controller.createPerson(param);
            case UPDATE_PERSON:
                return controller.update(param);
            case DELETE_PERSON:
                return controller.deletePerson(param);
            case DELETE_ALL:
                return controller.delete();
            default:
                return Result.error(StatusCode.BAD_REQUEST);
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}


