package domotica;

// ===== MultiClient Movimento =====
import java.io.*;
import java.net.*;
import org.json.JSONObject;
import java.util.Random;


public class MovimentoClient {
	String server = "localhost";
	int porta = 6789;


	public String generaOra() {
		Random r = new Random();
		int h = r.nextInt(24);
		int m = r.nextInt(60);
		return String.format("%02d:%02d", h, m);
	}
	
	
	public String generaJSON() {
		JSONObject obj = new JSONObject();
		obj.put("id", "M1");
		obj.put("tipo", "movimento");
		obj.put("zona", "Cucina");
		obj.put("ora", generaOra());
		return obj.toString();
	}
	
	
	public void avvia() throws Exception {
		Socket s = new Socket(server, porta);
		BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		DataOutputStream out = new DataOutputStream(s.getOutputStream());
		
		
		String json = generaJSON();
		out.writeBytes(json + "
		");
		
		
		System.out.println("Risposta server: " + in.readLine());
		
		
		s.close();
	}
}