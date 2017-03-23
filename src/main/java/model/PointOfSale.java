package model;

import controller.IController;
import model.db.ItemDAO;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Albert Żóraw
 */

public class PointOfSale implements IPointOfSale, Observer {

    private IController controller;
    private ItemDAO itemDAO;

    private List<Item> items;

    public PointOfSale(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
        items = new ArrayList<>();
    }

    public void setController(IController controller) {
        this.controller = controller;
    }

    @Override
    public void checkProduct(String barCode) {
        Optional<Item> item = itemDAO.getItem(barCode);
        if (item.isPresent()) {
            items.add(item.get());
            controller.itemAdded(item.get());
        } else {
            controller.itemNotFound();
        }
    }

    @Override
    public void exit() {
        BigDecimal total = new BigDecimal("0").setScale(2, BigDecimal.ROUND_HALF_UP);
        for (Item item : items) {
            total = total.add(new BigDecimal(String.valueOf(item.getPrice())));
        }
        controller.finalizeTransaction(items, total.toString());
        items.clear();
    }

    @Override
    public void update(Observable o, Object input) {
        switch ((String) input) {
            case "":
                controller.invalidBarCode();
                break;
            case "finalizeTransaction":
                exit();
                break;
            default:
                checkProduct((String) input);
        }
    }
}
