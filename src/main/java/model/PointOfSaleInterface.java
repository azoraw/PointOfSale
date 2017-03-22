package model;

public interface PointOfSaleInterface {
    void onScan(String barCode);
    void onExit();
}
