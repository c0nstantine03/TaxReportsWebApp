package db.dao.impl.jdbc;

import db.dao.ResidencyDao;
import db.dao.impl.mapper.ResidencyMapper;
import db.entity.Residency;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResidencyDaoImpl implements ResidencyDao {
	private final Connection connection;
	private final String tableName = "residency_list";

	public ResidencyDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(@NotNull Residency entity) throws SQLException {
		String SQL_INSERT = "INSERT INTO %s (code, name) VALUES (%s, %s)".
				formatted(tableName, entity.getCode(), entity.getName());

		statementUpdate(connection, SQL_INSERT);
	}

	@Override
	public Residency findById(Long id) {
		Residency entity = null;
		String SQL_SELECT_BY_ID = "SELECT id, code, name FROM %s WHERE id = %d".formatted(tableName, id);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_ID)) {
				if (resultSet.next()) {
					entity = new ResidencyMapper().extractFromResultSet(resultSet);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public void update(@NotNull Residency entity) throws SQLException {
		String SQL_UPDATE = "UPDATE %s SET code = %s, name = %s WHERE id = %d".
				formatted(tableName, entity.getCode(), entity.getName(), entity.getId());

		statementUpdate(connection, SQL_UPDATE);
	}

	@Override
	public void delete(@NotNull Residency entity) throws SQLException {
		String SQL_DELETE = "DELETE FROM %s WHERE id = %d".formatted(tableName, entity.getId());

		statementUpdate(connection, SQL_DELETE);
	}

	@Override
	public List<Residency> getAll() {
		List<Residency> entityList = new ArrayList<>();
		String SQL_SELECT_ALL= "SELECT id, code, name FROM %s".formatted(tableName);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)) {
				while (resultSet.next()) {
					Residency entity = new ResidencyMapper().extractFromResultSet(resultSet);
					entityList.add(entity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entityList;
	}

	@Override
	public Residency findByCode(String code) {
		Residency entity = null;
		String SQL_SELECT_BY_CODE = "SELECT id, code, name FROM %s WHERE code = %s".formatted(tableName, code);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_CODE)) {
				if (resultSet.next()) {
					entity = new ResidencyMapper().extractFromResultSet(resultSet);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}
}
