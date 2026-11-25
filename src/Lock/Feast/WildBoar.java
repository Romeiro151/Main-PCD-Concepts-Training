package Lock.Feast;

public class WildBoar {
	public final int boarID;
	public final int cookID;

	public WildBoar(int numOrdem, int idCozinheiro) {
		super();
		this.boarID = numOrdem;
		this.cookID = idCozinheiro;
	}

	@Override
	public String toString() {
		return "Boar [ID = " + boarID + ", CookID = " + cookID + "]";
	}
	
}
