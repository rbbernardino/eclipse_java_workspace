package entidade;

import java.util.ArrayList;
import java.util.Date;

public class Escalonador extends Thread {

	// 2000 milisegundos = 2 segundos
	private final int QUANTUM = 2000;
	private Processo procAtual;
	public FilaDeProntos filaDeProntos;
	private ArrayList<Processo> filaTerminados = new ArrayList<Processo>();

//	public boolean pronto = false;

	public Escalonador(FilaDeProntos filaDeProntos) {
		this.filaDeProntos = filaDeProntos;
	}

	public void sonoSeguro(int tempo) {
		try {
			Thread.sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private String horaAtual() {
		Date data = new Date();
		String horaAtual = data.toString();
		return horaAtual;
	}

	public void run() {
		while (!filaDeProntos.estaVazio()) {
			synchronized (filaDeProntos) {
				procAtual = filaDeProntos.getPrimeiro();
			}

			if (procAtual.tempoDeUsoRestante <= QUANTUM) {
				// neste metodo o cronometro eh parado e eh incrementado o tempo
				// de espera
				procAtual.acordar();
				fronteira.Interface.imprime("\nACORDAR Thread: " + procAtual.pid + "\n");


				sonoSeguro(procAtual.tempoDeUsoRestante);

				procAtual.tempoDeCicloTotal += procAtual.tempoDeUsoRestante;
				procAtual.tempoDeUsoRestante = 0;
				procAtual.horaDeTermino = horaAtual();

				filaTerminados.add(procAtual);

			} else {
				procAtual.acordar();
				fronteira.Interface.imprime("\nACORDAR Thread: " + procAtual.pid + "\n");

				sonoSeguro(QUANTUM);

				// neste metodo o cronometro da thread eh inciado
				procAtual.dormir();
				fronteira.Interface.imprime("\nDORMIR Thread: " + procAtual.pid);

				
				procAtual.tempoDeUsoRestante -= QUANTUM;
				procAtual.tempoDeCicloTotal += QUANTUM;

				synchronized (filaDeProntos) {
					filaDeProntos.enfileirar(procAtual);
				}
			}
		}
		// quando encerrar tudo, imprime os PCBS dos procs na fila de terminados
		// Fronteira.Interface.imprimeLista( filaTerminados )
		
		for( int i = 0; i < filaTerminados.size(); i++ ){
			Processo p = filaTerminados.get(i);
			fronteira.Interface.imprimePCB(p);
		}
		
		// finaliza, criando o log...
		fronteira.Interface.criaLog();
		
		
	}
}
