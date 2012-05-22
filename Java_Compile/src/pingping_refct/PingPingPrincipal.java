package pingping_refct;

public class PingPingPrincipal {

	public static void main(String[] args) { 
		int tamMsg = Integer.parseInt(args[0]);
		int qtdMsg = Integer.parseInt(args[1]);
				
		PingPing p = new PingPing(tamMsg, qtdMsg);
		p.start();
	}
	
}