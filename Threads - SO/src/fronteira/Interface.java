package fronteira;

import entidade.Persistencia;
import entidade.Processo;
import java.util.Scanner;

public class Interface {

//	static final String LOCAL = "C:/Users/Lidia/Desktop/procs.log";
//	static final String LOCAL = "/home/rodrigo/procs.log";
	static final String LOCAL = "C:/procs.log";
	
	static Scanner input = new Scanner(System.in);
	
	static String log = "";
	
	public static int menuPrincipal() {
		String menuPrincipal = "Sistemas Operacionais - Trabalho Prático\n" +
				"Gerenciador de Processos usando Round Robin\n" +
				"Professora: Danielle Valente\n" +
				"Alunos: Harry, Josiane, Lidia, Rodrigo, William\n\n" +

				"Digite a quantidade de processos desejados";
		
		System.out.println(menuPrincipal);
		
		int k = input.nextInt();
		
		System.out.println("\nProcessos a serem criados: " + k);
		
		System.out.println("\nO arquivo de log será salvo na pasta atual, com o nome procs.log\n");
		
		return k;
	}

	public static void imprime(String s){
		System.out.println(s);
		log += s + "\n";
	}
	
	public static void imprimePCB( Processo p ){
		String atual =  "\n------------------------------------\n" +
						"PCB " + p.pid + "\n\n" +
						"PID: " + p.pid + "\n" +
						"Tempo de ciclo da CPU: " + p.tempoDeCicloTotal + "ms\n" +
						"Hora de admissao no sistema: " + p.horaDeAdmissao + "\n" +
						"Tempo de Uso da CPU restante: " + p.tempoDeUsoRestante + "ms\n" +
						"Tempo de Espera: " + p.tempoDeEspera + "ms\n" +
						"Hora de termino: " + p.horaDeTermino;
		
		log += atual;
		
		System.out.println(atual);	
	}
	
	public static void criaLog(){
		Persistencia arqOUT = new Persistencia();
		String arqSTR = Interface.log;

		arqOUT.abrir(LOCAL);
		arqOUT.escrever(arqSTR);
		arqOUT.fechar();
	}
	
}
