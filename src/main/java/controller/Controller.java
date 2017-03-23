package controller;

import app.GUI;
import model.Item;
import model.PointOfSale;
import model.PointOfSaleInterface;
import view.BarCodeScanner;
import view.DisplayInterface;
import view.PrinterInterface;

import java.util.List;

public class Controller implements DeviceProvider{

    private static final String INVALID_BAR_CODE = "Invalid bar-code";
    private static final String PRODUCT_NOT_FOUND = "Product not found";

    private PrinterInterface printer;
    private DisplayInterface display;

    public Controller(PointOfSaleInterface pointOfSale, BarCodeScanner barCodeScanner, DisplayInterface display, PrinterInterface printer) {
        pointOfSale.setController(this);
        this.display = display;
        this.printer = printer;
        barCodeScanner.addObserver(pointOfSale);
    }

    public Controller(PointOfSale pointOfSale, GUI gui) {
        pointOfSale.setController(this);
        this.display = gui;
        this.printer = gui;
        gui.addObserver(pointOfSale);

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
    public void invalidCode() {
        display.displayError(INVALID_BAR_CODE);
    }
}
