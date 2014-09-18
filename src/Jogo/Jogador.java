package Jogo;

import java.util.ArrayList;

/**
 * Todos atributos da classe Jogador 
 * - Nome
 * - Herois (5)
 * - Numero Navios
 * - Numero de Medalhas (Uma a cada 5 vitorias)
 * - Numero de Vitorias
 * - Numero de Derrotas
 * - Total de Jogos
 *  
 * Atributos do Jogador para o Banco de Dados
 * - Nome
 * - Numero Imagem
 * - String Pais
 * - Numero de Medalhas (Uma a cada 5 vitorias)
 * - Numero de Vitorias 
 * - Numero de Derrotas
 * - Total de Jogos
 */

public class Jogador {

	private String nome;
	private String senha;
	private int numEscolhas = 1;
	private ArrayList<Heroi> herois;
	private int vida;
	private int imagem;
	private String pais;
	private int numeroNavios;
	private int medalhas;
	private int vitorias;
	private int derrotas;
	private int totalJogos;
	// Auxiliares
	private int contadorHeroi = 0;
	private int contadorPosicao = 0;
	private boolean vez = false;
	
	public Jogador(){
		this.nome = "";
		this.imagem = 0;
		this.herois = new ArrayList<Heroi>();
		this.numeroNavios = 5;
		this.vida = 15;
		this.medalhas = 0;
		this.vitorias = 0;
		this.derrotas = 0;
		this.totalJogos = 0;
		this.criaHerois();
	}
	
	public Jogador(String nome){
		setNome(nome);
		this.imagem = 0;
		this.herois = new ArrayList<Heroi>();
		this.numeroNavios = 5;
		this.vida = 15;
		this.medalhas = 0;
		this.vitorias =0;
		this.derrotas = 0;
		this.totalJogos = 0;
		this.criaHerois();
	}

	public void criaHerois(){
		
		Posicao[] posicoesNavio1 = new Posicao[2];
		Heroi Navio1 = new Heroi("Barco", posicoesNavio1, 2);
		
		Posicao[] posicoesNavio2 = new Posicao[2];
		Heroi Navio2 = new Heroi("Costeiro", posicoesNavio2, 2);
		
		Posicao[] posicoesNavio3 = new Posicao[3];
		Heroi Navio3 = new Heroi("Destroyer", posicoesNavio3, 3);
		
		Posicao[] posicoesNavio4 = new Posicao[4];
		Heroi Navio4 = new Heroi("Cargueiro", posicoesNavio4, 4);
		
		Posicao[] posicoesNavio5 = new Posicao[4];
		Heroi Navio5 = new Heroi("Submarino", posicoesNavio5, 4);
		
		this.herois.add(Navio1);
		this.herois.add(Navio2);
		this.herois.add(Navio3);
		this.herois.add(Navio4);
		this.herois.add(Navio5);
	}
	
	public void addHeroi(int posicao) {
		if (getContadorHeroi() <= 4){
			if (getContadorPosicao()==getHerois().get(getContadorHeroi()).getPosicao().length){
				setContadorHeroi(getContadorHeroi()+1);
				setContadorPosicao(0);
			}
			this.getHerois().get(getContadorHeroi()).getPosicao()[getContadorPosicao()] = new Posicao(posicao);
			setContadorPosicao(getContadorPosicao()+1);
			if  (getContadorHeroi() == 4 && getContadorPosicao() == 3){
				setVez(true);
			}
		}
		else {
				setContadorHeroi(0);
		}
	}	
	
	public boolean verificaPosicao(int posicao) {
		 for (int contadorHeroi = 0; contadorHeroi < 5; contadorHeroi++){
	        	for (int contadorPosicao = 0; contadorPosicao < this.getHerois().get(contadorHeroi).getPosicao().length; contadorPosicao++){
	        		if (this.getHerois().get(contadorHeroi).getPosicao()[contadorPosicao].getX() == posicao){
						return true;
	        		}
	        	}
		 }
		return false;
	}
	
	public int verificaHeroi(int posicao) {
		int aux = 0; 
		for (int contadorHeroi = 0; contadorHeroi < 5; contadorHeroi++){
	        	for (int contadorPosicao = 0; contadorPosicao < this.getHerois().get(contadorHeroi).getPosicao().length; contadorPosicao++){
	        		if (this.getHerois().get(contadorHeroi).getPosicao()[contadorPosicao].getX() == posicao){
	        			aux = contadorHeroi;
	        		}
	        	}
		 }
		return aux;
	}

	// S and G
	
	public String getNome() {
		return nome;
	}
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isVez() {
		return vez;
	}
	public void setVez(boolean vez) {
		this.vez = vez;
	}
	public int getNumEscolhas() {
		return numEscolhas;
	}
	public void setNumEscolhas(int numRodadas) {
		this.numEscolhas = numRodadas;
	}
	public ArrayList<Heroi> getHerois() {
		return herois;
	}
	public void setHerois(ArrayList<Heroi> herois) {
		this.herois = herois;
	}
	public int getVida() {
		return this.vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}
	public int getNumeroNavios() {
		return numeroNavios;
	}
	public void setNumeroNavios(int numeroNavios) {
		this.numeroNavios = numeroNavios;
	}
	public int getMedalhas() {
		return medalhas;
	}
	public void setMedalhas(int medalhas) {
		this.medalhas = medalhas;
	}	
	public int getVitorias() {
		return vitorias;
	}
	public void setVitorias(int vitorias) {
		this.vitorias = vitorias;
	}	
	public int getDerrotas() {
		return derrotas;
	}
	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}
	public int getTotalJogos() {
		return totalJogos;
	}
	public void setTotalJogos(int totalJogos) {
		this.totalJogos = totalJogos;
	}

	public int getContadorHeroi() {
		return contadorHeroi;
	}

	public void setContadorHeroi(int contadorHeroi) {
		this.contadorHeroi = contadorHeroi;
	}

	public int getContadorPosicao() {
		return contadorPosicao;
	}

	public void setContadorPosicao(int contadorPosicao) {
		this.contadorPosicao = contadorPosicao;
	}

	public int getImagem() {
		return imagem;
	}

	public void setImagem(int imagem) {
		this.imagem = imagem;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
}
