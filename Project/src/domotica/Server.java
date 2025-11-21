import java.io.*;
import java.net.*;

public class Server {
    private static final int PORT = 5000;

    public static void main(String[] args) {
        System.out.println("[SERVER] Avviato sulla porta " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("[SERVER] Nuovo client connesso.");

                Thread t = new Thread(new ServerWorker(clientSocket));
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ServerWorker implements Runnable {

    private Socket socket;

    public ServerWorker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            String msg;
            while ((msg = in.readLine()) != null) {
                System.out.println("[SERVER] Ricevuto: " + msg);
            }
        } catch (IOException e) {
            System.out.println("[SERVER] Client disconnesso.");
        }
    }
}
