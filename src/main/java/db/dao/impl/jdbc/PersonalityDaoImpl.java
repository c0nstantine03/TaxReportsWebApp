package db.dao.impl.jdbc;

import db.dao.PersonalityDao;
import db.entity.Personality;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonalityDaoImpl implements PersonalityDao {
    private final Connection connection;
    private final String tableName = "person_list";

    public PersonalityDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(@NotNull Personality entity) {

        String SQL_INSERT_PERSON = "INSERT INTO " + tableName + " (code, name) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_PERSON)) {

            preparedStatement.setString(1, entity.getCode());
            preparedStatement.setString(2, entity.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Personality> getAll() {
        List<Personality> entityList = new ArrayList<>();

        String SQL_SELECT_ALL_PERSON = "SELECT id, code, name FROM " + tableName;

        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_PERSON)) {
                while (resultSet.next()) {
                    Personality entity = new Personality(resultSet.getLong("id"),
                            resultSet.getString("code"),
                            resultSet.getString("name"));
                    entityList.add(entity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entityList;
    }

    @Override
    public Personality findById(Long id) {
        Personality entity = null;

        String SQL_SELECT_BY_ID_PERSON = "SELECT id, code, name FROM " + tableName + " WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID_PERSON)) {

            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    entity = new Personality(resultSet.getLong("id"),
                            resultSet.getString("code"),
                            resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public void update(@NotNull Personality entity) {

        String SQL_UPDATE_PERSON = "UPDATE " + tableName + " SET code = ?, name = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PERSON)) {

            preparedStatement.setString(1, entity.getCode());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setLong(3, entity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(@NotNull Personality entity) {

        String SQL_DELETE_PERSON = "DELETE FROM " + tableName + " WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_PERSON)) {

            preparedStatement.setLong(1, entity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
