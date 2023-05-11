package db.dao.impl.jdbc;

import db.dao.RoleDao;
import db.dao.impl.mapper.RoleMapper;
import db.entity.Role;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoleDaoImpl implements RoleDao, General<Role> {
	private static final Logger logger = Logger.getLogger(RoleDaoImpl.class.getName());
	private final Connection connection;
	private final String tableName = "role_list";

	public RoleDaoImpl(@NotNull Connection connection) {
		this.connection = connection;
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage());
		}
	}

	@Override
	public Role getMappedEntity(ResultSet resultSet) throws SQLException {
		return new RoleMapper().extractFromResultSet(resultSet);
	}

	@Override
	public Optional<Role> insert(@NotNull Role entity) throws SQLException {
		String SQL_INSERT = "INSERT INTO %s (code, name) VALUES ('%s', '%s')".
				formatted(tableName, entity.getCode(), entity.getName());

		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)) {
			preparedStatement.executeUpdate();
			connection.commit();
			return findByCode(entity.getCode());
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage());
			connection.rollback();
		}
		return Optional.empty();
	}

	@Override
	public Optional<Role> findById(Long id) {
		String SQL_SELECT_BY_ID = "SELECT id, code, name FROM %s WHERE id = %d".formatted(tableName, id);

		return findOneBy(connection, SQL_SELECT_BY_ID, logger);
	}

	@Override
	public void update(@NotNull Role entity) throws SQLException {
		String SQL_UPDATE = "UPDATE %s SET code = '%s', name = '%s' WHERE id = %d".
				formatted(tableName, entity.getCode(), entity.getName(), entity.getId());

		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			logger.warning(e.getMessage());
			connection.rollback();
		}
	}

	@Override
	public void delete(Long id) throws SQLException {
		String SQL_DELETE = "DELETE FROM %s WHERE id = %d".formatted(tableName, id);

		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			logger.warning(e.getMessage());
			connection.rollback();
		}
	}

	@Override
	public List<Role> getAll() {
		String SQL_SELECT_ALL= "SELECT id, code, name FROM %s".formatted(tableName);

		return findManyBy(connection, SQL_SELECT_ALL, logger);
	}

	@Override
	public Optional<Role> findByCode(String code) {
		String SQL_SELECT_BY_CODE = "SELECT id, code, name FROM %s WHERE code = '%s'".formatted(tableName, code);

		return findOneBy(connection, SQL_SELECT_BY_CODE, logger);
	}
}
