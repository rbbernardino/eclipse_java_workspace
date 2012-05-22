package entidade;

public class Processo extends Thread {
	Cronometro cronometro = new Cronometro();

	public int pid;
	public int tempoDeCicloTotal;
	public String horaDeAdmissao;
	public int tempoDeUsoRestante;
	public int tempoDeEspera;
	public String horaDeTermino;

	public boolean espera;

	public Processo(int pid, int tempoDeCicloTotal, String horaDeAdmissao,
			int tempoDeUsoRestante, int tempoDeEspera) {
		super();
		this.pid = pid;
		this.tempoDeCicloTotal = tempoDeCicloTotal;
		this.horaDeAdmissao = horaDeAdmissao;
		this.tempoDeUsoRestante = tempoDeUsoRestante;
		this.tempoDeEspera = tempoDeEspera;
	}

	public synchronized void dormir() {
		this.cronometro.inicio();
		this.espera = true;
	}

	public synchronized void acordar() {
		this.cronometro.fim();
		this.tempoDeEspera += (int) this.cronometro.duracao();
		this.espera = false;
		notifyAll();
	}

	private void trabalha(){
		try {
			sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// if necessario para evitar que imprima mesmo depois de
		// ter sido configurado para que parasse
		if (!espera){
			fronteira.Interface.imprime("EXECUTA Thread: " + pid);		
		}
	}
	
	public void run() {
		fronteira.Interface.imprimePCB( this );
		while (tempoDeUsoRestante != 0) {
			// verifica se deve esperar ou nao
			synchronized (this) {
				while (espera) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			trabalha();

		}
		
		fronteira.Interface.imprime("\nFINALIZOU Thread: " + pid + "\n");
	}
}