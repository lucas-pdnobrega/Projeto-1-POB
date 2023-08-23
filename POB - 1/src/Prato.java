import java.util.ArrayList;

public class Prato {
	
	private int id;
	private Carne carne;
	private ArrayList<Acompanhamento> acompanhamentos;
	
	public Prato(Carne carne) {
		this.carne = carne;
		this.acompanhamentos = new ArrayList<>();
	}
	
	
	
}
