package ProducerConsumer.GoldBars;

import javax.swing.*;
import java.util.List;

public class Scale {
    private static final double GOLD_BAR_KG = 12.5;
    private double actual_gold = 0.0;
    private JTextField field;

    public Scale(JTextField field){
        this.field = field;
        updateField();
    }

    public synchronized void addGold(double goldWeight) throws InterruptedException{
        while(actual_gold >= GOLD_BAR_KG){
            System.out.println(Thread.currentThread().toString() + " - before waiting...");
            wait();
            System.out.println(Thread.currentThread().toString() + " - after waiting...");
        }

        actual_gold += goldWeight;
        updateField();
        notifyAll();
    }

    public synchronized void takeGold() throws InterruptedException{
        while(actual_gold < GOLD_BAR_KG){
            System.out.println(Thread.currentThread().toString() + " - before waiting.");
            wait();
            System.out.println(Thread.currentThread().toString() + " - after waiting...");
        }

        actual_gold -= GOLD_BAR_KG;
        updateField();
        notifyAll();
    }

    private void updateField(){
        String text = String.format("%.3f kg", actual_gold);
        SwingUtilities.invokeLater(() -> field.setText(text));
    }
}
