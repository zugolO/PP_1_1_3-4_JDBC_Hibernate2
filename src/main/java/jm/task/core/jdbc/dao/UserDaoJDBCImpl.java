package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS schema1.new_table1 (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT NOT NULL,\n" +
                "  PRIMARY KEY (`id`));";


        try (PreparedStatement preparedStatement = connection.prepareStatement(createUsersTable)) {
            preparedStatement.executeUpdate(createUsersTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String dropUsersTable = "DROP DATABASE IF EXISTS schema1.new_table1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(dropUsersTable)) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT INTO schema1.new_table1(name, lastName, age) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(saveUser)) {
            preparedStatement.setString(1,name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String removeUserById = "DELETE FROM schema1.new_table1 WHERE id ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(removeUserById)) {
            preparedStatement.execute(removeUserById);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {

        List<User> allUser = new ArrayList<>();
        String getAllUsers = "SELECT * FROM schema1.new_table1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(getAllUsers);
             ResultSet resultSet = preparedStatement.executeQuery(getAllUsers)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                allUser.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allUser;
    }
    public void cleanUsersTable() {
        String cleanUsersTable = "TRUNCATE schema1.new_table1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(cleanUsersTable)) {
            preparedStatement.executeUpdate(cleanUsersTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
