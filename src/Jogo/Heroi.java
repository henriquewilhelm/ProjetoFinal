package Jogo;

/**
 * Atributos da classe Heroi 
 * - Nome
 * - Posicao (2 ate 4)
 * - Vida
 * - Porder de Ataque
 * - Porder de Defesa
 */

public class Heroi {
	private String nome;
	private Posicao[] posicao;
	private int vida;
	private double ataque, defesa;
	private boolean superVelocidade;
	private boolean vivo; 

	public Heroi(String nome, Posicao[] posicao, int vida, double ataque, double defesa) {
		
		 setNome(nome);
		 setPosicao(posicao);
		 setVida(vida);
		 setAtaque(ataque);
		 setDefesa(defesa);
		 setVivo(false);
	}
	
	public void caminha(String direcao) {
		System.out.printf("O heroi %s ", getNome() + " caminhou para ");
		switch(direcao) {
		case "cima":
			for (int i=0; i<this.posicao.length; i++){ 
				posicao[i].moverCima((isSuperVelocidade() ? 2 : 1));
			}
			System.out.printf("cima.");
			break;
		case "baixo":
			for (int i=0; i<this.posicao.length; i++){
				posicao[i].moverBaixo((isSuperVelocidade() ? 2 : 1));
			}
			System.out.printf("baixo.");
			break;
		case "esquerda":
			for (int i=0; i<this.posicao.length; i++){
				posicao[i].moverEsquerda((isSuperVelocidade() ? 2 : 1));
			}
			System.out.printf("esquerda.");
			break;
		case "direita":
			for (int i=0; i<this.posicao.length; i++){
				posicao[i].moverDireita((isSuperVelocidade() ? 2 : 1));
			}
			System.out.printf("direita.");
			break;
		}
		System.out.println("\n");
		setSuperVelocidade(false);
	}

	public void corre(String direcao) {
		System.out.printf("O heroi %s ", getNome() + " correu para ");
		switch(direcao) {
		case "cima":
			for (int i=0; i<this.posicao.length; i++){
				posicao[i].moverCima((isSuperVelocidade() ? 4 : 2));
			}
			System.out.printf("cima.");
			break;
		case "baixo":
			for (int i=0; i<this.posicao.length; i++){
				posicao[i].moverBaixo((isSuperVelocidade() ? 4 : 2));
			}
			System.out.printf("baixo.");
			break;
		case "esquerda":
			for (int i=0; i<this.posicao.length; i++){
				posicao[i].moverEsquerda((isSuperVelocidade() ? 4 : 2));
			}
			System.out.printf("esquerda.");
			break;
		case "direita":
			for (int i=0; i<this.posicao.length; i++){
				posicao[i].moverDireita((isSuperVelocidade() ? 4 : 2));
			}
			System.out.printf("direita.");
			break;
		}
		System.out.println("\n");
		setSuperVelocidade(false);
	}

	public void ataca(Heroi inimigo) {
		// int dano = (int) (getAtaque() - inimigo.getDefesa());
		// Ou
		int dano = (int) (this.getAtaque() - inimigo.getDefesa());
		// Ou
		// int dano = (int) (this.ataque - inimigo.defesa);
		// Ou
		// int dano = (int) (ataque - inimigo.getDefesa());
		// Ou
		// int dano = (int) (this.ataque - inimigo.getDefesa());
		// Ou...
		// if(dano > 0) {
		// inimigo.setVida(inimigo.getVida() - dano);
		// } else {
		// inimigo.setVida(inimigo.getVida() - 1);
		// // inimigo.vida -= 1;
		// // inimigo.vida = inimigo.getVida() - 1;
		// // inimigo.vida = inimigo.vida - 1;
		// }

		inimigo.setVida(inimigo.getVida() - Math.max(1, dano));
		System.out.println("O heroi "+ this.getNome() +" atacou ("+ this.getAtaque() +") "
				+ "o heroi " + inimigo.getNome() + " ("+inimigo.getDefesa()+") e "
				+ "o mesmo recebeu "+ dano +" de dano, "
				+ "ficando com "+ inimigo.getVida() +".");

//		inimigo.setVida(inimigo.getVida()
//				- Math.max(1, ((int) (this.getAtaque() - inimigo.getDefesa()))));
	}
	
	public void tomarPocaoVida() {
		this.setVida(this.getVida() + 10);
//		this.vida += 10;
		
		System.out.println("O heroi "+ getNome()+" tomou uma po��o de vida e "
				+ "agora est� com "+this.getVida()+" pontos de vida.");
	}
	
	public void tomarPocaoVelocidade() {
		setSuperVelocidade(true);
		System.out.println("O heroi "+ getNome() +" tomou uma po��o de velocidade.");
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

	public double getAtaque() {
		return ataque;
	}

	public void setAtaque(double ataque) {
		this.ataque = ataque;
	}

	public double getDefesa() {
		return defesa;
	}

	public void setDefesa(double defesa) {
		this.defesa = defesa;
	}
	
	// get para o tipo boolean
	public boolean isSuperVelocidade() {
		return superVelocidade;
	}

	public void setSuperVelocidade(boolean superVelocidade) {
		this.superVelocidade = superVelocidade;
	}

}