package org.example.context.commands.DepartmentCommands;

import org.example.annotation.Injectable;
import org.example.context.commands.Command;
import org.example.controllers.DepartmentController;
import org.example.factory.BeanFactory;
import org.example.models.Department;

import java.util.Scanner;

@Injectable
public class CreateDepartmentCommand extends Command {
    @Override
    public boolean execute() {
        Scanner scanner = new Scanner(System.in);
        DepartmentController departmentController = BeanFactory.getInstance().getBean(DepartmentController.class);
        System.out.println("Введите название отдела: ");
        String name = scanner.nextLine();
        System.out.println("Введите часы работы отдела: ");
        String workTime = scanner.nextLine();
        departmentController.addNewDepartment(BeanFactory.getInstance().getBean(Department.class));
        System.out.println("Отдел " + name + " успешно создан!");
        return true;
    }
}
