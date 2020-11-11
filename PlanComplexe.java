import javax.swing.*;
import java.awt.*;

public class PlanComplexe extends JFrame{
	public PlanComplexe(JPanel ensemble, int width, int height, int xOffset){
		super("Plan Complexe");
		setVisible(true);
		setBounds(xOffset,0,width,height);
		setResizable(false);
		add(ensemble);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
