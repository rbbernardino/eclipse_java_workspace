package prodcons;

import java.util.concurrent.BlockingQueue;

@SuppressWarnings("rawtypes")
public class Consumer implements Runnable {
  private final BlockingQueue queue;

  public Consumer(BlockingQueue q) {
    queue = q;
  }
 
  public void run() {

    while (true) {
      try {
        System.out.println(queue.take());
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}