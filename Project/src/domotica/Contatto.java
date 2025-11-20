package domotica;

import java.io.*;
import java.net.*;
import org.json.JSONObject;
import java.util.Random;


public class Contatto {
String server = "localhost";
int porta = 6789;


public String generaJSON() {
Random r = new Random();
boolean val = r.nextBoolean();
JSONObject obj = new JSONObject();
obj.put("id", "C1");
obj.put("tipo", "contatto");
obj.put("zona", "Ingresso");
obj.put("valore", val);
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