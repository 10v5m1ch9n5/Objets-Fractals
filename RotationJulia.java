import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class RotationJulia extends JPanel implements ActionListener{
	int deltaT = 20;
	double r = 0.7885;
	int nbFrames;
	Complexe c = Complexe.zero();
	BufferedImage[] frames;
	
	int w;
	int h;
	
	double xStart = -1;
	double xEnd = 1;
	double yStart = -1;
	double yEnd = 1;
	
	int nmax = 70;
	double rayonConvergence = 4.2;
	
	Timer monChrono = new Timer(deltaT, this);
	int t = 0;
	
	public RotationJulia(double rayon, int nbFrames, int deltaT, int w, int h) {
		super();
		setBounds(0,0,w,h);
		r = rayon;
		this.deltaT = deltaT;
		this.nbFrames = nbFrames;
		this.w = w;
		this.h = h;
		frames = new BufferedImage[nbFrames];
		
		int ti = (int)(System.nanoTime()/1000000000.0);
		for(int k = 0; k < nbFrames; k++){
			frames[k] = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			Graphics g = frames[k].getGraphics();
			c = new Complexe(r*Math.cos(2*k*Math.PI/nbFrames), r*Math.sin(2*k*Math.PI/nbFrames));
			for(int i = 0; i < w; i++){
				for(int j = 0; j < h; j++){
					g.setColor(iterer(new Complexe(xStart + ((double)i/(double)w)*(xEnd-xStart), yStart + ((double)(h-j)/(double)h)*(yEnd-yStart))));
					g.fillRect(i,j,1,1);
				}
			}
			System.out.println("Frame " + (k+1) + " sur " + nbFrames + " dessinee");
		}
		int temps = (int)(System.nanoTime()/1000000000.0) - ti;
		System.out.println("Dessin realise en " + (temps / 60) +"min" + (temps%60) +"s");
		System.out.println("Dimensions : " +w+"x"+h);
		System.out.println("Echantillonnage : " + (int)(1000/deltaT) + " FPS");
		monChrono.start();
	}
	
	public Color iterer(Complexe z) {
		SuiteZ zn = new SuiteZ(z, c);
		int i = 1;
		
		while(zn.iteration(i).module() < rayonConvergence && i < nmax) {
			i++;
		}
		if(i == nmax) {
			return Color.black;
		} else {
			return Colorisation.degreTeinteToRGB((int)(((double)i/(double)nmax)*360));
		}
	}
	
	public void paint(Graphics g){
		g.drawImage(frames[t], getInsets().left, getInsets().top, null);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==monChrono){
			t++;
			t%=nbFrames;
			repaint();
		}
	}
	
	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		RotationJulia rj = new RotationJulia(0.7885, 600, 25,1280/*(int)screenSize.getWidth()*/, 720/*(int)screenSize.getHeight()*/);
		PlanComplexe pc = new PlanComplexe(rj,1280 /*(int)screenSize.getWidth()*/,720 /*(int)screenSize.getHeight()*/, 0);
	}
}
