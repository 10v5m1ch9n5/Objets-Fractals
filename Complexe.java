public class Complexe { // Nombre complexe rien de compliqué
	public double re;
	public double im;
	
	public Complexe(double re, double im){
		this.re = re;
		this.im = im;
	}
	
	public static Complexe somme(Complexe z1, Complexe z2){
		Complexe c = new Complexe(z1.re+z2.re, z1.im+z2.im);
		return c;
	}
	
	public static Complexe sqr(Complexe z){ // sqr = square = mettre au carré
		Complexe c = new Complexe(Math.pow(z.re, 2) - Math.pow(z.im, 2), 2*z.re*z.im);
		return c;
	}
	
	public String toString(){
		String s = "x : " + re + " y : " + im;
		return s;
	}
	
	public static Complexe zero(){
		return new Complexe(0,0);
	}
	
	public double module(){
		return Math.sqrt(re*re + im*im);
	}
}
