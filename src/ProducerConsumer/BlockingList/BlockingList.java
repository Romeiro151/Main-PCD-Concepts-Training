package ProducerConsumer.BlockingList;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingList<T> {
    private Queue<T> queue = new LinkedList<>();
    private final int capacity;

    public BlockingList(){
        this.capacity = -1;
    }

    public BlockingList(int capacity){
        this.capacity = capacity;
    }

    public synchronized void put(T item) throws InterruptedException{
        while(size() == capacity && capacity > 0){
            wait();
        }
        queue.add(item);
        notifyAll();
    }

    public synchronized T take() throws InterruptedException{
        while(queue.isEmpty()){
            wait();
        }

        T item = queue.remove();
        if(capacity > 0) notifyAll();
        return item;
    }

    public int size(){
        return queue.size();
    }

    public void clear(){
        queue.clear();

        if(capacity > 0) notifyAll();
    }
}
