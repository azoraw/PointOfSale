package model;

public interface ScannerListener {
    void onScan(String barCode);

    void onExit();
}
