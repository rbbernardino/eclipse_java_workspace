package alltoall;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

import persistencia.Salvar;


public class AllToAll{
	private final String NOME = "Anel";
	private final String OUT_LOCATION;
	private final int NUM_PROC;
	private final int NUM_REP;
	private final int TAM_MSG;
	
	private BlockingQueue<Message> mailbox = new LinkedBlockingQueue<Message>();
	
	private Message msg;
	private ExecutorService executor;
	
	public AllToAll(String outLocation, int num_proc, int threads, int num_rep, int tamMsg, ExecutorService executor) {
		OUT_LOCATION = outLocation;
		NUM_PROC = num_proc;
		NUM_REP = num_rep;
		TAM_MSG = tamMsg;
		this.executor = executor;
	}

	public void send(Message m) {
		mailbox.add(m);
	}
	
	public long getTimeMicro() {
		return System.nanoTime() / 1000;
	}

	public byte[] generateData(int tamDados) {
		byte[] dado = new byte[tamDados];
		return dado;
	}
	
	public Proc[] spawnProcs(int n) {
		Proc[] procList = new Proc[n];
		
		for (int i = 0; i < n; i++) {
			procList[i] = new Proc(i+1);
		}
		return procList;
	}

	private long[] finalize(int n){
		try {
			long[] endTimeList = new long[n];
		
			for(int i=0; i < n; i++){
				Message recvMsg = mailbox.take();
				Object[] tuple = (Object[]) recvMsg.value;
				//Proc from = (Proc) tuple[0];
				long endTime = (Long) tuple[1];
				endTimeList[i] = endTime;
				
				//System.out.println("Processo " + from.getNodeId() + " Terminou!!!");
			}
			return endTimeList;
			
		} catch (InterruptedException e) {
			System.out.println("Thread AllToAll Interrmopida!");
			return null;
		}
	}
	
	private long sum(long[] list){
		long result = 0;
		for(long elem : list){
			result += elem;
		}
		return result;
	}
	
	// esse supress warning é necessário por causa do Collections, não sei bem a razão...
	public void run(){
		// variáveis de medição
		long spawnStart, spawnEnd, timeSpawn, execStart, timeMin, timeMax, timeAvg;
		long[] endTimeList, timeList;
		
		// geração de dados feita ao criar-se o anel!
		msg = new Message(0, generateData(TAM_MSG));

		spawnStart = getTimeMicro();
		Proc[] procList = spawnProcs(NUM_PROC);
		spawnEnd = getTimeMicro();

		timeSpawn = spawnEnd - spawnStart;

		// Inicia-se o teste!
		execStart = getTimeMicro();
		for (Proc proc : procList) {
			proc.setRepetitions(NUM_REP);
			proc.setData(((byte[]) msg.value).clone());
			proc.setParent(this);
			proc.setProcList(procList.clone());
			executor.execute(proc);
		}

		endTimeList = finalize(NUM_PROC);
		
		timeList = new long[NUM_PROC];
		for (int i=0; i < NUM_PROC; i++){
			timeList[i] = endTimeList[i] - execStart;
		}
		
		Arrays.sort(timeList);
		timeMin = timeList[0];
		timeMax = timeList[NUM_PROC-1];
		timeAvg = sum(timeList)/timeList.length;

		Salvar.writeResultAlltoall(OUT_LOCATION, TAM_MSG, NUM_REP, NUM_PROC, timeMin, timeMax, timeAvg, timeSpawn);
		
		System.out.println("============Teste concluido============\n" +
				"Linguagem: Java\r\n" +
				"Teste: " + NOME + "\r\n" +
				"Mensagem: " + TAM_MSG + " Bytes\r\n" +
				"Processos: " + NUM_PROC + "\r\n" +
				"Repeticoes: " + NUM_REP);
		
		// 0 significa sair com status normal, 1 seria com erro
		System.exit(0);
	}
}