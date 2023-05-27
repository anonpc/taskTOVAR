package org.example.context.commands.ItemCommands;

import org.example.annotation.Injectable;
import org.example.context.commands.Command;
import org.example.controllers.ItemController;
import org.example.factory.BeanFactory;
import org.example.models.Item;

import java.util.Scanner;

@Injectable
public class AddItemCommand extends Command {

    @Override
    public boolean execute() {
        Scanner scanner = new Scanner(System.in);
        ItemController itemController = BeanFactory.getInstance().getBean(ItemController.class);
        System.out.println("Введите название товара: ");
        String name = scanner.nextLine();
        System.out.println("Введите стоимость товара: ");
        int cost = scanner.nextInt();
        System.out.println("Введите id отдела: ");
        Long departmentId = scanner.nextLong();
        itemController.addItem(BeanFactory.getInstance().getBean(Item.class));
        return true;
    }

}
