package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.annotation.Inject;
import org.example.annotation.Injectable;
import org.example.dao.ItemDao;
import org.example.models.Item;

import java.util.List;


@Injectable
public class ItemController {
    @Inject
    private ItemDao itemDao;

    public boolean removeItem(Item item) {
        return itemDao.removeItem(item);
    }

    public List<Item> getAllItem() {
        return itemDao.getAllItems();
    }

    public List<Item> findItemByName(String name) {
        return itemDao.find(name);
    }

    public void addItem(Item item) {
        itemDao.create(item);
    }

    public void update(Item item) {
        itemDao.update(item);
    }
}
