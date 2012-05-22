import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Proc extends Thread {
	public boolean espera = false;
	public BlockingQueue<String> mailbox = new LinkedBlockingQueue<String>();
	public int tipo;
	private Proc target;
	
	public Proc(String name, int tipo) {
		this.setName(name);
		this.tipo = tipo;
	}
	
	public Proc(String name, int tipo, Proc target) {
		this.setName(name);
		this.tipo = tipo;
		this.target = target;
	}

	private void send(String msg) {
		try {
			target.mailbox.add(msg);
		} catch (IllegalStateException e) {
			System.out.println("Falha ao adicionar msg, fila cheia! " + this.getName());
			e.printStackTrace();
		}
	}

	private void recv() {
		try {
			String msgRecv = (String) this.mailbox.take();
			System.out.println("---------------\n" +
					"Proc: " + this.getName() + "\n" +
					"Recebi! Msg: " + msgRecv);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		switch (tipo) {

		case 0:
			recv();
			break;

		case 1:
			send("oi!");
			break;
		}	
	}
}
