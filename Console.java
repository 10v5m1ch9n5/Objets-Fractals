import javax.swing.*;
import java.awt.*;

public class Console extends JFrame {
	private static JTextArea terminal;
	private JScrollPane asc;
	
	public Console(int yOffset, int width, int height) {
		super("Console");
		setVisible(true);
		setResizable(false);
		setLayout(null);
		setBounds(0, yOffset, width, height);
		
		terminal = new JTextArea("C'est parti mon kiki (^0^)/\n");
		terminal.setBounds(0,0,width, (int)((double)height*9.0/10.0));
		terminal.setLineWrap(true);
		asc = new JScrollPane(terminal);
		asc.setBounds(0,0,width-getInsets().left*2, (int)((double)height*9.0/10.0));
		this.add(asc);
	}
	
	public static void afficher(String texte){
		terminal.append(texte);
	}
}
