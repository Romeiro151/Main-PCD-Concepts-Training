package Lock.Feast;

public class Cook extends Thread {
    public final int id;
    private final TableLock table;
    public Cook(int id, TableLock table) {
        this.id = id;
        this.table = table;
    }
    public void run() {
        for(int i=0; i!=10;i++)
            try {
                table.put(new WildBoar(i, id));
                System.out.println("Coloquei:"+id+":"+i);
            } catch (InterruptedException e) {		}
    }
}