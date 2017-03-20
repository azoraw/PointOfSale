package connectors.io;

public interface BarCodeScannerInterface {
    void scanItem(String barCode);
    void exit();
}
