package Lock.Feast;

import ProducerConsumer.WildBoarFeast.Table;

public class Gauls extends Thread {
    private TableLock table;

    public Gauls(TableLock table){
        this.table = table;
    }
    @Override
    public void run() {
        for(int i=0; i!=5;i++)
            try {
                System.out.println("Consumi: "+ table.take());
            } catch (InterruptedException e) {}
    }
}
