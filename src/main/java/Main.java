public class Main {
    public static void main(String[] args) {
        Thread serverThread = new Server();
        serverThread.start();

        Thread clientThread = new Client();
        clientThread.start();
    }
}