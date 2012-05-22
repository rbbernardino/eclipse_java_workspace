package prodcons;

import java.util.concurrent.ArrayBlockingQueue;

import java.util.concurrent.BlockingQueue;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;

@SuppressWarnings("rawtypes")
public class ProducerConsumer {
	public static void main(String... args) throws Exception {

		BlockingQueue q = new ArrayBlockingQueue(10);

		ExecutorService executor = Executors.newCachedThreadPool();

		Producer p = new Producer(q);
		
		// 2 produtores
		executor.execute(p);
		executor.execute(p);

		// 6 consumidores
		executor.execute(new Consumer(q));
		executor.execute(new Consumer(q));
		executor.execute(new Consumer(q));
		executor.execute(new Consumer(q));
		executor.execute(new Consumer(q));
		executor.execute(new Consumer(q));
	}
}