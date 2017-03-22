package io;

import model.Item;

public interface DisplayInterface {

    void displayItem(Item item);

    void displayError(String error);

    void displayTotalSum(String sum);
}
