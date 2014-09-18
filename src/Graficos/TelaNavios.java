package Graficos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Arquivos.ConsultaDescNavio;
import Jogo.Jogador;

/**
 * Batalha Naval (Projeto GeracaoTec) - Versao 2.2 de Interface Grafica 
 * Classe: telaConsole
 * Esta classe eh responsavel pela Tela do Console do Jogo. Ela  possui 5 Label`s
 * com Imagens (5 imagens com Pecas/Navios), uma Campo de Texto (JTextField) com uma
 * breve descricao de cada barco (lida atras de um arquivo), dois RadioButton para 
 * determinar a posicao dos navios (vertical. horizontal) e tambem dois Botoes, um 
 * para Voltar a Jogada e outro para Confirma a escolha.
 * Autor: Henrique Wilhelm
 */

public class TelaNavios {
	
	// Declara Tela Principal - panelConsole (JPanel)
	private JPanel panelConsole1;
	private JPanel panelConsole2;
	private JPanel cards;
	// Declara Sub-telas (JPanel)
	private JPanel panelDescricao;
	private JPanel[] telaNavio;
	private JPanel[] telaImg;
	private JPanel[] caixaVida;
	private JLabel[] img;
	private JLabel[] nome;
	private JLabel[] vida;
	// Declara Campo de texto com Barra de rolagem
	private JTextArea textArea;
	private JScrollPane scrollPane;
	// Declara a classe (Importada do pacote Arquivos)
	private ConsultaDescNavio consulta;
	// Declara Radio Button (Caixa de Opcao)
	private JRadioButton radioVertical;
	private JRadioButton radioHorizontal;
    // Declara Componente - Botoes Volta e Proximo
	private JButton ButtonVoltar;
	private JButton ButtonProximo;

	// Declarando e adicionando as Imagens ao vetor de Icon (Diretorio img) 
	private Icon imagem[] = {
			new ImageIcon("img/navios/couracado.jpg"),
			new ImageIcon("img/navios/cruzador.jpg"),
			new ImageIcon("img/navios/destroyer.jpg"),
			new ImageIcon("img/navios/portaavioes.jpg"),
			new ImageIcon("img/navios/submarino.jpg") };
	// Adicionando Icon (Imagens) ao JLabel[]
	private JLabel label = new JLabel(imagem[0]);
	// Nossa classe (Classe importada de Jogo.Jogador)
	private Jogador jogador;
	// Declara variavel auxiliar (Flags)
	private boolean escolha = false;
	private boolean volta = false;
	private boolean posicao = false;
	// Construtor da Tela
	public TelaNavios(Jogador jogador) {
		// Cria Card de Telas
		cards = new JPanel(new CardLayout());
		// Cria Telas
		telaConsole1(jogador);
		telaConsole2(jogador);
		// Adiciona Telas ao Card de Telas
		cards.add(getPanelConsole1(), "1");
		cards.add(getPanelConsole2(), "2");
	}
	// TelaConsole1 (Console de Escolhas)
	public void telaConsole1(Jogador player) {
		this.jogador = player;
		// Cria Tela Principal (JPanel)
		setPanelConsole1(new JPanel());
		// Adiciona imagem do Navio/Peca a tela
		getPanelConsole1().add(getLabel());
		// Cria Tela para Descricoes (JPanel) 
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
        getPanelConsole1().add(getPanelDescricao());
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
        getPanelConsole1().add(radioVertical);
        getPanelConsole1().add(radioHorizontal);
        // Criando Botoes Volta e Proxima
		setButtonVoltar( (new JButton("Voltar Escolha")) );
		setButtonProximo( (new JButton("Confirmar Escolha")) );
		// Adicionando os botes a tela principal
		getPanelConsole1().add(getButtonVoltar());
		getPanelConsole1().add(getButtonProximo());
		// Adiciona os botoes e radio button ao Manipulador de eventos
		getButtonVoltar().addActionListener(handler); 
		getButtonProximo().addActionListener(handler);
        radioVertical.addActionListener(handler);
        radioHorizontal.addActionListener(handler);
	}
	// Tela Console 2 (Console do Jogo/Navios)
	public void telaConsole2(Jogador player) {
		this.jogador = player;
		// Cria Tela Principal (JPanel)
		setPanelConsole2(new JPanel(new GridLayout(5,1)));
		// Cria Sub-Telas	
		this.telaNavio = new JPanel[5];
		this.telaImg = new JPanel[5];
		this.caixaVida = new JPanel[5];
		// Cria Label para Imagem e Nome dos Navios
		this.img = new JLabel[5];
		this.nome = new JLabel[5];
    	for (int i=0; i< player.getHerois().size(); i++){
    			// Cria Label passando a String com diretorio da imagem dos navios
    			String dirNavio = "img/navios/navio" + Integer.toString((i+1)) + ".jpg";
				this.img[i] = new JLabel(new ImageIcon(dirNavio));
				// Cria Label com Nome do Navio, muda a cor, fonte etc
				this.nome[i] = new JLabel(player.getHerois().get(i).getNome());
				this.nome[i].setFont(new Font("Verdana", Font.BOLD, 14));
				this.nome[i].setForeground(Color.RED);
				// Adicionando Labels a Sub-tela
				this.telaImg[i] = new JPanel(new GridLayout(2,1));
				this.telaImg[i].add(this.img[i]);
				this.telaImg[i].add(this.nome[i]);
				// Criando e adicionando Telas a Sub-Tela
				this.caixaVida[i] = new JPanel (new FlowLayout());
				this.vida = new JLabel[player.getHerois().get(i).getPosicao().length];
				for (int contador =0; contador<vida.length; contador++){
					this.vida[contador] = new JLabel(new ImageIcon("img/vida/vida1.jpg"));
					this.caixaVida[i].add(this.vida[contador]);
				}
				// Criando e adicionando Telas a Sub-Tela
				this.telaNavio[i] = new JPanel(new FlowLayout());
				this.telaNavio[i].add(this.telaImg[i]);
				this.telaNavio[i].add(this.caixaVida[i]);
				getPanelConsole2().add(this.telaNavio[i]);
    	}
	}
	// Manipulador de Acoes - Botoes  
	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				// Se marcar RadioButton
				if (e.getActionCommand().equals("horizontal")){
					setPosicao(true);
				}
				if (e.getActionCommand().equals("vertical")){
					setPosicao(false);
				}
				// Se "apertar" botao Voltar 
				if (e.getSource() == getButtonVoltar()) {
						if (jogador.getNumEscolhas() > 1 ){
							jogador.setNumEscolhas(jogador.getNumEscolhas()-1);
							setVolta(true);
							getLabel().setIcon(getImagem()[jogador.getNumEscolhas()-1]);
							getTextArea().setText("");
							getConsulta().ConsultaNavioTextFiel(jogador.getNumEscolhas()-1, getTextArea());	
						}
				}
				// Se "apertar" botao Escolher
				if (e.getSource() == getButtonProximo()) {
					// Muda Imagem do navio/peca
					setEscolha(true);
				}
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(null, "ERRO - Uso incorreto do Console");
				exception.printStackTrace();
			}
		}
	}
	public JPanel getPanelConsole1() {
		return panelConsole1;
	}
	public void setPanelConsole1(JPanel panelConsole1) {
		this.panelConsole1 = panelConsole1;
	}
	public JPanel getPanelConsole2() {
		return panelConsole2;
	}
	public void setPanelConsole2(JPanel panelConsole2) {
		this.panelConsole2 = panelConsole2;
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
	public Jogador getJogador() {
		return jogador;
	}
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
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

	public JPanel[] getTelaNavio() {
		return telaNavio;
	}

	public void setTelaNavio(JPanel[] telaNavio) {
		this.telaNavio = telaNavio;
	}

	public JPanel[] getTelaImg() {
		return telaImg;
	}

	public void setTelaImg(JPanel[] telaImg) {
		this.telaImg = telaImg;
	}

	public JPanel[] getCaixaVida() {
		return caixaVida;
	}

	public void setCaixaVida(JPanel[] caixaVida) {
		this.caixaVida = caixaVida;
	}

	public JLabel[] getImg() {
		return img;
	}

	public void setImg(JLabel[] img) {
		this.img = img;
	}

	public JLabel[] getNome() {
		return nome;
	}

	public void setNome(JLabel[] nome) {
		this.nome = nome;
	}

	public JLabel[] getVida() {
		return vida;
	}

	public void setVida(JLabel[] vida) {
		this.vida = vida;
	}

	public JPanel getCards() {
		return cards;
	}

	public void setCards(JPanel cards) {
		this.cards = cards;
	}
	
}
