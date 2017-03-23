package model.db;

import model.Item;
import java.util.Optional;

public interface DaoInterface {
    Optional<Item> checkProductAvailability(final String barCode);
}
