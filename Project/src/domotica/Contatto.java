import java.io.*;
import java.net.*;
import java.util.Random;

public class Contatto {
    public static void main(String[] args) {
        Random rand = new Random();

        try (Socket socket = new Socket("localhost", 5000);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("[CONTATTO] Connesso al server.");

            while (true) {
                String stato = rand.nextBoolean() ? "ON" : "OFF";
                out.println("CONTATTO: " + stato);
                Thread.sleep(3000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
