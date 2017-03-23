package app;

import controller.Controller;
import model.Item;
import model.PointOfSale;
import model.db.ProductDAO;
import view.BarCodeScanner;

import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        Map<String, Item> map = new HashMap<>();
        map.put("01", new Item("komputer", 2199.99));
        map.put("02", new Item("mysz", 45.00));
        map.put("03", new Item("spinacz", 0.01));
        PointOfSale pointOfSale = new PointOfSale(new ProductDAO(map));
        GUI gui = new GUI(new BarCodeScanner());
        Controller controller = new Controller(pointOfSale, gui.getBarCodeScanner(),gui,gui);

    }
}
