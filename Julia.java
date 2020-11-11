import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Julia extends JPanel {
	private Complexe c;
	
	private int w;
	private int h;
	private int nmax;
	private double rayonConvergence;
	
	private double xStart;
	private double xEnd;
	private double yStart;
	private double yEnd;
	
	private BufferedImage boeuf; //jeu de mot qui me faisait rire boeuf=buff
	private boolean dessine;
	
	public Julia(int width, int height, int nmax, double rayonConvergence, Complexe c, double xStart, double xEnd, double yStart, double yEnd){
		this.c = c;
		w = width;
		h = height;
		this.nmax = nmax; // Nombre d'itérations maximal : si on calcule le nmaxième  terme de la suite et que ce terme est < à rayonConvergence, on condidère que ça converge
		this.rayonConvergence = rayonConvergence; // seuil en dessous duquel on considère que la suite (zn) est convergente
		boeuf = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		this.xStart = xStart;
		this.xEnd = xEnd;
		this.yStart = yStart;
		this.yEnd = yEnd;
		dessine=false;
	}
	
	private Color iterer(Complexe z) { // fonction qui calcule znmax et qui voit si la fonction a divergé
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
	
	private void dessinJulia(Graphics g) {
		Console.afficher("----------Ensemble de Julia----------\n");
		Console.afficher("c = " + c.re + " + " + c.im + "i\n");
		Console.afficher("Nombre d'iterations maximal : " + nmax);
		Console.afficher("\nRayon de convergence impose : " + rayonConvergence);
		Console.afficher("\naxe reel : [" + xStart +";" + xEnd + "]\n");
		Console.afficher("axe imaginaire pur : [" + yStart + ";" + yEnd + "]\n");
		
		double tOp = System.nanoTime();
		for(int i = 0; i < w; i++){
			for(int j = 0; j < h; j++){
				g.setColor(iterer(new Complexe(xStart + ((double)i/(double)w)*(xEnd-xStart), yStart + ((double)(h-j)/(double)h)*(yEnd-yStart))));
				g.fillRect(i,j,1,1);
			}
		}
		Console.afficher("Dessiné en " + (System.nanoTime()-tOp)/1000000000.0 + " s\n");
		dessine=true;
	}
	
	public void paint(Graphics g) {
		if(!dessine)dessinJulia(boeuf.getGraphics());
		g.drawImage(boeuf, getInsets().left, getInsets().top, null);
	}
}
