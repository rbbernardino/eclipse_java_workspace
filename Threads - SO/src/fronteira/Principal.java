package fronteira;

import entidade.Escalonador;
import entidade.Gerador;
import entidade.FilaDeProntos;

public class Principal {
	
	public static void main(String args[]) {

		int qtdProc = Interface.menuPrincipal();

		FilaDeProntos filaDeProntos = new FilaDeProntos();

		Gerador gerador = new Gerador(qtdProc, filaDeProntos);
		Escalonador escalonador = new Escalonador(filaDeProntos);

		gerador.start();
		
		while(!gerador.terminado);
		
		escalonador.start();
	}

}
