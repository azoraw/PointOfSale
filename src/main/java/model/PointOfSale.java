package model;

import controller.Controller;
import controller.DeviceProvider;
import model.db.DaoInterface;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Albert Żóraw
 */

public class PointOfSale implements PointOfSaleInterface, Observer {

    private DeviceProvider controller;
    private DaoInterface productDAO;

    private List<Item> items;

    public PointOfSale(DaoInterface productDAO) {
        this.productDAO = productDAO;
        items = new ArrayList<>();
    }

    public void setController(DeviceProvider controller) {
        this.controller = controller;
    }

    @Override
    public void checkProduct(String barCode) {
        Optional<Item> item = productDAO.checkProductAvailability(barCode);
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
                controller.invalidCode();
                break;
            case "finalizeTransaction":
                exit();
                break;
            default:
                checkProduct((String) input);
        }
    }
}
