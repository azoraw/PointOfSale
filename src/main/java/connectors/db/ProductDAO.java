package connectors.db;

import model.Item;

import java.util.Map;
import java.util.Optional;

public class ProductDAO implements DataBaseInterface {

    private Map<String, Item> map;

    public ProductDAO(Map<String, Item> map) {
        this.map = map;
    }

    @Override
    public Optional<Item> checkProductAvailability(String barCode) {
        return Optional.ofNullable(map.get(barCode));
    }
}
