package ProducerConsumer.WildBoarFeast;

public class WildBoarFeast {

    public static void main(String[] args) throws InterruptedException {
        Table table = new Table(10);

        Cook a = new Cook(table, 1, "Jorge", -1);
        Cook b = new Cook(table, 2, "Rui", -1);
        Cook c = new Cook(table, 3, "Santiago", -1);

        Gauls d = new Gauls(1, "Jonas", table, -1);
        Gauls e = new Gauls(2, "Unas", table, -1);
        Gauls f = new Gauls(3, "Mario", table, -1);

        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
        f.start();

        Thread.sleep(10000);
        System.out.println("\n--- END ---\n");

        a.interrupt();
        b.interrupt();
        c.interrupt();
        d.interrupt();
        e.interrupt();
        f.interrupt();
    }
}