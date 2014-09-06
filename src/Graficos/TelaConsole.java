package Graficos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Arquivos.ConsultaDescNavio;
import Jogo.Jogador;

/**
 * Batalha Naval (Ultimate Battle) v1.0 - Versao 2.2 de Interface Grafica 
 * (telaMenuImagens)
 * Esta classe eh responsavel pela Tela do Menu de Imagens. Ela  possui 5 Label`s
 * com Imagens (5 imagens com Pecas/Navios e 1 com Mensagem de OK).
 * 
 * Obs: Ainda vou adicionar o nome dos navios e uma descricao perto da imagem de
 * cada um, mas essa eh basicamente a tela da primeira etapa (Escolha das Posicoes).
 * Falta criar a tela da segunda etapa (quando o jogo comeca), onde vamos ter os niveis
 * de vida do jogador e de cada navio do Jogador assim como os pontos/acertos no inimigo...
 * Autor: Henrique W.
 */

public class TelaConsole {
	
	// Declara Tela Principal - panelConsole (JPanel)
	private JPanel panelConsole;
	// Declara Sub-tela Descricao (JPanel)
	private JPanel panelDescricao;
	// Declara Campo de texto com Barra de rolagem
	private JTextArea textArea;
	private JScrollPane scrollPane;
	// Declara nossa classe (Importada do pacote Arquivos)
	private ConsultaDescNavio consulta;
	// Declara Radio Button (Caixa de Opcao)
	private JRadioButton radioVertical;
	private JRadioButton radioHorizontal;
    // Declara Componente - Botoes Volta e Proximo
	private JButton ButtonVoltar;
	private JButton ButtonProximo;
	// Declara variavel auxiliar do vetor de Imagens
	// Declarando e adicionando as Imagens ao vetor de Icon (Diretorio img) 
	private Icon imagem[] = {
			new ImageIcon(getClass().getResource("img/couracado.jpg")),
			new ImageIcon(getClass().getResource("img/cruzador.jpg")),
			new ImageIcon(getClass().getResource("img/destroyer.jpg")),
			new ImageIcon(getClass().getResource("img/portaavioes.jpg")),
			new ImageIcon(getClass().getResource("img/submarino.jpg")) };
	// Adicionando Icon (Imagens) ao JLabel[]
	private JLabel label = new JLabel(imagem[0]);
	// Nossa classe (Classe importada de Jogo.Jogador)
	private Jogador player;	
	private boolean escolha = false;
	private boolean volta = false;
	private boolean posicao = false;
	
	// Construtor da Tela
	public TelaConsole(Jogador player) {
		this.player = player;
		// Cria Tela Principal (JPanel)
		setPanelConsole(new JPanel());
		// Adiciona imagem do Navio/Peca a tela
		getPanelConsole().add(getLabel());
		// // Cria Tela para Descricoes (JPanel) 
		setPanelDescricao(new JPanel(new GridBagLayout()));
		// Cria e instancia TextArea para Descricao dos Navios/Pecas
        setTextArea(new JTextArea(10, 55));
        getTextArea().setEditable(false);	// Desabilita edicao
        // Todos (Pacote Arquivos)
        setConsulta(new ConsultaDescNavio());
		// Consulta descricao
        getConsulta().ConsultaNavioTextFiel(0, getTextArea());
        
        // Cria Scrollpane (Barra de rolagem) no Campo de Texto
        setScrollPane( new JScrollPane(getTextArea()));
        // Coloca Barra de rolagem no lugar (Final do texto e na Horizontal)
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        // Adiciona TextArea com Scrollpane na tela Descricao
        getPanelDescricao().add(scrollPane, c);
        // Adiciona a tela Descricao na tela principal Console
        getPanelConsole().add(getPanelDescricao());
		// Cria Radio Button (Opcao Vertical)
		setRadioVertical( new JRadioButton("Vertical"));
		//getRadioVertical().setMnemonic(KeyEvent.VK_R);
		getRadioVertical().setActionCommand("vertical");
		// Cria Radio Button (Opcao Horizontal)
		setRadioHorizontal(new JRadioButton("Horizontal"));
		//getRadioHorizontal().setMnemonic(KeyEvent.VK_P);
		getRadioHorizontal().setActionCommand("horizontal");
        // Cria grupo de radio buttons.
        ButtonGroup group = new ButtonGroup();
        // Adiciona os radio buttons ao grupo
        group.add(radioVertical);
        group.add(radioHorizontal);
        // Cria manipulador de eventos
        ButtonHandler handler = new ButtonHandler();
        // Adiciona na tela principal Console
        getPanelConsole().add(radioVertical);
        getPanelConsole().add(radioHorizontal);
        // Criando Botoes Volta e Proxima
		setButtonVoltar( (new JButton("Voltar Escolha")) );
		setButtonProximo( (new JButton("Confirmar Escolha")) );
		// Adicionando os botes a tela principal
		getPanelConsole().add(getButtonVoltar());
		getPanelConsole().add(getButtonProximo());
		// Adiciona os botoes e radio button ao Manipulador de eventos
		getButtonVoltar().addActionListener(handler); 
		getButtonProximo().addActionListener(handler);
        radioVertical.addActionListener(handler);
        radioHorizontal.addActionListener(handler);
	}
	private class ButtonHandler implements ActionListener {
		// Manipulador de Acoes - Botoes (BottonsChat)
		public void actionPerformed(ActionEvent e) {
			try {       
				if (e.getActionCommand().equals("horizontal")){
					setPosicao(true);
				}
				if (e.getActionCommand().equals("vertical")){
					setPosicao(false);
				}
				if (e.getSource() == getButtonVoltar()) {
						if (player.getNumRodadas() > 1 ){
							player.setNumRodadas(player.getNumRodadas()-1);
							setVolta(true);
							getLabel().setIcon(getImagem()[player.getNumRodadas()-1]);
							getTextArea().setText("");
							getConsulta().ConsultaNavioTextFiel(player.getNumRodadas()-1, getTextArea());	
						}
				}
				if (e.getSource() == getButtonProximo()) {
					// Muda Imagem do navio/peca
					setEscolha(true);
					System.out.println("Monitora Rodadas - " + player.getNumRodadas());
				}
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(null, "ERRO - Uso incorreto do Console");
				exception.printStackTrace();
			}
		}
		
	}
	public JPanel getPanelConsole() {
		return panelConsole;
	}
	public void setPanelConsole(JPanel panelConsole) {
		this.panelConsole = panelConsole;
	}
	public JPanel getPanelDescricao() {
		return panelDescricao;
	}
	public void setPanelDescricao(JPanel panelDescricao) {
		this.panelDescricao = panelDescricao;
	}
	public JTextArea getTextArea() {
		return textArea;
	}
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
	public ConsultaDescNavio getConsulta() {
		return consulta;
	}
	public void setConsulta(ConsultaDescNavio consulta) {
		this.consulta = consulta;
	}
	public JRadioButton getRadioVertical() {
		return radioVertical;
	}
	public void setRadioVertical(JRadioButton radioVertical) {
		this.radioVertical = radioVertical;
	}
	public JRadioButton getRadioHorizontal() {
		return radioHorizontal;
	}
	public void setRadioHorizontal(JRadioButton radioHorizontal) {
		this.radioHorizontal = radioHorizontal;
	}
	public JButton getButtonVoltar() {
		return ButtonVoltar;
	}
	public void setButtonVoltar(JButton buttonVoltar) {
		ButtonVoltar = buttonVoltar;
	}
	public JButton getButtonProximo() {
		return ButtonProximo;
	}
	public void setButtonProximo(JButton buttonProximo) {
		ButtonProximo = buttonProximo;
	}
	public Icon[] getImagem() {
		return imagem;
	}
	public void setImagem(Icon[] imagem) {
		this.imagem = imagem;
	}
	public JLabel getLabel() {
		return label;
	}
	public void setLabel(JLabel label) {
		this.label = label;
	}
	public Jogador getPlayer() {
		return player;
	}
	public void setPlayer(Jogador jogador) {
		this.player = jogador;
	}
	public boolean isEscolha() {
		return escolha;
	}
	public void setEscolha(boolean escolha) {
		this.escolha = escolha;
	}
	public boolean isVolta() {
		return volta;
	}
	public void setVolta(boolean volta) {
		this.volta = volta;
	}
	public boolean isPosicao() {
		return posicao;
	}
	public void setPosicao(boolean posicao) {
		this.posicao = posicao;
	}
	
}
