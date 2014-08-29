package Jogo;

/** Autor: Henrique Wilhelm
 * Herói (jogo)
 * 
 * Nosso Heroi possui como seus atributos: Seu nome, sua posição atual no mapa (int),
 *  quantidade de vida restante (int), seu poder de ataque e seu poder de defesa(double).
 * 
 * Durante a criação do Heroi, aonde ele sempre possui um nome, uma posição inicial, quantos 
 * pontos de vida ele tem, quanto é seu poder de ataque e quanto é seu poder de defesa (construtor)
 * 
 * Ele caminha: 15 unidades por vez. 
 * Corre: equivalente a 10 caminhadas.
 * Ataca: Recebe um outro heroi como parametro e reduz da vida deste heroi, igual ao poder de 
 * ataque do atacante menos a defesa do atacado. Caso este valor seja reduzido a 0 ou menos deve-se 
 * reduzir pelo menos 1 de dano no atacado.
 * Toma Poção de Vida: Recebe 10 de vida para cada poção utilizada.
 * Toma Poção de velocidade, a velocidade de caminhada dele dobra durante a próxima caminhada/corrida.
 * 
 * Se faz necessário em cada uma das funções um log que diga o que está acontecendo. Ex.:
 * "O heroi <nome_heroi> caminhou."
 * "O heroi <nome_heroi> correu."
 * "O heroi <nome_heroi_1> atacou (<poder_heroi_1>) o heroi <nome_heroi_2> (<defesa_heroid_2) e o mesmo
 *  recebeu <dano total>, ficando com <vida_heroid_2>.
 * "O heroi tomou uma poção de vida e agora está com <vida_heroi> pontos de vida"
 * "O heroi tomou uma poção de velocidade."
 */

public class Heroi {

	private String nome;
	private int posicao;
	private int vida;
	private boolean pocaoVelocidade;
	private double poderAtaque;
	private double poderDefesa;
	
	public Heroi(){
		
	}
	
	public Heroi(String nome, int posicao, int vida, double poderAtaque, double poderDefesa){
			this.nome = nome;
			this.posicao = posicao;
			this.vida = vida;
			this.poderAtaque = poderAtaque;
			this.poderDefesa = poderDefesa;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPosicao() {
		return posicao;
	}
	public void setPosicao(int posicao) {
		this.posicao += posicao;
	}
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}
	public double getPoderAtaque() {
		return poderAtaque;
	}
	public void setPoderAtaque(int poderAtaque) {
		this.poderAtaque = poderAtaque;
	}
	public double getPoderDefesa() {
		return poderDefesa;
	}
	public void setPoderDefesa(int poderDefesa) {
		this.poderDefesa = poderDefesa;
	}
	public void caminhar(){
		if (this.pocaoVelocidade == false)
			this.posicao = this.posicao+15;
		else {
			this.posicao = this.posicao+30;
			this.pocaoVelocidade = false;
		}
		System.out.println("O heroi " + this.nome + " caminhou. Posicao atual: " + this.posicao);
	}
	public void correr(){
		if (this.pocaoVelocidade == false)
			this.posicao = this.posicao + (15*10);
		else {
			this.posicao = this.posicao + ((15*10)*2);
			this.pocaoVelocidade = false;
		}
		System.out.println("O heroi " + this.nome + " correu. Posicao atual: " + this.posicao);
	}
	public void atacar(Heroi heroi){
		int valorAux = 0;
		valorAux = (int) (this.poderAtaque - heroi.poderDefesa);
		if (valorAux!=0){
			heroi.vida = heroi.vida - valorAux;
		}
		else{
			valorAux = -1;
			heroi.vida = heroi.vida - valorAux;
		}
		System.out.println("O heroi " + this.nome + " atacou com poder de ataque " + this.poderAtaque + " no Heroi " + heroi.nome + " com poder de defesa " + heroi.poderDefesa + " e o mesmo recebeu " + valorAux + " pontos, ficando com " + heroi.vida);
	}
	public void pocaoVida(){
		this.vida = vida + 10;
		System.out.println("O heroi " + this.nome + " tomou pocao de vida e agora esta com " + this.vida + " pontos de vida!");
	}
	
	public void pocaoVelocidade(){
		this.pocaoVelocidade = true;
		System.out.println("O heroi " + this.nome + " tomou uma pocao de velocidade!");
	}
}
