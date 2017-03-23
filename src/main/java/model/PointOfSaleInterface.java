package model;

import controller.DeviceProvider;

public interface PointOfSaleInterface {
    void checkProduct(String barCode);
    void exit();

    void setController(DeviceProvider controller);
}
