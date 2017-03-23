package view;

import model.Item;
import java.util.List;

public interface IPrinter {

    void printReceipt(List<Item> items, String sum);
}
