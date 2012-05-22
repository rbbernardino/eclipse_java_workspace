package alltoall;

public class Message {
	// volátil:
	// variable's value will be modified by different threads.
	// The value of this variable will never be cached thread-locally: all reads and writes will go straight to "main memory";
	// Access to the variable acts as though it is enclosed in a synchronized block, synchronized on itself.
	public volatile Object value;
	public int source;
	
	public Message(int source, Object value) {
		this.source = source;
		this.value = value;
	}
}
