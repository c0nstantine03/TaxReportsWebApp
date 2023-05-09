package db.dao.impl.jdbc;

import db.dao.PersonalityDao;
import db.dao.impl.mapper.PersonalityMapper;
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
    public void insert(@NotNull Personality entity) throws SQLException {
        String SQL_INSERT = "INSERT INTO %s (code, name) VALUES (%s, %s)".
                formatted(tableName, entity.getCode(), entity.getName());

        DaoImplGeneral.update(connection, SQL_INSERT);
    }

    @Override
    public Personality findById(Long id) {
        Personality entity = null;
        String SQL_SELECT_BY_ID = "SELECT id, code, name FROM %s WHERE id = %d".formatted(tableName, id);

        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_ID)) {
                if (resultSet.next()) {
                    entity = new PersonalityMapper().extractFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public void update(@NotNull Personality entity) throws SQLException {
        String SQL_UPDATE = "UPDATE %s SET code = %s, name = %s WHERE id = %d".
                formatted(tableName, entity.getCode(), entity.getName(), entity.getId());

        DaoImplGeneral.update(connection, SQL_UPDATE);
    }

    @Override
    public void delete(@NotNull Personality entity) throws SQLException {
        String SQL_DELETE = "DELETE FROM %s WHERE id = %d".formatted(tableName, entity.getId());

        DaoImplGeneral.update(connection, SQL_DELETE);
    }

    @Override
    public List<Personality> getAll() {
        List<Personality> entityList = new ArrayList<>();
        String SQL_SELECT_ALL= "SELECT id, code, name FROM %s".formatted(tableName);

        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)) {
                while (resultSet.next()) {
                    Personality entity = new PersonalityMapper().extractFromResultSet(resultSet);
                    entityList.add(entity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entityList;
    }

    @Override
    public Personality findByCode(String code) {
        Personality entity = null;
        String SQL_SELECT_BY_CODE = "SELECT id, code, name FROM %s WHERE code = %s".formatted(tableName, code);

        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_CODE)) {
                if (resultSet.next()) {
                    entity = new PersonalityMapper().extractFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }
}
