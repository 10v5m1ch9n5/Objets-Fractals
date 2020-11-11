import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class ZoomMandelbrot extends JPanel implements ActionListener{
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int w, h, facteur, nmax;
	double rayonConvergence;
	int[][] matrix;
	
	double xStart = -2.0;
	double xEnd = 1.0;
	double yStart = -1.0;
	double yEnd = 1.0;
	
	double pasX, pasY;
	BufferedImage buffer;
	BufferedImage[] animation;
	
	int realW, realH;
	
	int x0;
	int y0;
	Complexe centre;
	
	Timer monChrono = new Timer(50, this);
	int index = 0;
	double pasVideo=0.05;
	
	public ZoomMandelbrot(int nmax, double rayonConvergence, int facteur, Complexe centre, boolean previsualisation) {
		super();
		double t = System.nanoTime()/1000000000.0;
		this.rayonConvergence = rayonConvergence;
		this.centre = centre;
		this.facteur = facteur;
		this.nmax = nmax;
		this.w = (int)(/*screenSize.getWidth()*/1280*Math.exp((facteur*pasVideo)*Math.log(2.0)));
		this.h =  (int)(/*screenSize.getHeight()*/720*Math.exp((facteur*pasVideo)*Math.log(2.0)));
		this.realW = 1280/*(int)screenSize.getWidth()*/;
		this.realH = 720/*(int)screenSize.getHeight()*/;
		matrix = new int[realW][realH];
		buffer = new BufferedImage(realW, realH, BufferedImage.TYPE_INT_RGB);
		animation = new BufferedImage[facteur];
		for(int i=0; i<facteur; i++){animation[i]=new BufferedImage(realW, realH, BufferedImage.TYPE_INT_RGB);}
		pasX = Math.abs(xEnd-xStart)/w;
		pasY = Math.abs(yEnd-yStart)/h;
		
		if(previsualisation) {
			creerMatrice();
			drawZoom(buffer);
		} else {
			animer();
			monChrono.start();
		}

		System.out.println(( (int)(System.nanoTime()/1000000000.0 - t)/3600) + " h " + ((int)(System.nanoTime()/1000000000.0 - t)/60) + " min " + ((int)(System.nanoTime()/1000000000.0 - t)%60) + " s");
	}
	
	public void animer() {
		int nbFrames = facteur;
		int i=0;
		double tOp = System.nanoTime()/1000000000.0;
		while(i<nbFrames){
			//~ facteur = (int)Math.exp((i*pas)*Math.log(2.0));
			w = (int)(screenSize.getWidth()*Math.exp((i*pasVideo)*Math.log(2.0)));
			h = (int)(screenSize.getHeight()*Math.exp((i*pasVideo)*Math.log(2.0)));
			pasX = Math.abs(xEnd-xStart)/w;
			pasY = Math.abs(yEnd-yStart)/h;
			creerMatrice();
			drawZoom(animation[i]);
			System.out.println("Frame " + (i+1) + " | temps : " + (int)(System.nanoTime()/1000000000.0-tOp) + " s");
			tOp = System.nanoTime()/1000000000.0;
			i++;
		}
		facteur=nbFrames;
	}
	
	public void actionPerformed(ActionEvent e){
		index++;
		index%=facteur;
		repaint();
	}
	
	public void paint(Graphics g) {
		if(monChrono.isRunning()) {
			g.drawImage(animation[index], getInsets().left, getInsets().top, null);
		} else {
			g.drawImage(buffer, getInsets().left, getInsets().top, null);
		}
	}
	
	public void creerMatrice() {
		for(int i = 0; i < realW; i++) {
			for(int j = 0; j < realH; j++) {
				//~ SuiteZ zn = new SuiteZ(Complexe.zero(), new Complexe(xStart + i*pasX, yStart + (matrix[i-x0].length-j)*pasY));
				SuiteZ zn = new SuiteZ(Complexe.zero(), new Complexe(centre.re-((realW/2.0) - i)*pasX, centre.im-((realH/2.0) - j)*pasY));
				int k = 1;
				while(k < nmax && zn.iteration(k).module() < rayonConvergence){
					k++;
				}
				matrix[i][j] = k;
			}
		}
	}
	
	public void draw(){
		int[][] newMat = new int[realW][realH];
		Graphics g = buffer.getGraphics();
		for(int i=0; i < realW; i++){
			for(int j=0; j<realH; j++){
				newMat[i][j]=0;
				for(int p=0; p < facteur; p++){
					for(int q=0; q < facteur; q++){
						newMat[i][j] += matrix[i*facteur+p][j*facteur+q];
					}
				}
				newMat[i][j] = (int)(newMat[i][j]/(double)(facteur*facteur)); //problème possible
				g.setColor(Colorisation.degreTeinteToRGB((int)(((double)newMat[i][j]/(double)nmax)*360)));
				g.drawRect(i, j, 1, 1);
			}
		}
	}
	
	public void drawZoom(BufferedImage buffer) {
		//~ int[][] newMat = new int[realW][realH];
		Graphics g = buffer.getGraphics();
		for(int i = 0; i < realW; i++){
			for(int j = 0; j < realH; j++){
				g.setColor(Colorisation.degreTeinteToRGBln((int)(((double)matrix[i][j]/(double)nmax)*360)));
				g.drawRect(i, realH-j, 1, 1);
			}
		}
	}
	
	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		ZoomMandelbrot zm = new ZoomMandelbrot(150, 4.0, 100, new Complexe(-1.039, 0.349096), false);
		PlanComplexe pc = new PlanComplexe(zm, 1280/*(int)screenSize.getWidth()*/, 720/*(int)screenSize.getHeight()*/, 0);
	}
}
