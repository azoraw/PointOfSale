package connectors.io;

import model.Item;

public class LcdConnector implements LcdInterface {
    @Override
    public void displayItem(Item item) {
        System.out.println(item.getName() + ": " + item.getPrice());
    }

    @Override
    public void displayError(String error) {
        System.out.println(error);
    }

    @Override
    public void displayTotalSum(String sum) {
        System.out.println("total: " + sum);
    }
}
