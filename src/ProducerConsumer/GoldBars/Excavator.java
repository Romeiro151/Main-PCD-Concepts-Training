package ProducerConsumer.GoldBars;
import java.util.Random;

public class Excavator extends Thread{
    private Random random = new Random();
    private Scale scale;

    public Excavator(Scale scale){
        super("Excavator");
        this.scale = scale;
    }

    @Override
    public void run(){
        System.out.println(Thread.currentThread() + " - starting the run method ...");
        try{
            while(!isInterrupted()){
                System.out.print(Thread.currentThread() + " - before excavating the gold");
                Thread.sleep(250);
                System.out.print(Thread.currentThread() + " - after excavating the gold");

                double goldExcavated = 0.01 + (0.99 * random.nextDouble());
                scale.addGold(goldExcavated);
            }
        }catch (InterruptedException e){
            System.err.print("Error: " + e.getMessage());
        }finally{
            System.out.print(Thread.currentThread() + " - end of run.");
        }
    }
}
