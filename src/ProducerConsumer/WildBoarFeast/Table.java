package ProducerConsumer.WildBoarFeast;

import java.util.LinkedList;
import java.util.Queue;

public class Table{
    private final int capacity;
    private final Queue<WildBoar> wildBoars = new LinkedList<>();

    public Table(int capacity){
        this.capacity = capacity;
    }

    public synchronized void put(WildBoar wildBoar, String cookName) throws InterruptedException{
            while(wildBoars.size() == capacity){
                System.out.println("[FULL TABLE] - " + cookName + " is waiting for room ...");
                wait();
            }
            wildBoars.add(wildBoar);
            System.out.println(cookName + " put: [" + wildBoar + "]. Table has " + wildBoars.size() + " wild boars on the table!" );
            notifyAll();
    }

    public synchronized WildBoar take(String gaulsName) throws InterruptedException{
        while(wildBoars.isEmpty()){
            System.out.println("[EMPTY TABLE] - " + gaulsName + " is waiting for wildd boars ...");
            wait();
        }
        WildBoar wildBoar = wildBoars.poll();
        System.out.println(gaulsName + " took [" + wildBoar +"] from the table. Table has " + wildBoars.size() + " wild boars on the table!");
        notifyAll();
        return wildBoar;
    }

    public int getCapacity() {
        return capacity;
    }
}