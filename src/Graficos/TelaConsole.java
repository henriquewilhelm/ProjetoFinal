package Graficos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import Arquivos.ConsultaDescNavio;
import Jogo.Jogador;

/**
 * Batalha Naval (Ultimate Battle) v1.0 - Versao 2.1 de Interface Grafica 
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
	// Declara Tela (JPanel)
	private JPanel panelConsole;
	private JPanel panelDescricao;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private ConsultaDescNavio consulta;
    static String vertical = "Vertical";
    static String horizontal = "Horizontal";
	private JButton ButtonVoltar;
	private JButton ButtonProximo;
	private int contador = 0;
	// Declarando e adicionando as Imagens ao vetor de Icon (Diretorio img) 
	private Icon icons[] = {
			new ImageIcon(getClass().getResource("img/img1.jpg")),
			new ImageIcon(getClass().getResource("img/img2.jpg")),
			new ImageIcon(getClass().getResource("img/img3.jpg")),
			new ImageIcon(getClass().getResource("img/img4.jpg")),
			new ImageIcon(getClass().getResource("img/img5.jpg")),
			new ImageIcon(getClass().getResource("img/img6.jpg")) };
	// Adicionando vetor de Icon (Imagens) ao JLabel[]
	private JLabel label = new JLabel(icons[0]);
	// Nossa classe (Classe importada de Jogo.Jogador)
	private Jogador jogador;
	// Construtor da Tela
	public TelaConsole(Jogador jogador) {
		this.jogador = jogador;
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
        this.consulta = new ConsultaDescNavio();
		// Consulta descricao
        this.consulta.ConsultaNavioTextFiel(0, getTextArea());
        
        // Cria Scrollpane (Barra de rolagem) no Campo de Texto
        scrollPane = new JScrollPane(getTextArea());
        // Coloca Barra de rolagem no lugar (Final do texto e na Horizontal)
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        // Adiciona TextArea com Scrollpane na tela Descricao
        getPanelDescricao().add(scrollPane, c);
        // Adiciona a tela Descricao na tela principal Console
        getPanelConsole().add(getPanelDescricao());
		// Cria Radio Button (Opcao Vertical)
		JRadioButton radioVertical = new JRadioButton(vertical);
        radioVertical.setMnemonic(KeyEvent.VK_R);
        radioVertical.setActionCommand(vertical);
		// Cria Radio Button (Opcao Horizontal)
        JRadioButton radioHorizontal = new JRadioButton(horizontal);
        radioHorizontal.setMnemonic(KeyEvent.VK_P);
        radioHorizontal.setActionCommand(horizontal);
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
		setButtonProximo( (new JButton("Proxima Escolha")) );
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
				if (e.getSource() == getButtonVoltar()) {
					
				}
				if (e.getSource() == getButtonProximo()) {
					// Muda Imagem do navio/peca
					if (getContador() < getIcons().length){
							setContador(getContador()+1);
							getLabel().setIcon(getIcons()[getContador()]);
							// Atualiza descricao do Navio
							getTextArea().setText("");
							getConsulta().ConsultaNavioTextFiel(getContador(), getTextArea());
					}
				}
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(null, "ERRO - Uso incorreto");
				exception.printStackTrace();
			}
		}
		
	}

	public JPanel getPanelConsole() {
		return panelConsole;
	}

	public void setPanelConsole(JPanel panelOpcoes) {
		this.panelConsole = panelOpcoes;
	}

	public Icon[] getIcons() {
		return icons;
	}

	public void setIcons(Icon[] icons) {
		this.icons = icons;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public JButton getButtonVoltar() {
		return ButtonVoltar;
	}

	public void setButtonVoltar(JButton ButtonVoltar) {
		this.ButtonVoltar = ButtonVoltar;
	}

	public JButton getButtonProximo() {
		return ButtonProximo;
	}

	public void setButtonProximo(JButton ButtonProximo) {
		this.ButtonProximo = ButtonProximo;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public static String getVertical() {
		return vertical;
	}

	public static void setVertical(String vertical) {
		TelaConsole.vertical = vertical;
	}

	public static String getHorizontal() {
		return horizontal;
	}

	public static void setHorizontal(String horizontal) {
		TelaConsole.horizontal = horizontal;
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

	public JPanel getPanelDescricao() {
		return panelDescricao;
	}

	public void setPanelDescricao(JPanel panelDescricao) {
		this.panelDescricao = panelDescricao;
	}

	public ConsultaDescNavio getConsulta() {
		return consulta;
	}

	public void setConsulta(ConsultaDescNavio consulta) {
		this.consulta = consulta;
	}
	
}
