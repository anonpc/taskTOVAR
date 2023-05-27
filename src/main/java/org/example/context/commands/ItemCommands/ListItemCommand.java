package org.example.context.commands.ItemCommands;

import org.example.annotation.Injectable;
import org.example.context.commands.Command;
import org.example.controllers.ItemController;
import org.example.factory.BeanFactory;
import org.example.models.Item;

import java.util.List;

@Injectable
public class ListItemCommand extends Command {
    @Override
    public boolean execute() {
        ItemController itemController = BeanFactory.getInstance().getBean(ItemController.class);
        List<Item> items = itemController.getAllItem();
        int i = 1;
        if (items.size() != 0) {
            System.out.println("Все доступные товары:");
            for (Item m : items) {
                System.out.printf("%2d. Название: %s Стоимость: %s, Отдел: %s \n", i, m.getName(), m.getCost(), m.getDepartmentId());
                i++;
            }
        } else {
            System.out.println("Список товаров пуст!");
        }
        return true;
    }
}
