import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuMandelbrot extends JFrame implements ActionListener {
	private JButton afficher;
	private JButton fermer;
	private JTextField nmax;
	private JTextField rayonConvergence;
	private PlanComplexe pc;
	
	public MenuMandelbrot(int width, int height){
		super("Menu Mandelbrot");
		setVisible(true);
		setResizable(false);
		setLayout(null);
		setBounds(0,0,width,height);
		
		afficher = new JButton("Afficher");
		afficher.setBounds((int)(width/10.0), (int)(height*8.0/10.0), (int)(width/4.0), (int)(height/10.0));
		afficher.addActionListener(this);
		this.add(afficher);
		
		fermer = new JButton("Fermer");
		fermer.setBounds((int)(width*6.0/10.0), (int)(height*8.0/10.0), (int)(width/4.0) ,(int)(height/10.0));
		fermer.addActionListener(this);
		this.add(fermer);
		
		nmax = new JTextField("77");
		nmax.setBounds((int)(width/10.0), (int)(height*6.0/10.0), (int)(width/4.0) ,(int)(height/10.0));
		this.add(nmax);
		
		rayonConvergence = new JTextField("4.2");
		rayonConvergence.setBounds((int)(width*6.0/10.0), (int)(height*6.0/10.0), (int)(width/4.0) ,(int)(height/10.0));
		this.add(rayonConvergence);
	}
	
	public void actionPerformed(ActionEvent e){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double scWidth = screenSize.getWidth();
		double scHeight = screenSize.getHeight();
		
		if(e.getSource() == afficher){
			if(pc!=null){pc.dispose();}
			Mandelbrot m = new Mandelbrot((int)(1.3*scHeight), (int)(scHeight), (int)Double.parseDouble(nmax.getText()), Double.parseDouble(rayonConvergence.getText()));
			pc = new PlanComplexe(m, (int)(1.3*scHeight), (int)scHeight, (int)(scWidth-1.3*scHeight));
		} else if(e.getSource() == fermer){
			if(pc!=null){pc.dispose();}
			Menu.console.dispose();
			String[] s = {""}; Principale.main(s);
			dispose();
		}
	}
}
