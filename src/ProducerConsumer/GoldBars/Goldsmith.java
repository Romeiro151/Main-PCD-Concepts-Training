package ProducerConsumer.GoldBars;

public class Goldsmith extends Thread{
    private int barsNumber = 0;
    private final Scale scale;

    public Goldsmith(Scale scale){
        super("Goldsmith");
        this.scale = scale;
    }

    @Override
    public void run(){
        System.out.println(Thread.currentThread() + " - starting the run method...");
        try{
            while(!isInterrupted()){
                scale.takeGold();

                System.out.println(Thread.currentThread() + " - before making the gold bar");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread() + " - after making the gold bar...");

                barsNumber ++;
            }
        }catch (InterruptedException e){
            System.err.println("Error - " + e.getMessage());
        }finally {
            System.out.println(Thread.currentThread() + " - Total of gold bars produced: " + barsNumber);
            System.out.println(Thread.currentThread() + " - End of the run method.");
        }
    }

}
