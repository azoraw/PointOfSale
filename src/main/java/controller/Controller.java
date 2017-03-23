package controller;

import model.Item;
import model.PointOfSale;
import view.BarCodeScanner;
import view.IDisplay;
import view.IPrinter;

import java.util.List;

public class Controller implements IController {

    private static final String INVALID_BAR_CODE = "Invalid bar-code";
    private static final String PRODUCT_NOT_FOUND = "Product not found";

    private IPrinter printer;
    private IDisplay display;

    public Controller(PointOfSale pointOfSale, BarCodeScanner barCodeScanner, IDisplay display, IPrinter printer) {
        pointOfSale.setController(this);
        this.display = display;
        this.printer = printer;
        barCodeScanner.addObserver(pointOfSale);
    }

    @Override
    public void itemAdded(Item item) {
        display.displayItem(item);
    }

    @Override
    public void itemNotFound() {
        display.displayError(PRODUCT_NOT_FOUND);
    }

    @Override
    public void finalizeTransaction(List<Item> items, String sum) {
        display.displayTotalSum(sum);
        printer.printReceipt(items,sum);
    }

    @Override
    public void invalidBarCode() {
        display.displayError(INVALID_BAR_CODE);
    }
}
