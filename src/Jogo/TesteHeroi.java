package Jogo;

public class TesteHeroi {

	public static void main(String[] args) {
		Heroi barco = new Heroi("", 0, 100, 75, 50);
		Heroi navio = new Heroi("", 0, 100, 65, 40);
		
		barco.caminhar();
		barco.correr();
		barco.pocaoVelocidade();
		barco.caminhar();
		
		barco.atacar(navio);
		navio.pocaoVida();
		
		barco.atacar(navio);
		barco.atacar(navio);
		barco.atacar(navio);
		
		Mapa mapa = new Mapa(10,10, 20);
		System.out.println(mapa);
	}
}
