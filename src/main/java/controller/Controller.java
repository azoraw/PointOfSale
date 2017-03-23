package controller;

import model.PointOfSale;
import view.BarCodeScanner;
import view.DisplayInterface;
import view.PrinterInterface;

public class Controller implements DeviceProvider{
    private PrinterInterface printer;
    private DisplayInterface display;

    public Controller(PointOfSale pointOfSale, BarCodeScanner barCodeScanner, DisplayInterface display, PrinterInterface printer) {
        pointOfSale.setController(this);
        this.display = display;
        this.printer = printer;
        barCodeScanner.addObserver(pointOfSale);
    }

    public DisplayInterface getDisplay() {
        return display;
    }

    public PrinterInterface getPrinter() {
        return printer;
    }
}
