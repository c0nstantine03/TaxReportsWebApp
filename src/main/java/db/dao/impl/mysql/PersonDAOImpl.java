package db.dao.impl.mysql;

import db.dao.PersonDAO;
import db.entity.Person;
import org.jetbrains.annotations.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAOImpl implements PersonDAO {
    private final Connection connection;
    private final String SQL_INSERT_PERSON = "INSERT INTO person_list (id, name) VALUES (?, ?)";
    private final String SQL_SELECT_ALL_PERSON = "SELECT id, name FROM person_list";
    private final String SQL_SELECT_BY_ID_PERSON = "SELECT id, name FROM person_list WHERE id = ?";
    private final String SQL_UPDATE_PERSON = "UPDATE person_list SET name = ? WHERE id = ?";
    private final String SQL_DELETE_PERSON = "DELETE FROM person_list WHERE id = ?";

    public PersonDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(@NotNull Person entity) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_PERSON)) {

            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> getAll() {

        List<Person> personList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_PERSON);

            while (resultSet.next()) {
                Person entity = new Person(resultSet.getLong("id"),
                        resultSet.getString("name"));
                personList.add(entity);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }

    @Override
    public Person getById(Long id) {

        Person entity = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID_PERSON)) {

            preparedStatement.setLong(1, entity.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                entity = new Person(resultSet.getLong("id"),
                        resultSet.getString("name"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public void update(@NotNull Person entity) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PERSON)) {

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setLong(2, entity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(@NotNull Person entity) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_PERSON)) {

            preparedStatement.setLong(1, entity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
