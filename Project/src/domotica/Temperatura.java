import java.io.*;
import java.net.*;
import java.util.Random;

public class Temperatura {
    public static void main(String[] args) {
        Random rand = new Random();

        try (Socket socket = new Socket("localhost", 5000);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("[TEMPERATURA] Connesso al server.");

            while (true) {
                int valore = rand.nextInt(15) + 15; // temperatura tra 15 e 30
                out.println("TEMPERATURA: " + valore + " °C");
                Thread.sleep(2000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
