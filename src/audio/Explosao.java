package audio;

import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;

public class Explosao {

	final String explosion = "mp3/explosion.mp3";
	final String missil = "mp3/missil.mp3";
	
	public Explosao(int valor) throws FileNotFoundException, JavaLayerException, InterruptedException{
		Som mp3;
		if (valor == 1){
			mp3 = new Som(missil);
			mp3.play();
			while (!mp3.getPlayer().isComplete()){
				//System.out.println("espera acabar...");
			}
			mp3 = new Som(explosion);
			mp3.play();
		}
		if (valor == 2){
			mp3 = new Som(missil);
			mp3.play();
			while (!mp3.getPlayer().isComplete()){
				//System.out.println("espera acabar...");
			}
		}
	}
	public static void main(String[] args) throws FileNotFoundException, JavaLayerException, InterruptedException {
		Explosao explosao = new Explosao(2);
	}
}