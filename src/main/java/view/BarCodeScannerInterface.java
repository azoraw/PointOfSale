package view;

import model.PointOfSaleInterface;

public interface BarCodeScannerInterface {
    void input(String input);

    void addObserver(PointOfSaleInterface pointOfSale);
}
