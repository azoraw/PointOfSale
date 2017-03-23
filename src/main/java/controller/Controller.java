package controller;

import model.Item;
import model.PointOfSale;
import view.BarCodeScanner;
import view.IDisplay;
import view.IPrinter;

import java.util.List;
import java.util.ResourceBundle;

public class Controller implements IController {

    private static final String RESOURCE_PATH = "messages";
    private static final String INVALID_BAR_CODE = "INVALID_BAR_CODE";
    private static final String PRODUCT_NOT_FOUND = "PRODUCT_NOT_FOUND";
    private final ResourceBundle res;

    private IPrinter printer;
    private IDisplay display;

    public Controller(PointOfSale pointOfSale, BarCodeScanner barCodeScanner, IDisplay display, IPrinter printer) {
        pointOfSale.setController(this);
        this.display = display;
        this.printer = printer;
        barCodeScanner.addObserver(pointOfSale);
        res = ResourceBundle.getBundle(RESOURCE_PATH);
    }

    @Override
    public void itemAdded(Item item) {
        display.displayItem(item);
    }

    @Override
    public void itemNotFound() {
        display.displayError(res.getString(PRODUCT_NOT_FOUND));
    }

    @Override
    public void finalizeTransaction(List<Item> items, String sum) {
        display.displayTotalSum(sum);
        printer.printReceipt(items,sum);
    }

    @Override
    public void invalidBarCode() {
        display.displayError(res.getString(INVALID_BAR_CODE));
    }
}
