package domotica;

// ===== MultiClient Temperatura =====
import java.io.*;
import java.net.*;
import org.json.JSONObject;
import java.util.Random;


public class TemperaturaClient {
	String server = "localhost";
	int porta = 6789;
	
	
	public String generaJSON() {
		Random r = new Random();
		double valore = 20 + r.nextDouble() * 20;
		JSONObject obj = new JSONObject();
		obj.put("id", "T1");
		obj.put("tipo", "temperatura");
		obj.put("valore", valore);
		return obj.toString();
	}
	
	
	public void avvia() throws Exception {
		Socket s = new Socket(server, porta);
		BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		DataOutputStream out = new DataOutputStream(s.getOutputStream());
		
		
		String json = generaJSON();
		out.writeBytes(json + "");
		
		
		System.out.println("Risposta server: " + in.readLine());
		
		
		s.close();
	}
}