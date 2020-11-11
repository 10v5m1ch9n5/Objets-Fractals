import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Mandelbrot extends JPanel {
	private BufferedImage boeuf;
	private int w;
	private int h;
	
	private double xStart = -2;
	private double xEnd = 1;
	private double yStart = -1;
	private double yEnd = 1;
	
	private int nmax;
	private double rayonConvergence;
	
	private boolean dessine;
	
	public Mandelbrot(int width, int height, int nmax, double rayonConvergence){
		w = width;
		h = height;
		this.nmax = nmax;
		this.rayonConvergence = rayonConvergence;
		
		boeuf = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		dessine=false;
	}
	
	private Color iterer(Complexe z, int nmax){
		SuiteZ zn = new SuiteZ(Complexe.zero(), z);
		int i = 1;
		while(zn.iteration(i).module() <= rayonConvergence && i < nmax) {
			i++;
		}
		if(i == nmax) {
			return Color.black;
		} else {
			return Colorisation.degreTeinteToRGB((int)(360.0*(double)i/(double)(nmax)));
		}
	}
	
	private void dessinMandelbrot(Graphics g) {
		double tOp = System.nanoTime();
		for(int i = 0; i<w; i++){
			for(int j = 0; j<h; j++){
				g.setColor(iterer(new Complexe((i/(double)w)*(xEnd-xStart) + xStart, ((h-j)/(double)h)*(yEnd-yStart) + yStart + ((double)-40/(double)h)*(yEnd-yStart)), nmax));
				g.fillRect(i,j,1,1);
			}
			System.out.println("Colonne " + i + " calculée");
		}
		Console.afficher("----------Ensemble de Mandelbrot----------\n");
		Console.afficher("Nombre d'iterations maximal : " + nmax);
		Console.afficher("\nRayon de convergence impose : " + rayonConvergence + "\n");
		Console.afficher("Temps de l'operation : " + ((System.nanoTime()-tOp)/1000000000.0) + " s\n");
		dessine=true;
	}
	
	public void paint(Graphics g){
		if(!dessine)dessinMandelbrot(boeuf.getGraphics());
		g.drawImage(boeuf, getInsets().left, getInsets().top, null);
	}
}
