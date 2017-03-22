package model;

import io.*;
import model.db.DaoInterface;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Albert Żóraw
 */

public class PointOfSale implements PointOfSaleInterface {

    private static final String INVALID_BAR_CODE = "Invalid bar-code";
    private static final String PRODUCT_NOT_FOUND = "Product not found";

    private DisplayInterface displayInterface;
    private PrinterInterface printerInterface;
    private DaoInterface productDAO;

    private List<Item> items;

    public PointOfSale(BarCodeScannerInterface barCodeBarCodeScannerInterface, DisplayInterface displayInterface, PrinterInterface printerInterface, DaoInterface productDAO) {
        barCodeBarCodeScannerInterface.setPointOfSale(this);
        this.displayInterface = displayInterface;
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
                displayInterface.displayItem(item.get());
            } else {
                displayInterface.displayError(PRODUCT_NOT_FOUND);
            }
        } else {
            displayInterface.displayError(INVALID_BAR_CODE);
        }
    }

    @Override
     public void onExit() {
        BigDecimal total = new BigDecimal("0").setScale(2);
        for (Item item : items) {
            total = total.add(new BigDecimal(String.valueOf(item.getPrice())));
        }
        displayInterface.displayTotalSum(total.toString());
        printerInterface.printReceipt(items, total.toString());
        items.clear();
    }

}
