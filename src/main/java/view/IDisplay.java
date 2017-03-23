package view;

import model.Item;

public interface IDisplay {

    void displayItem(Item item);

    void displayError(String error);

    void displayTotalSum(String sum);
}
