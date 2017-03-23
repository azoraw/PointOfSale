package model;

import controller.IController;

public interface IPointOfSale {
    void checkProduct(String barCode);
    void exit();
    void setController(IController controller);
}
