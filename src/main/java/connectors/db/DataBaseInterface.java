package connectors.db;

import model.Item;

import java.util.Optional;

public interface DataBaseInterface {
    Optional<Item> checkProductAvailability(String barCode);
}
