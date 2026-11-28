package ProducerConsumer.GoldBars;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame janela = new JFrame("Simulação - Lingotes de Ouro");
            janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            janela.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 20));

            JLabel label = new JLabel("Ouro na balança:");
            JTextField campoPeso = new JTextField(10);
            campoPeso.setEditable(false);

            JButton botaoStop = new JButton("Stop");

            janela.add(label);
            janela.add(campoPeso);
            janela.add(botaoStop);

            Scale balanca = new Scale(campoPeso);
            Excavator escavadora = new Excavator(balanca);
            Goldsmith ourives = new Goldsmith(balanca);

            botaoStop.addActionListener(e -> {
                botaoStop.setEnabled(false);
                new Thread(() -> {
                    escavadora.interrupt();
                    ourives.interrupt();
                    try {
                        System.out.println(Thread.currentThread().toString() + " - before join.");
                        escavadora.join();
                        System.out.println(Thread.currentThread().toString() + " - before join.");
                        ourives.join();
                    } catch (InterruptedException ex) {
                        System.out.println(Thread.currentThread().toString() + " - before dealing InterruptedException.");
                    }
                }, "Controlador-Stop").start();
            });

            janela.pack();
            janela.setLocationRelativeTo(null);
            janela.setVisible(true);

            escavadora.start();
            ourives.start();
        });
    }
}