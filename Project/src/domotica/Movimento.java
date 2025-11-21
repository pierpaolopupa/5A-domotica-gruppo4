import java.io.*;
import java.net.*;
import java.util.Random;

public class Movimento {
    public static void main(String[] args) {
        Random rand = new Random();

        try (Socket socket = new Socket("localhost", 5000);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("[MOVIMENTO] Connesso al server.");

            while (true) {
                boolean rilevato = rand.nextBoolean();
                if (rilevato) {
                    out.println("MOVIMENTO: RILEVATO!");
                } else {
                    out.println("MOVIMENTO: nessun movimento");
                }
                Thread.sleep(5000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
