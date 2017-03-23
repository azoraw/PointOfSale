package controller;

import model.Item;

import java.util.List;

public interface IController {

    void itemAdded(Item item);

    void itemNotFound();

    void invalidBarCode();

    void finalizeTransaction(List<Item> items, String s);

}
