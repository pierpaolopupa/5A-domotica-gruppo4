package domotica;

// ===== MultiServer aggiornato =====
import java.net.*;


public class MultiServer {
	public void start(){
	try {
		ServerSocket serverSocket = new ServerSocket(6789);
		System.out.println("Server avviato...");
		
		
		for(;;) {
			Socket socket = serverSocket.accept();
			System.out.println("Nuova connessione: " + socket);
			ServerThread t = new ServerThread(socket);
			t.start();
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	public static void main(String[] args){
		MultiServer s = new MultiServer();
		s.start();
	}
}