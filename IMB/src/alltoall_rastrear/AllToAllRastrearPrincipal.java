package alltoall_rastrear;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AllToAllRastrearPrincipal {	
	public static void main(String[] args) {
		int tamMsg = Integer.parseInt(args[0]);
		int num_proc = Integer.parseInt(args[1]);
		int num_rep = Integer.parseInt(args[2]);
		
		ExecutorService executor = Executors.newFixedThreadPool(num_proc);
		
		String localSaida = "/home/pesquisador/temp/result.txt";
		
		AllToAll alltoall = new AllToAll(localSaida, num_proc, num_proc, num_rep, tamMsg, executor);
		alltoall.run();
	}
}
