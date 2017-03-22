import model.*;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class PointOfSaleTest {

    @Test
    public void integralTest() {

        Map<String, Item> map = new HashMap<>();
        map.put("01", new Item("komputer", 2199.99));
        map.put("02", new Item("mysz", 45.00));

        BarCodeScanner barCodeScanner = new BarCodeScanner();
        Display display = new Display();
        Printer printer = new Printer();
        ProductDAO dao = new ProductDAO(map);

        PointOfSale pointOfSale = new PointOfSale(barCodeScanner, display, printer, dao);

        barCodeScanner.scanItem("");
        assertEquals("Invalid bar-code", display.getOutputMsg());
        barCodeScanner.scanItem("01");
        assertEquals("komputer: 2199,99", display.getOutputMsg());
        barCodeScanner.scanItem("02");
        barCodeScanner.scanItem("03");
        barCodeScanner.exit();
        assertEquals("total: 2244.99", display.getOutputMsg());
        barCodeScanner.scanItem("02");
        barCodeScanner.scanItem("02");
        barCodeScanner.scanItem("02");
        assertEquals("mysz: 45,00", display.getOutputMsg());
        barCodeScanner.exit();
        assertEquals("total: 135.00", display.getOutputMsg());

    }

    @Test
    public void manyItemsTest() {
        /*double manyAddition = 0;
        for (int i = 0; i < 100000; i++) {
            manyAddition = manyAddition + 0.01;
        }
        System.out.println(manyAddition);*/
        Map<String, Item> map = new HashMap<>();
        map.put("01", new Item("spinacz", 0.01));

        Display display = new Display();
        PointOfSale pointOfSale = new PointOfSale(new BarCodeScanner(),display,new Printer(), new ProductDAO(map));

        for (int i = 0; i < 100000; i++) {
            pointOfSale.onScan("01");
        }
        pointOfSale.onExit();
        assertEquals("total: 1000.00",display.getOutputMsg());

    }

}