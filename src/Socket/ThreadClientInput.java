package Socket;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Batalha Naval (Ultimate Battle) - Versao 2.0
 * (Thread Cliente Input)
 * Essa classe (thread) é responsavel pelo recebimento dos dados de cada servidor para nao ocupar a 
 * a classe "Pai".
 */

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
