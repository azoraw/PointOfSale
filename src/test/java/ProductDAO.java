
import model.Item;
import model.db.DaoInterface;

import java.util.Map;
import java.util.Optional;

public class ProductDAO implements DaoInterface {

    private Map<String, Item> map;

    public ProductDAO(Map<String, Item> map) {
        this.map = map;
    }

    @Override
    public Optional<Item> checkProductAvailability(String barCode) {
        return Optional.ofNullable(map.get(barCode));
    }
}
