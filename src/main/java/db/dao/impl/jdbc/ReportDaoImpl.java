package db.dao.impl.jdbc;

import db.dao.ReportDao;
import db.dao.impl.mapper.ReportMapper;
import db.entity.Report;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportDaoImpl implements ReportDao, General<Report> {
	private static final Logger logger = Logger.getLogger(ReportDaoImpl.class.getName());
	private final Connection connection;
	private final String tableName = "report_list";

	public ReportDaoImpl(@NotNull Connection connection) {
		this.connection = connection;
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage());
		}
	}

	@Override
	public Report getMappedEntity(ResultSet resultSet) throws SQLException {
		return new ReportMapper().extractFromResultSet(resultSet);
	}

	@Override
	public Optional<Report> insert(@NotNull Report entity) throws SQLException {
		String SQL_INSERT = """
				INSERT INTO %s (code, content, author_id, `comment`)
				VALUES (?, ?, ?, ?)""".formatted(tableName);

		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)) {
			preparedStatement.setString(1, entity.getCode());
			preparedStatement.setString(2, entity.getContent());
			preparedStatement.setLong(3, entity.getAuthor().getId());
			preparedStatement.setString(4, entity.getComment());

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
	public Optional<Report> findById(Long id) {
		String SQL_SELECT_BY_ID = """
				SELECT id, code, content, author_id, inspector_id, supplied, updated, status_id, comment
				FROM %s WHERE id = %d""".formatted(tableName, id);

		return findOneBy(connection, SQL_SELECT_BY_ID, logger);
	}

	@Override
	public void update(@NotNull Report entity) throws SQLException {
		String SQL_UPDATE = """
				UPDATE %s SET code = ?, content = ?, author_id = ?,
				inspector_id = ?, updated = NOW(), status_id = ?, comment = ?
				WHERE id = %d""".formatted(tableName, entity.getId());

		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
			preparedStatement.setString(1, entity.getCode());
			preparedStatement.setString(2, entity.getContent());
			preparedStatement.setLong(3, entity.getAuthor().getId());
			preparedStatement.setLong(4, entity.getInspector().getId());
			preparedStatement.setLong(5, entity.getStatus().getId());
			preparedStatement.setString(6, entity.getComment());

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
	public List<Report> getAll() {
		String SQL_SELECT_ALL = """
				SELECT id, code, content, author_id, inspector_id, supplied, updated, status_id, comment
				FROM %s""".formatted(tableName);

		return findManyBy(connection, SQL_SELECT_ALL, logger);
	}

	@Override
	public Optional<Report> findByCode(String code) {
		String SQL_SELECT_BY_CODE = """
				SELECT id, code, content, author_id, inspector_id, supplied, updated, status_id, comment
				FROM %s WHERE code = '%s'""".formatted(tableName, code);

		return findOneBy(connection, SQL_SELECT_BY_CODE, logger);
	}

	@Override
	public List<Report> findForAuthor(Long id) {
		String SQL_SELECT_BY_AUTHOR_ID = """
				SELECT id, code, content, author_id, inspector_id, supplied, updated, status_id, comment
				FROM %s WHERE author_id = %d""".formatted(tableName, id);

		return findManyBy(connection, SQL_SELECT_BY_AUTHOR_ID, logger);
	}

	@Override
	public List<Report> findForInspector(Long id) {
		String SQL_SELECT_BY_INSPECTOR_ID = """
				SELECT id, code, content, author_id, inspector_id, supplied, updated, status_id, comment
				FROM %s WHERE inspector_id = %d""".formatted(tableName, id);

		return findManyBy(connection, SQL_SELECT_BY_INSPECTOR_ID, logger);
	}
}
