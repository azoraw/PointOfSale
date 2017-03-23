import controller.Controller;
import view.BarCodeScanner;
import view.IDisplay;
import view.IPrinter;
import model.Item;
import model.PointOfSale;
import model.db.ItemDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ReceiptTest {
    private PointOfSale pointOfSale;

    @Mock
    private IDisplay display;

    private int size;
    private String sum;

    @Before
    public void init() {
        Map<String, Item> map = new HashMap<>();

        map.put("01", new Item("komputer", 2000.00));
        map.put("02", new Item("mysz", 10.00));
        IPrinter printer = (items, sum) -> {
            size = items.size();
            this.sum = sum;
        };
        BarCodeScanner barCodeScanner = new BarCodeScanner();
        ItemDAO dao = new ItemDAO(map);
        pointOfSale = new PointOfSale(dao);
        Controller controller = new Controller(pointOfSale, barCodeScanner, display, printer);
    }

    @Test
    public void receiptTest() {
        pointOfSale.checkProduct("01");
        pointOfSale.checkProduct("02");
        pointOfSale.exit();
        assertEquals(2, size);
        pointOfSale.exit();
        assertEquals(0, size);
    }
}
