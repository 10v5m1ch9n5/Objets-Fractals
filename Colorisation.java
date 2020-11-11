//Cette classe regroupe différentes méthodes de colorisation utilisables pour dessiner les ensembles de Julia et de Mandelbrot

import javax.swing.*;
import java.awt.*;

public class Colorisation {
	
	//Cette méthode convertit un degré de teinte x (valeur comprise entre 0 et 360) en une couleur de code RGB équivalent au code HSL (x°,100%,50%)
	public static  Color degreTeinteToRGB(int x) {
		if(0 <= x && x < 60) {
			return new Color(255, (int)(255.0*(x/60.0)), 0);
		} else if(60 <= x && x < 120) {
			return new Color((int)(255*(1-((x%60)/60.0))), 255, 0);
		} else if(120 <= x && x < 180) {
			return new Color(0, 255, (int)((255.0/60.0)*(x%60)));
		} else if(180 <= x && x < 240) {
			return new Color(0, (int)(255*(1-(x%60.0)/60.0)), 255);
		} else if(240 <= x && x < 300) {
			return new Color((int)((255.0/60.0)*(x%60)), 0, 255);
		} else if(300 <= x && x < 360){
			return new Color(255, 0, (int)(255*(1-(x%60)/60.0)));
		}
		return Color.black;
	}
	
	//Même utilité que la méthode précédente mais l'échelle de teinte est parcourue dans le sens inverse
	public static Color degreTeinteInverseToRGB(int x) {
		return degreTeinteToRGB(360-x);
	}
	
	//Couleurs parcourues avec une croissance logarithmique
	public static Color degreTeinteToRGBln(int x) {
		double y = Math.log(x);
		double ymax = Math.log(360);
		int y360 = (int)((y/ymax)*360);
		return degreTeinteToRGB(y360);
	}
	
	//Couleurs parcourues en sinus
	public static Color degreTeinteToRGBsin(int x) {
		return degreTeinteToRGB((int)(((Math.sin(x)+1)/2)*360));
	}
}
