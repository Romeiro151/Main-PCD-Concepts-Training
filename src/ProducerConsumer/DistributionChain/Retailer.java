package ProducerConsumer.DistributionChain;

public class Retailer extends Thread{
    private final Distributor distributor;
    private final int id;
    private int bought = 0;

    public Retailer(Distributor distributor, int id){
        super("Retailer " + id);
        this.distributor = distributor;
        this.id = id;
    }

    @Override
    public void run(){
        System.out.println(Thread.currentThread().toString() + " - Before running the Thread");
        try{
            while(!isInterrupted()){
                distributor.buy();
                bought++;
            }

        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().toString() + " - Error while trying to buy stock.");
            Thread.currentThread().interrupt();
        }finally{
            System.out.println(Thread.currentThread().toString() + " - Retailer " + id + " has bought " + bought + " lots");
            System.out.println(Thread.currentThread().toString() + " - End of run.");
        }
    }
}
