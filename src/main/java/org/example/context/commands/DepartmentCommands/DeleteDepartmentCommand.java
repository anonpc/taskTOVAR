package org.example.context.commands.DepartmentCommands;

import org.example.annotation.Injectable;
import org.example.context.commands.Command;
import org.example.controllers.DepartmentController;
import org.example.factory.BeanFactory;
import org.example.models.Department;

import java.util.List;
import java.util.Scanner;

@Injectable
public class DeleteDepartmentCommand extends Command {

    @Override
    public boolean execute() {
        Scanner scanner = new Scanner(System.in);
        DepartmentController departmentController = BeanFactory.getInstance().getBean(DepartmentController.class);
        System.out.println("Введите название отдела, который вы хотите удалить: ");
        String nameToDelete = scanner.nextLine();
        List<Department> listToDelete = departmentController.findDepartmentByName(nameToDelete);
        if (listToDelete.size() != 0) {
            System.out.println("Вы хотите удалить отдел " + listToDelete.get(0).getName() + "?");
            System.out.println("1. ДА");
            System.out.println("2. НЕТ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    if (departmentController.removeDepartment(listToDelete.get(0))) {
                        System.out.println("Отдел был успешно удален");
                    } else {
                        System.out.println("Произошла ошибка!");
                    }
                    break;
                case 2:
                    System.out.println("Отмена операции!");
                    break;
            }
        } else {
            System.out.println("Отдела с именем " + nameToDelete + " не существует!");
        }
        return true;
    }
}
