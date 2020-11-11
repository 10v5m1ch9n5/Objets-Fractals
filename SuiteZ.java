public class SuiteZ {
	private Complexe c;
	private Complexe z0;
	
	public SuiteZ(Complexe z0,Complexe c) {
		this.c = c;
		this.z0 = z0;
	}
	
	public Complexe iteration(int n){
		Complexe z = z0;
		for (int i = 0; i < n; i++){
			z = Complexe.somme(Complexe.sqr(z), c);
		}
		return z;
	}
	
}
