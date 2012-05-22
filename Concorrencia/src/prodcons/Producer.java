package prodcons;

import java.util.concurrent.BlockingQueue;

@SuppressWarnings("rawtypes")
public class Producer implements Runnable {
	private final BlockingQueue queue;

	public Producer(BlockingQueue q) {
		queue = q;
	}
	
	@SuppressWarnings("unchecked")
	public void run() {
		while (true) {
			try {
				queue.put("Java Magazine");
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}