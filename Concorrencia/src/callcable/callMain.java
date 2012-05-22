package callcable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class callMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ExecutorService e = Executors.newSingleThreadExecutor();
		
        Callable<String> c = new Callable<String>() {
        	public String call(){
        		try {
        			synchronized (this) {
        				wait(1000);	
					}
					
				} catch (Exception e) {
				}
				return ".";
        	}
		};
		
		while(true){
			Future<String> f = e.submit(c);
			try {
				System.out.println(f.get());
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				e1.printStackTrace();
			}
		}
		
		
	}

}
