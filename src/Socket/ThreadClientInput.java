package Socket;
import java.io.InputStream;
import java.util.Scanner;


public class ThreadClientInput implements Runnable {
	 
	   private InputStream inputCliente;
	   private Cliente cliente;
	 
	   public ThreadClientInput(InputStream inputCliente, Cliente cliente) {
	     this.inputCliente = inputCliente;
	     this.cliente = cliente;
	   }
	   
	   public void run() {
		   		// recebe msgs do servidor e imprime na tela
  				Scanner s = new Scanner(this.inputCliente);
		   		try{
		   			
		   			while (s.hasNextLine()) {
		   				// System.out.println(s.nextLine());
		   				cliente.setComandoEntrada(s.nextLine());
		   				System.out.println(cliente.getComandoEntrada());
		   			}
		   		}catch (Exception e) {
					// TODO: handle exception
		   			e.printStackTrace();
		   			s.close();
				}
		  }
}
