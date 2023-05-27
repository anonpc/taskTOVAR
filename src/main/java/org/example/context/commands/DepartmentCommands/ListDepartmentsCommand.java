package org.example.context.commands.DepartmentCommands;

import org.example.annotation.Injectable;
import org.example.context.commands.Command;
import org.example.controllers.DepartmentController;
import org.example.factory.BeanFactory;
import org.example.models.Department;

import java.util.List;
import java.util.Scanner;

@Injectable
public class ListDepartmentsCommand extends Command {
    @Override
    public boolean execute() {
        Scanner scanner = new Scanner(System.in);
        DepartmentController departmentController = BeanFactory.getInstance().getBean(DepartmentController.class);
        List<Department> departments = departmentController.getAllDepartment();
        int i = 1;
        if (departments.size() != 0) {
            System.out.println("Все доступные отделы:");
            for (Department m : departments) {
                System.out.printf("%2d. Name: %s TimeWork: %s \n", i, m.getName(), m.getTimeWork());
                i++;
            }
        } else {
            System.out.println("Список отделов пуст!");
        }
        return true;
    }
}
