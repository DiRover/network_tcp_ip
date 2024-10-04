import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    public static final Integer PORT = 8080;

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is started");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    String message = bufferedReader.readLine();
                    System.out.printf("New connection is accepted. Info: %s, port: %d%n", message, clientSocket.getPort());

                    System.out.println("Client message: " + message);

                    printWriter.println("Hello from server! Your port is " + clientSocket.getPort() + ". Write your name line:");

                    String userName = bufferedReader.readLine();
                    System.out.println("Client's name: " + userName);

                    printWriter.println("Are you child? (yes/no)");
                    String age = bufferedReader.readLine();

                    if (age.equals("yes")) {
                        printWriter.printf("Welcome to the kids area, %s! Let's play!", userName);
                    } else {
                        printWriter.printf("Welcome to the adult zone, %s! Have a good rest, or a good working day!", userName);
                    }

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
