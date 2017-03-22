
import io.BarCodeScannerInterface;
import model.PointOfSale;

public class BarCodeScanner implements BarCodeScannerInterface {

    private PointOfSale pointOfSale;

    public void setPointOfSale(PointOfSale pointOfSale) {
        this.pointOfSale = pointOfSale;
    }

    @Override
    public void scanItem(String barCode) {
        pointOfSale.onScan(barCode);
    }

    @Override
    public void exit() {
        pointOfSale.onExit();
    }

}
