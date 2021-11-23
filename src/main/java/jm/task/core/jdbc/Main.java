package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 38);
        userService.saveUser("Fedor", "Leonov", (byte) 20);
        userService.saveUser("Maria", "Sidorova", (byte) 25);
        userService.saveUser("Olga", "Sedova", (byte) 33);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
