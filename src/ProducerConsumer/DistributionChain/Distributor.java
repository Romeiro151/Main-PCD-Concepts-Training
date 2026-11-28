package ProducerConsumer.DistributionChain;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class Distributor {
    private static final int MIN_TO_SELL = 10;
    private List<Integer> stock = new LinkedList<>();
    private final JTextField listField;

    public Distributor(JTextField listField){
        this.listField = listField;
    }

    public synchronized void supply(Integer product) throws InterruptedException{
        stock.add(product);
        updateGUI();

        if(stock.size() >= MIN_TO_SELL) notifyAll();
    }

    public synchronized void buy() throws InterruptedException{
        while(stock.size() < MIN_TO_SELL){
            System.out.println(Thread.currentThread().toString() + "- Before wait()");
            wait();
            System.out.println(Thread.currentThread().toString() + "- After wait()");
        }

        stock.clear();
        updateGUI();
    }

    public void updateGUI(){
        String content = stock.toString();
        SwingUtilities.invokeLater(() -> listField.setText(content));
    }
}
