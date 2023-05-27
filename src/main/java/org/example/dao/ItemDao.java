package org.example.dao;

import org.example.models.Item;

import java.util.List;

public interface ItemDao extends Dao<Item>{
    List<Item> getAllItems();
    List<Item> find(String name);

    boolean removeItem(Item item);
}
