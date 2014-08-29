package Socket;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class InterfaceCliente {
	     
		
	   private String host;
	   private int porta;
	   private boolean status = false;
	   private JButton conectaServer, closeServer, um, dois, tres;
	   private JFrame frame;
	   private JTextField textField1, textField2;    
	   private PrintStream saida; 
	   private String comandoSaida = "";
	   Cliente cli;
	   
	   public InterfaceCliente () {
	   }
	   
	   public InterfaceCliente (String host, int porta) {
	     this.host = host;
	     this.porta = porta;
	   }
	   
	   public void constroiFrame() {
		   		// Controi Frame
		   		frame = new JFrame("Cliente");
		   		// set frame layout
		        frame.setLayout( new FlowLayout() ); 
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.setSize(300,300);
		       
		        // Campos (textField) e botoes (Button)
				textField1 = new JTextField( 30 );    
				textField1.setText("localhost");
				textField2 = new JTextField( 10 );    
				conectaServer = new JButton( "Conecta no servidor" );
				closeServer = new JButton( "Desconecta" );
				um = new JButton( "1" );
				dois = new JButton( "2" );
				tres = new JButton( "3" );
				
				// Adiciona objetos no frame
				frame.add( textField1 );
				frame.add( textField2 );
				frame.add( conectaServer );
				frame.add( closeServer );
				frame.add( um );
				frame.add( dois );
				frame.add( tres );
				// Visivel
				frame.setVisible(true);
				
				// Cria handler (Manipulador de Botoes - Acoes)
				ButtonButtonHandler handler = new ButtonButtonHandler();         
				um.addActionListener( handler );
				dois.addActionListener( handler );
				tres.addActionListener( handler );
				
				// Cria handlerCon (Manipulador de Botoes - Acoes)
				ButtonConexaoHandler handlerCon = new ButtonConexaoHandler();         
				closeServer.addActionListener( handlerCon );
				conectaServer.addActionListener( handlerCon );
				
				// Desabilita botao
				closeServer.setEnabled(false);
		}
	   	// Manipulador de Acoes - Botoes (1, 2, 3)
		private class ButtonButtonHandler implements ActionListener{
			
		      public void actionPerformed( ActionEvent event )
		      {
		    	  if (event.getSource() == um) {
		    		  	comandoSaida = "1";
		    	  }
		    	  else if (event.getSource() == dois) {
		    		  comandoSaida = "2";
		    	  }
		    	  else if (event.getSource() == tres) {
		    		  comandoSaida = "3";
		    	  }
		    	  saida.println(comandoSaida);
		    	  //System.out.println(comandoSaida);
		      } 
		   } 
		// Manipulador de Acoes - Botoes (Conecta, Desconecta) - Acoes
		private class ButtonConexaoHandler implements ActionListener  {
		      public void actionPerformed( ActionEvent event )
		      {
		          try {
		        	  if (event.getSource() == conectaServer) {
		        		  host = textField1.getText();
		        		  porta = Integer.parseInt(textField2.getText());
		        		  cli = new Cliente(host,porta);
		        		  saida = cli.conecta();
		        		  // 
	        			  conectaServer.setEnabled(false);
	        			  closeServer.setEnabled(true);
		        	  }
		        	  else if (event.getSource() == closeServer) {
		        		  cli.desconta(); 
		        		  conectaServer.setEnabled(true);
		        		  closeServer.setEnabled(false);
		        	  }
		          }
		          catch (IOException e)
		          {
		             e.printStackTrace();
		          }
		      } 
		 } 
		// Getters and Setters
		public boolean isStatus() {
			return status;
		}
		public void setStatus(boolean status) {
			this.status = status;
		}
		// Teste Main
		public static void main(String[] args) throws  UnknownHostException, IOException {
		     // inicia o cliente 
		     new InterfaceCliente().constroiFrame();
	}
}
	 
	 
