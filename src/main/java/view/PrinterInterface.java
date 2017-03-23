package view;

import model.Item;
import java.util.List;

public interface PrinterInterface {

    void printReceipt(List<Item> items, String sum);
}
