package persistencia;

public class Salvar {
	public static String OUT_PATH = "../../docs/testes/java/out_java_";
	
	public static void writeResultAlltoall(String outLocation, int tamDados, int qtdRept, int qtdProcs, long timeMin, long timeMax, long timeAvg, long timeSpawn){
		String header = String.format("%-10s %-15s %-15s %-17s %-17s %-17s %-17s%n",
									  "#bytes", "#repetitions", "#processes", "t_min[µsec]", "t_max[µsec]", "t_avg[µsec]", "spawn_time[µsec]");
		String result = String.format("%-9d| %-14d| %-14d| %-16d| %-16d| %-16d| %-16d|%n",
									  tamDados, qtdRept, qtdProcs, timeMin, timeMax, timeAvg,timeSpawn);
		
		writeResult(outLocation, header, result);		
	}
	
	public static void writeResultMulti(String outLocation, int tamDados, int qtdProcs, int qtdRept, long timeExec, long timeSpawn) {
		String header = String.format("%-13s %-13s %-12s %-16s %-12s %n", "bytes", "processos", "repeticoes", "tempo exec[µsec]", "tempo de spawn[µsec]");
		String result = String.format("%-13d| %-12d| %-12d| %-15d| %-20d| %n", tamDados, qtdProcs, qtdRept, timeExec, timeSpawn); 
		
		writeResult(outLocation, header, result);
	}
	
	public static void writeResultPeer(String outLocation, int tamDados, int qtdRept, long timeExec, long timeSpawn){
		String header = String.format("%-13s %-12s %-15s %-12s %n", "bytes", "repeticoes", "t[µsec]", "tempo de spawn[µsec]");
		String result = String.format("%-13d| %-12d| %-14d| %-20d| %n",tamDados, qtdRept,timeExec,timeSpawn);
		
		writeResult(outLocation, header, result);
	}
	
	private static void writeResult(String outLocation, String header, String result){
		ArquivoLeitura arq = new ArquivoLeitura();
		if (!arq.abrir(outLocation)) {
			ArquivoEscrita arqOut = new ArquivoEscrita();
			arqOut.abrir(outLocation);
			arqOut.escrever(header);
			arqOut.fechar();
		}

		concatenar(outLocation, result);		
	}
	
	private static boolean concatenar(String local, String conteudo){
		ArquivoLeitura arqIn = new ArquivoLeitura();
		if(arqIn.abrir(local)){
			String strIn = arqIn.lerTudo();
			arqIn.fechar();
			
			String strOut = strIn + conteudo;

			ArquivoEscrita arqOut = new ArquivoEscrita();
			arqOut.abrir(local);
			arqOut.escrever(strOut);
			arqOut.fechar();
			return true;
		}else{
			return false;
		}
	}
}
