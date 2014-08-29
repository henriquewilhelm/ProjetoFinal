package Socket;

import java.util.Scanner;
import java.io.InputStream;


public class ThreadServInput implements Runnable {
	 
	   private InputStream cliente;
	   private Servidor servidor;
	 
	   public ThreadServInput(InputStream cliente, Servidor servidor) {
	     this.cliente = cliente;
	     this.servidor = servidor;
	   }
	   
	   // Servidor recebe msgs de um cliente e envia a todos clientes;
	   public void run() {
		   		Scanner s = new Scanner(this.cliente);	
		   		try{
		   			while (s.hasNextLine()) {
		   		       servidor.broadCast(s.nextLine());
		   		     }
		   			
		   		}catch (Exception e) {
		   			s.close();
		   			e.printStackTrace();
		   		}
	   }
	 }