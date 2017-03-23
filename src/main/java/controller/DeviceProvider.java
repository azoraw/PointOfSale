package controller;

import model.Item;

import java.util.List;

public interface DeviceProvider {

    void itemAdded(Item item);

    void itemNotFound();

    void invalidCode();

    void finalizeTransaction(List<Item> items, String s);

}
