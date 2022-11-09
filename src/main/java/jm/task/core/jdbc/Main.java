package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.Comparator;

public class Main {
    private final static UserService user= new UserServiceImpl();
    public static void main(String[] args) {
        user.createUsersTable();

        user.saveUser("Andrey","Andreevich", (byte) 25);
        user.saveUser("Ivan","Yovanovitch", (byte) 22);
        user.saveUser("Max","Maximovich", (byte) 18);
        user.saveUser("Vladimir","Vladimirovich", (byte) 17);

        user.getAllUsers();
        user.removeUserById(3);
        user.cleanUsersTable();
        user.dropUsersTable();

//        Util.closeConnection();




    }
}
