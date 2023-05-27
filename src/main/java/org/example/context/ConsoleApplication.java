package org.example.context;

import org.example.annotation.Inject;
import org.example.annotation.Injectable;
import org.example.context.commands.Command;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;

@Injectable
public class ConsoleApplication {
    private final Scanner scanner = new Scanner(System.in);
    @Inject
    private Command[] commands;

    public void start() {
        while (true) {
            try {
                if (!step()) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка при вводе, повторите попытку");
                scanner.nextLine();
            }
        }
        System.out.println("Finish");
    }

    private boolean step() {
        printMainMenu();
        System.out.print("> ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 0 || choice >= commands.length) {
            System.out.println("Ошибочная команда!");
            return true;
        }

        Command command = commands[choice];
        return command.execute();
    }

    public void printMainMenu() {
        System.out.println("Выберите операцию");
        Stream.of(commands)
                .map(command -> command.getClass().getSimpleName())
                .forEach(System.out::println);
    }
}
