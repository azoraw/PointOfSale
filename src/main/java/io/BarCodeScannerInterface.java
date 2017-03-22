package io;

import model.PointOfSale;

public interface BarCodeScannerInterface {
    void scanItem(String barCode);
    void exit();
    void setPointOfSale(PointOfSale pointOfSale);
}
