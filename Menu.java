import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;

public class Menu extends JFrame implements ActionListener, MouseListener {
	public static Console console;
	private BufferedImage buffer = new BufferedImage(600, 650, BufferedImage.TYPE_INT_RGB);
	private JLabel titre;
	private double scWidth;
	private double scHeight;
	private Timer time = new Timer(75, this);
	private Color violet = new Color(174, 0, 255);
	private Color bleuNeon = new Color(70,102,255);
	private int offset = 0;
	
	private final JFXPanel fxPanel = new JFXPanel();
	private String title = "musique/title.mp3";
	private Media hit = new Media(new File(title).toURI().toString());
	private MediaPlayer mediaPlayer = new MediaPlayer(hit);
	
	public Menu(){
		super("--- menu ---");
		this.addMouseListener(this);
		//Centrage de la fenêtre
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.scWidth = screenSize.getWidth();
		this.scHeight = screenSize.getHeight();
		setVisible(true);
		setLayout(null);
		setSize(600,650);
		setLocation((int)(scWidth/2.0 - getWidth()/2.0), (int)(scHeight/2.0 - getHeight()/2.0));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		time.start();
		//Musique
		mediaPlayer.play();
	}
	
	public void paint(Graphics gr) {
		Graphics g = buffer.getGraphics();
		// ECRITURE DU TITRE
		//fond noir
		g.setColor(Color.black);
		g.fillRect(getInsets().left, getInsets().top, getWidth(), getHeight());
		
		//ecriture du titre en bleu neon avec un pixel de decalage dans chaque sens possible
		g.setColor(bleuNeon);
		for(int i = -1; i<2; i++) {
			for(int j = -1; j<2; j++){
				g.setFont(new Font("Impact", Font.BOLD, 60));
				drawCenteredString(g, "Fractal visualization", j, getInsets().top+75+i, new Font("Impact", Font.BOLD, 60), getWidth());
				g.setFont(new Font("Impact", Font.ITALIC, 60));
				drawCenteredString(g, "for aware people",j, getInsets().top+150+i, new Font("Impact", Font.ITALIC, 60), getWidth());
			}
		}
		
		//ecriture du titre en noir au centre pour qu'il ne reste que les contours en bleu neon
		g.setColor(Color.black);
		g.setFont(new Font("Impact", Font.BOLD, 60));
		drawCenteredString(g, "Fractal visualization", 0, getInsets().top+75, new Font("Impact", Font.BOLD, 60), getWidth());
		g.setFont(new Font("Impact", Font.ITALIC, 60));
		drawCenteredString(g, "for aware people",0, getInsets().top+150, new Font("Impact", Font.ITALIC, 60), getWidth());
		
		//ANIMATION RETRO : Gestion des lignes violettes en perspective
		g.setColor(violet);
		g.drawLine(0, getHeight()/2, getWidth(), getHeight()/2); //horizon
		int h = 0;
		for(int i = 0; i < 14; i++){ // lignes obliques et horizontales
			g.drawLine(getWidth()/2 + 20*i + 10, getHeight()/2, getWidth()/2 + 120*i + 60, getHeight());
			g.drawLine(getWidth()/2 - 20*i - 10, getHeight()/2, getWidth()/2 - 120*i - 60, getHeight());
			g.drawLine(0, getHeight()/2 + h , getWidth(), getHeight()/2 + h);
			h += ((int)((100*i+50)/10.0)+offset);
		}
		
		//AFFICHAGE DES BOUTONS
		g.setColor(Color.red);
		Font fJM = new Font("", Font.PLAIN, 17);
		g.setFont(fJM);
		//Mandelbrot
		if(hovering("Mandelbrot")){
			g.setColor(Color.yellow);
		}
		g.drawRect(50, 240, 200, 32);
		drawCenteredString(g, "Mandelbrot", 50, 262, fJM, 200);
		//Julia
		if(hovering("Julia")){
			g.setColor(Color.yellow);
		} else {
			g.setColor(Color.red);
		}
		g.drawRect(350, 240, 200, 32);
		drawCenteredString(g, "Julia", 350, 262, fJM, 200);
		
		gr.drawImage(buffer,0,0, null);
	}
	
	private void drawCenteredString(Graphics g, String text, int x, int y, Font font, int width) {
		FontMetrics metrics = g.getFontMetrics(font);
		int abs = x + (width - metrics.stringWidth(text)) / 2;
		g.drawString(text, abs, y);
	}
	
	public void actionPerformed(ActionEvent e){
		offset++;
		offset%=11;
		repaint();
	}
	
	private boolean hovering(String s) {
		Point position = MouseInfo.getPointerInfo().getLocation();
		Point absPos= new Point(position.x - getBounds().x, position.y - getBounds().y);
		if(s == "Mandelbrot"){
			return (absPos.x >= 50 && absPos.x <= 250) && (absPos.y >= 240 && absPos.y <= 272);
		} else if (s == "Julia") {
			return (absPos.x >= 350 && absPos.x <= 550) && (absPos.y >= 240 && absPos.y <= 272);
		} else {
			return false;
		}
	}
	
	public void mouseClicked(MouseEvent e){
		//CREATION MANUELLE DES BOUTONS
		if(hovering("Mandelbrot")){
			MenuMandelbrot menuM = new MenuMandelbrot((int)(scWidth-1.3*scHeight), (int)(scHeight/2));
			console = new Console((int)(scHeight/2), (int)(scWidth-1.3*scHeight), (int)(scHeight/2));
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			dispose();
			mediaPlayer.stop();
		} else if(hovering("Julia")){
			MenuJulia menuJ = new MenuJulia((int)(scWidth-1.3*scHeight), (int)(scHeight/2));
			console = new Console((int)(scHeight/2), (int)(scWidth-1.3*scHeight), (int)(scHeight/2));
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			dispose();
			mediaPlayer.stop();
		}
	}
	
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
}
