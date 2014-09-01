package Jogo;

public class Posicao {
	private int x;
	
	public Posicao(int x) {
		setX(x);
	}
	
	public void moverCima(int movimento) {
		setX(getX()+movimento*10); 
	}
	
	public void moverBaixo(int movimento) {
		setX(getX()-movimento*10);
	}
	
	public void moverDireita(int movimento) {
		setX(getX() + movimento);
	}
	
	public void moverEsquerda(int movimento) {
		setX(getX() - movimento);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
}