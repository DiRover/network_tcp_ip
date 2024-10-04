import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {

    private final Scanner SCANNER = new Scanner(System.in);

    @Override
    public void run() {
        try (Socket clienSocket = new Socket("netology.homework", Server.PORT);
             PrintWriter writer = new PrintWriter(clienSocket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(clienSocket.getInputStream()))) {
            writer.println("Hello from client!");

            String message = reader.readLine();
            System.out.println(message);

            String name = SCANNER.nextLine();
            writer.println(name);

            String serverAgeRequest = reader.readLine();
            System.out.println(serverAgeRequest);

            String age = SCANNER.nextLine();
            System.out.println(age);

            writer.println(age);

            String serverAgeResponse = reader.readLine();
            System.out.println(serverAgeResponse);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
