package ProducerConsumer.DistributionChain;

import java.util.Random;

public class Supplier extends Thread{
    private final Distributor distributor;
    private final Random random = new Random();

    public Supplier(Distributor distributor) {
        this.distributor = distributor;
    }

    @Override
    public void run(){
        System.out.println(Thread.currentThread().toString() + " - Before running the Thread");
        try{
            while(!isInterrupted()){
                System.out.println(Thread.currentThread().toString() + " - Before putting the Thread to sleep");
                Thread.sleep(1000);                 // Simulating the time to store the products
                System.out.println(Thread.currentThread().toString() + " - After waking the Thread");

                Integer prodcuts = random.nextInt(1,10);
                distributor.supply(prodcuts);
            }
        }catch (InterruptedException e){
            System.out.println(Thread.currentThread().toString() + " - Error while trying to suplly");
        }finally {
            System.out.println(Thread.currentThread().toString() + " - End of the run.");
        }
    }
}
