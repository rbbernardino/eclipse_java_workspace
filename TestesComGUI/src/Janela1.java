
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
//import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
  
public class Janela1 extends JFrame implements ActionListener{
	JButton bTexto, bImg;
	ImageIcon img = new ImageIcon("/home/rodrigo/temp/img.gif");
	
	public Janela1(){
		setTitle("Testando isso aki!");
		setSize(800, 600);
		setLocation(200, 100);
		//setResizable(false);
		getContentPane().setBackground(Color.gray);
		criaBotoes();
		add(bTexto);
		add(bImg);
		getContentPane().setLayout(new FlowLayout());
		fazerGradiente();
	}

	private void criaBotoes(){
		bTexto = new JButton("testeee");
		bTexto.setBackground(Color.CYAN);
		bTexto.setForeground(Color.BLUE);
		bTexto.setEnabled(true);
		bTexto.setToolTipText("cool!!");
		bTexto.setMnemonic(KeyEvent.VK_E);
		bTexto.setEnabled(true);
		bTexto.addActionListener(this);
		
		bImg = new JButton(img);
		bImg.setHorizontalTextPosition(AbstractButton.LEFT);
		bImg.setToolTipText("doiiiis");
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == bTexto){
			bTexto.setBounds(150, 250, 100, 100);
			JOptionPane.showMessageDialog(null, "Botao com texto pressionado!!!\n" +
					"Comando: " + e.getActionCommand());
		}else
			if(e.getSource() == bImg){
				JOptionPane.showMessageDialog(null, "Botao com imagem pressionado!!!");
			}
	}
	
	private void fazerGradiente() {
		UIManager manager=new UIManager();
		LinkedList<Object> a=new LinkedList<Object>();  
		a.add(0.3);  
		a.add(0.3);
//		a.add(new ColorUIResource(44,208,206));
		a.add(new ColorUIResource(23,161,98));
		a.add(new ColorUIResource(136,255,254));
		a.add(new ColorUIResource(23,161,98));
//		a.add(new ColorUIResource(23,161,98));
		manager.put("Button.gradient",a);
	}
	
//	public static void main (String[] args){
//		JFrame janela = new Janela1();
//		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		janela.setVisible(true);
//	}
}
