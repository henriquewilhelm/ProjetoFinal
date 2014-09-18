package Jogo;

/**
 * Atributos da classe Heroi 
 * - Nome
 * - Posicao (2 ate 4)
 * - Vida
 * - Status vivo/morto
 */

public class Heroi {
	private String nome;
	private Posicao[] posicao;
	private int vida;
	private boolean vivo; 

	public Heroi(String nome, Posicao[] posicao, int vida) {
		
		 setNome(nome);
		 setPosicao(posicao);
		 setVida(vida);
		 setVivo(false);
	}

	public void caminha(String direcao) {
		System.out.printf("O heroi %s ", getNome() + " caminhou para ");
		switch(direcao) {
		case "cima":
			for (int i=0; i<this.posicao.length; i++){ 
				posicao[i].moverCima(1);
			}
			System.out.printf("cima.");
			break;
		case "baixo":
			for (int i=0; i<this.posicao.length; i++){
				posicao[i].moverBaixo(1);
			}
			System.out.printf("baixo.");
			break;
		case "esquerda":
			for (int i=0; i<this.posicao.length; i++){
				posicao[i].moverEsquerda(1);
			}
			System.out.printf("esquerda.");
			break;
		case "direita":
			for (int i=0; i<this.posicao.length; i++){
				posicao[i].moverDireita(1);
			}
			System.out.printf("direita.");
			break;
		}
		System.out.println("\n");
	}
	public void tomarPocaoVida() {
		this.setVida(this.getVida() + 10);
		System.out.println("O heroi "+ getNome()+" tomou uma pocao de vida e "
				+ "agora esta com "+this.getVida()+" pontos de vida.");
	}
	public boolean isVivo() {
		return vivo;
	}
	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Posicao[] getPosicao() {
		return posicao;
	}
	public void setPosicao(Posicao[] posicao) {
		this.posicao = posicao;
	}
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}
}