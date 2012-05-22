package fronteira.principal;

import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class Tutorial extends JFrame {

	public void montarTuto() {

		URL ende = Tutorial.class.getResource("Tutorial.html");
		JFrame tutorialWindow = new JFrame();
		tutorialWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		tutorialWindow.setSize(640, 480);
		tutorialWindow.setVisible(true);
		
		try {
			JEditorPane tutorial = new JEditorPane(ende);
			tutorial.setEditable(false);
			JScrollPane tutoPanel = new JScrollPane(tutorial);
			tutoPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			tutoPanel.setSize(new Dimension(250, 145));
			tutorialWindow.add(tutoPanel);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
