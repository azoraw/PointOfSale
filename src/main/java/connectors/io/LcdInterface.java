package connectors.io;

import model.Item;

public interface LcdInterface {

    void displayItem(Item item);

    void displayError(String error);

    void displayTotalSum(String sum);
}
