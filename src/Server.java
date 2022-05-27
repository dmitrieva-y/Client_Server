import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class Server {
    private final static int PORT = 8180;



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
                response = Result.error(StatusCod.BAD_REQUEST);
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
        Controller controller = new Controller(param);
        Command command;
        try {
            command = Command.fromString(param[0].trim());
        } catch (IllegalArgumentException e) {
            return Result.error(StatusCod.BAD_REQUEST);
        }
        String response;
        switch (command) {
            case GET_ALL_PERSON:
                response = controller.getAllPerson();
                break;
            case GET_PERSON:
                response = controller.getPerson();
                break;
            case CREATE_PERSON:
                response = controller.createPerson();
                break;
            case UPDATE_PERSON:
                response = controller.update();
                break;
            case DELETE_PERSON:
                response = controller.deletePerson();
                break;
            case DELETE_ALL:
                response = controller.delete();
                break;
            default:
                response = Result.error(StatusCod.BAD_REQUEST);
        }
        return response;
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

}


