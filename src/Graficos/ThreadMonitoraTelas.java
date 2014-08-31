package Graficos;

/**
 * Batalha Naval (Ultimate Battle) - Versao 2.1 de Interface Grafica 
 * (Thread Monitora Telas) 
 * Thread (Processo Filho) que monitora o objeto Jogador e as telas, se orienta principalmente
 * pelo o numero de rodadas para alterar a telaMenusImagens, trocando as imagens (Label`s) quando
 * receber um "click" na telaTabuleiro (troca imagem com Peca/Navio por imagem com Mensagem OK).
 * Assim as Telas (JPanel) podem se relacionar...
 * Autor: Henrique Wilhelm
 */

public class ThreadMonitoraTelas extends Thread {
	
	private TelaConexao telaConexao;
	private TelaTabuleiro telaTabuleiro;
	private TelaConsole telaConsole;
	private TelaChat telaChat;

	public ThreadMonitoraTelas(TelaConexao telaConexao, TelaTabuleiro telaTabuleiro, 
			TelaConsole telaConsole, TelaChat telaChat) {
		
		this.telaTabuleiro = telaTabuleiro;
		this.telaConsole = telaConsole;
		this.telaConexao = telaConexao;
		this.telaChat = telaChat;
	}
	// Metodo que executa start() a Thread (Processo Filho)
	public void run() {
		String aux = "";
		// Enquanto Numero de Jogadas for menor que 100 (Numero de posicao de um tabuleiro)
		while (telaConsole.getJogador().getNumRodadas() < 100) {
					// Comente a parte de baixo para nao ver o numero de Rodadas (repetindo)
					//System.out.println("ThreadMonitoraRodadas - " + telaMenuImagens.getJogador().getNumRodadas());
					
					// Troca Imagem (JLabel) mostrando que Peca/Navio foi escolhido
					if (telaConsole.getJogador().getNumRodadas() == 1) {
					
					}
					if (telaConsole.getJogador().getNumRodadas() == 2) {
						
					}
					if (telaConsole.getJogador().getNumRodadas() == 3) {
					
					}
					if (telaConsole.getJogador().getNumRodadas() == 4) {
						
					}
					if 	(telaConsole.getJogador().getNumRodadas() == 5){
						telaConsole.getPanelConsole().setVisible(false);
					}		
				// Atualiza tela do chat (se conexao ok)
				if (telaConexao.isStatus()){
					String comandoEntrada = telaConexao.getCli().getComandoEntrada();
					// Verifica se mensagem nao é igual a ultima...
					if (!comandoEntrada.equals(aux)){
						telaChat.setTextAreaConversas(comandoEntrada + "\n"+ telaChat.getTextAreaConversas().getText());
						aux = comandoEntrada;
					}
				}
		}
	}
}
	
	
	