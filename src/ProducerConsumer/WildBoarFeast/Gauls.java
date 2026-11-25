package ProducerConsumer.WildBoarFeast;

public class Gauls extends Thread {
    private final int gaulsID;
    private final String name;
    private final Table table;
    private final int maxBoars; // -1 para infinito (Versão 2), > 0 para fixo (Versão 1 e 3)

    public Gauls(int gaulsID, String name, Table table, int maxBoars) {
        this.gaulsID = gaulsID;
        this.name = name;
        this.table = table;
        this.maxBoars = maxBoars;
    }

    @Override
    public void run() {
        int eaten = 0;
        try {
            while (!isInterrupted() && (maxBoars == -1 || eaten < maxBoars)) {
                table.take("Gauls " + name + " (" + gaulsID + ")");
                eaten++;

                // Simula o tempo a comer (opcional/comentar na Versão 3 para teste rápido)
                Thread.sleep(800);
            }
        } catch (InterruptedException e) {
            System.out.println("Gauls " + name + " was interrupted.");
            interrupt();
        }
    }
}