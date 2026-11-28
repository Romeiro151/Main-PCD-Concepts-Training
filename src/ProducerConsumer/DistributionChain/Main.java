package ProducerConsumer.DistributionChain;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Main {
    private static final int NUM_RETAILERS = 5;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Distribution Chain");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 20));

            JLabel label = new JLabel("Products on the distributer: ");
            JTextField listField = new JTextField(25);
            listField.setEditable(false);

            JButton stopButton = new JButton("Stop");

            frame.add(label);
            frame.add(listField);
            frame.add(stopButton);

            Distributor distributor = new Distributor(listField);
            distributor.updateGUI();

            Supplier supplier = new Supplier(distributor);
            Retailer[] retailers = new Retailer[NUM_RETAILERS];

            for (int i = 0; i < NUM_RETAILERS; i++) {
                retailers[i] = new Retailer(distributor, i);
            }

            stopButton.addActionListener(e -> {
                stopButton.setEnabled(false);
                new Thread(() -> {
                    for (Retailer retailer : retailers) {
                        retailer.interrupt();
                    }

                    for (Retailer retailer : retailers) {
                        try {
                            System.out.println(Thread.currentThread().toString() + " - before join.");
                            retailer.join();
                        } catch (InterruptedException ex) {
                            System.out.println(Thread.currentThread().toString() + " - before dealing InterruptedException.");
                            Thread.currentThread().interrupt();
                        }
                    }

                    supplier.interrupt();
                    try {
                        System.out.println(Thread.currentThread().toString() + " - before join.");
                        supplier.join();
                    } catch (InterruptedException ex) {
                        System.out.println(Thread.currentThread().toString() + " - before dealing InterruptedException.");
                        Thread.currentThread().interrupt();
                    }
                }, "Stop-Controller").start();
            });

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            supplier.start();
            for (Retailer retailer : retailers) {
                retailer.start();
            }
        });
    }
}