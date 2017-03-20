package model;

import connectors.db.ProductDAO;
import connectors.io.BarCodeScannerConnector;
import connectors.io.LcdConnector;
import connectors.io.PrinterConnector;
import connectors.io.PrinterInterface;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Albert Żóraw
 */

public class PointOfSale implements ScannerListener {
    private static final String INVALID_BAR_CODE = "Invalid bar-code";
    private static final String PRODUCT_NOT_FOUND = "Product not found";

    private LcdConnector lcdConnector;
    private PrinterInterface printerInterface;
    private ProductDAO productDAO;

    private List<Item> items;

    public PointOfSale(BarCodeScannerConnector barCodeScannerConnector, LcdConnector lcdConnector, PrinterConnector printerInterface, ProductDAO productDAO) {
        barCodeScannerConnector.setPointOfSale(this);
        this.lcdConnector = lcdConnector;
        this.printerInterface = printerInterface;
        this.productDAO = productDAO;

        items = new ArrayList<>();
    }

    @Override
    public void onScan(String barCode) {
        if (Validator.isBarCodeValid(barCode)) {
            Optional<Item> item = productDAO.checkProductAvailability(barCode);
            if (item.isPresent()) {
                items.add(item.get());
                lcdConnector.displayItem(item.get());
            } else {
                lcdConnector.displayError(PRODUCT_NOT_FOUND);
            }
        } else {
            lcdConnector.displayError(INVALID_BAR_CODE);
        }
    }
    @Override
     public void onExit() {
        BigDecimal total = new BigDecimal("0");
        for (Item item : items) {
            total = total.add(new BigDecimal(String.valueOf(item.getPrice())));
        }
        lcdConnector.displayTotalSum(total.toString());
        printerInterface.printReceipt(items, total.toString());
        items.clear();
    }

}
