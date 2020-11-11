import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuJulia extends JFrame implements ActionListener {
	private JButton afficher;
	private JButton fermer;
	private JTextField nmax;
	private JTextField rayonConvergence;
	private JTextField reC;
	private JTextField imC;
	private JTextField reStart, reEnd, imStart, imEnd;
	private JTextField r;
	private JLabel valeurC;
	private JLabel rCV;
	private JLabel iterMax;
	private JLabel intervalleX, intervalleY;
	private JLabel xMin, xMax, yMin, yMax;
	private JLabel a, b;
	private JLabel zoom, rapport;
	private PlanComplexe pc;
	
	public MenuJulia(int width, int height){
		super("Tracer un ensemble de Julia");
		setVisible(true);
		setResizable(false);
		setLayout(null);
		setBounds(0,0,width,height);
		
		zoom = new JLabel("Homothetie", SwingConstants.CENTER);
		zoom.setBounds((int)(0.0), (int)(height*1.0/100.0), (int)(width/1.0), (int)(height/12.0));
		this.add(zoom);
		
		rapport = new JLabel("rapport =");
		rapport.setBounds((int)(width*5.0/10.0), (int)(height*1.0/10.0), (int)(width/6.0) ,(int)(height/12.0));
		this.add(rapport);
		
		r = new JTextField("1");
		r.setBounds((int)(width*6.0/10.0), (int)(height*1.0/10.0), (int)(width/4.0) ,(int)(height/12.0));
		this.add(r);
		
		intervalleX = new JLabel("Intervalle pour l'axe des reels", SwingConstants.CENTER);
		intervalleX.setBounds((int)(0.0), (int)(height*1.8/10.0), (int)(width/1.0), (int)(height/12.0));
		this.add(intervalleX);
		
		xMin = new JLabel("xMin =");
		xMin.setBounds((int)(width/75.0), (int)(height*2.5/10.0), (int)(width/6.0) ,(int)(height/12.0));
		this.add(xMin);
		
		xMax = new JLabel("xMax =");
		xMax.setBounds((int)(width*5.0/10.0), (int)(height*2.5/10.0), (int)(width/6.0) ,(int)(height/12.0));
		this.add(xMax);
		
		reStart = new JTextField("-1");
		reStart.setBounds((int)(width*1.0/10.0), (int)(height*2.5/10.0), (int)(width/4.0) ,(int)(height/12.0));
		this.add(reStart);
		
		reEnd = new JTextField("1");
		reEnd.setBounds((int)(width*6.0/10.0), (int)(height*2.5/10.0), (int)(width/4.0) ,(int)(height/12.0));
		this.add(reEnd);
		
		intervalleY = new JLabel("Intervalle pour l'axe des imaginaires", SwingConstants.CENTER);
		intervalleY.setBounds((int)(0.0), (int)(height*3.3/10.0), (int)(width/1.0), (int)(height/12.0));
		this.add(intervalleY);
		
		yMin = new JLabel("yMin =");
		yMin.setBounds((int)(width/75.0), (int)(height*4.0/10.0), (int)(width/6.0) ,(int)(height/12.0));
		this.add(yMin);
		
		yMax = new JLabel("yMax =");
		yMax.setBounds((int)(width*5.0/10.0), (int)(height*4.0/10.0), (int)(width/6.0) ,(int)(height/12.0));
		this.add(yMax);
		
		imStart = new JTextField("-1");
		imStart.setBounds((int)(width*1.0/10.0), (int)(height*4.0/10.0), (int)(width/4.0) ,(int)(height/12.0));
		this.add(imStart);
		
		imEnd = new JTextField("1");
		imEnd.setBounds((int)(width*6.0/10.0), (int)(height*4.0/10.0), (int)(width/4.0) ,(int)(height/12.0));
		this.add(imEnd);
		
		valeurC = new JLabel("Valeur complexe de c = a + ib", SwingConstants.CENTER);
		valeurC.setBounds((int)(0), (int)(height*4.8/10.0), (int)(width/1.0), (int)(height/12.0));
		this.add(valeurC);
		
		a = new JLabel("a =");
		a.setBounds((int)(width/20.0), (int)(height*5.5/10.0), (int)(width/6.0) ,(int)(height/12.0));
		this.add(a);
		
		b = new JLabel("b =");
		b.setBounds((int)(width*5.6/10.0), (int)(height*5.5/10.0), (int)(width/6.0) ,(int)(height/12.0));
		this.add(b);
		
		reC = new JTextField("0");
		reC.setBounds((int)(width/10.0), (int)(height*5.5/10.0), (int)(width/4.0) ,(int)(height/12.0));
		this.add(reC);
		
		imC = new JTextField("1");
		imC.setBounds((int)(width*6.0/10.0), (int)(height*5.5/10.0), (int)(width/4.0) ,(int)(height/12.0));
		this.add(imC);
		
		iterMax = new JLabel("Nombre d'iterations maximal");
		iterMax.setBounds((int)(width*0.7/10.0), (int)(height*6.2/10.0), (int)(width/2.0) ,(int)(height/12.0));
		this.add(iterMax);
		
		rCV = new JLabel("Rayon de convergence");
		rCV.setBounds((int)(width*6.0/10.0), (int)(height*6.2/10.0), (int)(width/3.0) ,(int)(height/12.0));
		this.add(rCV);
		
		nmax = new JTextField("69");
		nmax.setBounds((int)(width/10.0), (int)(height*7.0/10.0), (int)(width/4.0) ,(int)(height/12.0));
		this.add(nmax);
		
		rayonConvergence = new JTextField("4.2");
		rayonConvergence.setBounds((int)(width*6.0/10.0), (int)(height*7.0/10.0), (int)(width/4.0) ,(int)(height/12.0));
		this.add(rayonConvergence);
		
		afficher = new JButton("Afficher");
		afficher.setBounds((int)(width/10.0), (int)(height*8.0/10.0), (int)(width/4.0), (int)(height/10.0));
		afficher.addActionListener(this);
		this.add(afficher);
		
		fermer = new JButton("Fermer");
		fermer.setBounds((int)(width*6.0/10.0), (int)(height*8.0/10.0), (int)(width/4.0) ,(int)(height/12.0));
		fermer.addActionListener(this);
		this.add(fermer);
	}
	
	public void actionPerformed(ActionEvent e){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double scWidth = screenSize.getWidth();
		double scHeight = screenSize.getHeight();
		
		if(e.getSource() == afficher){
			if(pc!=null){pc.dispose();}
			
			Julia j = new Julia((int)(1.3*scHeight), (int)(scHeight), Integer.parseInt(nmax.getText()), 
			Double.parseDouble(rayonConvergence.getText()), 
			new Complexe(Double.parseDouble(reC.getText())*Double.parseDouble(r.getText()), 
			Double.parseDouble(imC.getText())), 
			Double.parseDouble(reStart.getText())*Double.parseDouble(r.getText()), 
			Double.parseDouble(reEnd.getText())*Double.parseDouble(r.getText()), 
			Double.parseDouble(imStart.getText())*Double.parseDouble(r.getText()), 
			Double.parseDouble(imEnd.getText())*Double.parseDouble(r.getText()));
			
			pc = new PlanComplexe(j, (int)(1.3*scHeight), (int)scHeight, (int)(scWidth-1.3*scHeight));
		} else if(e.getSource() == fermer){
			if(pc!=null){pc.dispose();}
			Menu.console.dispose();
			String[] s = {""}; Principale.main(s);
			dispose();
		}
	}
}
