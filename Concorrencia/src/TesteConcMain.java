import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TesteConcMain {

	public static void main(String args[]){
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		Proc receiver = new Proc("Receiver", 0);
		
		Proc sender = new Proc("Sender", 1, receiver);
		
		executor.execute(receiver);
		executor.execute(sender);

		executor.shutdown();
		//receiver.start();
		//sender.start();
	}	
}
