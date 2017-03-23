package model.db;

import model.Item;
import java.util.Map;
import java.util.Optional;

public class ItemDAO {

    private Map<String, Item> map;

    public ItemDAO(Map<String, Item> map) {
        this.map = map;
    }

    public Optional<Item> getItem(final String barCode) {
        return Optional.ofNullable(map.get(barCode));
    }
}
