package entidade;

import java.util.Date;
import java.util.Random;


public class Gerador extends Thread {

	private FilaDeProntos filaDeProntos;
	private static Random geraAleatorio = new Random();
	public int qtdProcessos;

	public boolean terminado = false;

	public Gerador(int qtdProcessos, FilaDeProntos filaDeProntos) {
		this.qtdProcessos = qtdProcessos;
		this.filaDeProntos = filaDeProntos;
	}

	public void run() {
		for (int i = 0; i < qtdProcessos; i++) {

			Processo p = criaProcesso(i);

			synchronized (filaDeProntos) {
				filaDeProntos.enfileirar(p);
			}

			dormeTempoAleatorio();
		}
		terminado = true;
		fronteira.Interface.imprime("\n\nGerador encerrou...\n");
		
	}

	private void dormeTempoAleatorio() {
		// tempo aleatório entre 0 e 3 segundos
		int tempoDeSono = geraAleatorio.nextInt(3000);

		try {
			Thread.sleep(tempoDeSono);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private String horaAtual(){
		Date data = new Date();
		String horaAtual = data.toString();
		return horaAtual;
	}

	public Processo criaProcesso(int i) {

		int pid = i + 1;
		int tempoDeCiclo = 0;
		String horaAdmissao = horaAtual();
		
		// 10.000 milisegundos = 10 segundos
		int tempoDeUsoRestante = geraAleatorio.nextInt(10000);
		
		int tempoDeEspera = 0;

		Processo p = new Processo(pid, tempoDeCiclo, horaAdmissao,
				tempoDeUsoRestante, tempoDeEspera);

		p.dormir();

		p.start();

		return p;
	}
}
