package ProducerConsumer.WildBoarFeast;

public class WildBoar {
    private final int cookID;
    private final int ID;

    public WildBoar(int idCozinheiro, int ID){
        this.cookID = idCozinheiro;
        this.ID = ID;
    }

    @Override
    public String toString(){
        return "O javali " + ID + " foi cozinhado pelo cozinheiro - " + cookID;
    }
}
