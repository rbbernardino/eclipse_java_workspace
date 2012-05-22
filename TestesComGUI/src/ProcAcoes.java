import java.awt.Color;

import javax.swing.JFrame;

public class ProcAcoes extends Thread{
	private JFrame janela;
	
	public ProcAcoes(JFrame j){
		this.janela = j;
	}
	
	public void run() {
		dormir(3000);
		
		janela.getContentPane().setBackground(Color.RED);
		
		dormir(3000);
		
		janela.setLocation(100, 0);
	}

	private synchronized void dormir(int i) {
		try {
			sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
