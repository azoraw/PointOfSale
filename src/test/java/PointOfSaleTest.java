import controller.Controller;
import view.BarCodeScanner;
import view.DisplayInterface;
import view.PrinterInterface;
import model.*;
import model.db.ProductDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;

import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class PointOfSaleTest {

    private PointOfSale pointOfSale;
    private BarCodeScanner barCodeScanner;

    @Mock
    private DisplayInterface display;
    @Mock
    private PrinterInterface printer;
    @Captor
    private ArgumentCaptor argCaptor;

    @Before
    public void init() {
        Map<String, Item> map = new HashMap<>();
        map.put("01", new Item("komputer", 2199.99));
        map.put("02", new Item("mysz", 45.00));
        map.put("03", new Item("spinacz", 0.01));

        barCodeScanner = new BarCodeScanner();
        ProductDAO dao = new ProductDAO(map);
        pointOfSale = new PointOfSale(dao);
        Controller controller = new Controller(pointOfSale, barCodeScanner, display, printer);

        doNothing().when(display).displayTotalSum((String) argCaptor.capture());
        doNothing().when(display).displayError((String) argCaptor.capture());
        doNothing().when(display).displayItem((Item) argCaptor.capture());
    }

    @Test
    public void integralTest() {
        barCodeScanner.input("01");
        Item item = (Item) argCaptor.getValue();
        assertEquals("komputer", item.getName());
        assertEquals(String.valueOf(2199.99), String.valueOf(item.getPrice()));
        barCodeScanner.input("02");
        barCodeScanner.input("03");
        barCodeScanner.input("finalizeTransaction");
        assertEquals("2245.00", argCaptor.getValue());
        barCodeScanner.input("02");
        barCodeScanner.input("02");
        barCodeScanner.input("02");
        item = (Item) argCaptor.getValue();
        assertEquals("mysz", item.getName());
        assertEquals(String.valueOf(45.0), String.valueOf(item.getPrice()));

    }

    @Test
    public void manyItemsTest() {
        for (int i = 0; i < 1000; i++) {
            pointOfSale.checkProduct("03");
        }
        pointOfSale.exit();
        assertEquals("10.00", argCaptor.getValue());
    }

    @Test
    public void validationTest() {
        barCodeScanner.input("");
        assertEquals("Invalid bar-code", argCaptor.getValue());
        barCodeScanner.input("finalizeTransaction");
        assertEquals("0.00", argCaptor.getValue());
    }

}