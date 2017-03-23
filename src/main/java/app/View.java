package app;

import model.Item;
import view.BarCodeScannerInterface;
import view.DisplayInterface;
import view.PrinterInterface;

import javax.swing.*;
import java.util.List;
import java.util.Observable;

public class View extends Observable implements BarCodeScannerInterface, DisplayInterface, PrinterInterface {
    private JButton scan;
    private JPanel panel;
    private JLabel info;
    private JTextField textField;
    private JFrame frame;

    public View() {
        scan.addActionListener(e -> input(textField.getText()));
        frame = new JFrame("App");
        frame.setContentPane(new View().panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void input(String input) {
        setChanged();
        notifyObservers(input);
    }

    @Override
    public void displayItem(Item item) {
        info.setText(item.getName() + ": " + item.getPrice());
    }

    @Override
    public void displayError(String error) {
        info.setText(error);
    }

    @Override
    public void displayTotalSum(String sum) {
        //do nth
    }

    @Override
    public void printReceipt(List<Item> items, String sum) {
        JOptionPane.showMessageDialog(null, "total: " + sum);
    }
}
