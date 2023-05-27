package org.example.context.commands.ItemCommands;

import org.example.annotation.Injectable;
import org.example.context.commands.Command;
import org.example.controllers.ItemController;
import org.example.factory.BeanFactory;
import org.example.models.Item;

import java.util.List;
import java.util.Scanner;

@Injectable
public class DeleteItemCommand extends Command {
    @Override
    public boolean execute() {
        Scanner scanner = new Scanner(System.in);
        ItemController itemController = BeanFactory.getInstance().getBean(ItemController.class);
        System.out.println("Введите название товара для удаления: ");
        String nameToDelete = scanner.nextLine();

        List<Item> listToDelete = itemController.findItemByName(nameToDelete);
        if (listToDelete.size() != 0) {
            System.out.println("Вы хотите удалить товар " + listToDelete.get(0).getName() + "?");
            System.out.println("1. ДА");
            System.out.println("2. НЕТ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    if (itemController.removeItem(listToDelete.get(0))) {
                        System.out.println("Товар был успешно удален");
                    } else {
                        System.out.println("Произошла ошибка!");
                    }
                    break;
                case 2:
                    System.out.println("Отмена операции!");
                    break;
            }
        } else {
            System.out.println("Товара с таким названием не существует!");
        }
        return true;
    }
}
