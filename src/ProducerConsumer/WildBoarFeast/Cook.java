package ProducerConsumer.WildBoarFeast;

public class Cook extends Thread{
    private final String name;
    private final int cookId;
    private final Table table;
    private final int maxBoars;

    public Cook(Table table, int cookId, String name, int maxBoars){
        this.table = table;
        this.cookId = cookId;
        this.name = name;
        this.maxBoars = maxBoars;
    }

    @Override
    public void run(){
        try{
            int boarID = 1;
            while(!isInterrupted() && (maxBoars == -1 || boarID <= maxBoars)){
                Thread.sleep(500);
                WildBoar newWildBoar = new WildBoar(cookId, boarID);
                table.put(newWildBoar, "Cook " + name + " (" + cookId + ")");
                boarID++;
            }
        }catch(InterruptedException e){
            System.err.println("Cook " + name + " - error while trying to put a wild boar in the table: " + e.getMessage());
            interrupt();
        }
    }
}
