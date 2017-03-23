package model;

import controller.Controller;
import model.db.DaoInterface;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Albert Żóraw
 */

public class PointOfSale implements PointOfSaleInterface, Observer {

    private static final String INVALID_BAR_CODE = "Invalid bar-code";
    private static final String PRODUCT_NOT_FOUND = "Product not found";

    private Controller controller;
    private DaoInterface productDAO;

    private List<Item> items;

    public PointOfSale(DaoInterface productDAO) {
        this.productDAO = productDAO;
        items = new ArrayList<>();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void checkProduct(String barCode) {
        Optional<Item> item = productDAO.checkProductAvailability(barCode);
        if (item.isPresent()) {
            items.add(item.get());
            controller.getDisplay().displayItem(item.get());
        } else {
            controller.getDisplay().displayError(PRODUCT_NOT_FOUND);
        }
    }

    @Override
    public void exit() {
        BigDecimal total = new BigDecimal("0").setScale(2, BigDecimal.ROUND_HALF_UP);
        for (Item item : items) {
            total = total.add(new BigDecimal(String.valueOf(item.getPrice())));
        }
        controller.getDisplay().displayTotalSum(total.toString());
        controller.getPrinter().printReceipt(items, total.toString());
        items.clear();
    }

    @Override
    public void update(Observable o, Object input) {
        switch ((String) input) {
            case "":
                controller.getDisplay().displayError(INVALID_BAR_CODE);
                break;
            case "exit":
                exit();
                break;
            default:
                checkProduct((String) input);
        }
    }
}
