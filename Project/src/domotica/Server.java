package domotica;

import org.json.JSONObject;
import java.net.*;
import java.io.*;
import java.net.*;

private String checkCont(JSONObject o) {
	boolean v = o.getBoolean("valore");
	return v ? "PORTA APERTA in " + o.getString("zona") : "Porta chiusa";
}

class Server extends Thread {
	private Socket client;
	private BufferedReader inDalClient;
	private DataOutputStream outVersoClient;
	private static Server server = new Server(); // condiviso tra tutti i thread
	
	
	public ServerThread(Socket socket){
		this.client = socket;
	}
	
public void run(){
	try{
		comunica();
	} catch (Exception e){
		e.printStackTrace();
	}
}

public void comunica() throws Exception{
	inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
	outVersoClient = new DataOutputStream(client.getOutputStream());

	for(;;) {
		String json = inDalClient.readLine();
		if (json == null || json.equals("FINE")) {
			outVersoClient.writeBytes("Chiusura connessione");
			break;
		}
		String risposta = server.elabora(json);
		outVersoClient.writeBytes(risposta + "");
		System.out.println("Ricevuto: " + json);
		System.out.println("Risposto: " + risposta);
	}
	client.close();
}

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