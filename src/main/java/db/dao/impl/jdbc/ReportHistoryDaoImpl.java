package db.dao.impl.jdbc;

import db.dao.ReportHistoryDao;
import db.dao.impl.mapper.ReportHistoryMapper;
import db.entity.ReportHistory;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportHistoryDaoImpl implements ReportHistoryDao, General<ReportHistory> {
	private static final Logger logger = Logger.getLogger(ReportHistoryDaoImpl.class.getName());
	private final Connection connection;
	private final String tableName = "report_history";

	public ReportHistoryDaoImpl(@NotNull Connection connection) {
		this.connection = connection;
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage());
		}
	}

	@Override
	public ReportHistory getMappedEntity(ResultSet resultSet) throws SQLException {
		return new ReportHistoryMapper().extractFromResultSet(resultSet);
	}

	@Override
	public Optional<ReportHistory> insert(@NotNull ReportHistory entity) throws SQLException {
		String SQL_INSERT = """
				INSERT INTO %s (code, content, author_id, inspector_id, status_id, comment)
				VALUES ('%s', ?, ?, ?, ?, ?)""".formatted(tableName, entity.getCode());

		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)) {
			preparedStatement.setString(1, entity.getContent());
			preparedStatement.setLong(2, entity.getAuthor().getId());
			preparedStatement.setLong(3, entity.getInspector().getId());
			preparedStatement.setLong(4, entity.getStatus().getId());
			preparedStatement.setString(5, entity.getComment());

			preparedStatement.executeUpdate();
			connection.commit();
			return findLastByCode(entity.getCode());
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage());
			connection.rollback();
		}
		return Optional.empty();
	}

	@Override
	public Optional<ReportHistory> findById(Long id) {
		String SQL_SELECT_BY_ID = """
				SELECT id, code, content, author_id, inspector_id, updated, status_id, comment
				FROM %s WHERE id = %d""".formatted(tableName, id);

		return findOneBy(connection, SQL_SELECT_BY_ID, logger);
	}

	@Override
	public void update(@NotNull ReportHistory entity) throws SQLException {
		String SQL_UPDATE = """
				UPDATE %s SET code = '%s', content = ?, author_id = ?,
				inspector_id = ?, updated = NOW(), status_id = ?, comment = ?
				WHERE id = %d""".formatted(tableName, entity.getCode(), entity.getId());

		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
			preparedStatement.setString(1, entity.getContent());
			preparedStatement.setLong(2, entity.getAuthor().getId());
			preparedStatement.setLong(3, entity.getInspector().getId());
			preparedStatement.setLong(4, entity.getStatus().getId());
			preparedStatement.setString(5, entity.getComment());

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
	public List<ReportHistory> getAll() {
		String SQL_SELECT_ALL = """
				SELECT id, code, content, author_id, inspector_id,
				updated, status_id, comment FROM %s""".formatted(tableName);

		return findManyBy(connection, SQL_SELECT_ALL, logger);
	}

	@Override
	public List<ReportHistory> findByCode(String code) {
		String SQL_SELECT_BY_CODE = """
				SELECT id, code, content, author_id, inspector_id,
				updated, status_id, comment FROM %s WHERE code = '%s'""".formatted(tableName, code);

		return findManyBy(connection, SQL_SELECT_BY_CODE, logger);
	}

	@Override
	public Optional<ReportHistory> findLastByCode(String code) {
		String SQL_SELECT_BY_CODE = """
				SELECT id, code, content, author_id, inspector_id,
				updated, status_id, comment FROM %s WHERE code = '%s'
				ORDER BY id DESC LIMIT 1""".formatted(tableName, code);

		return findOneBy(connection, SQL_SELECT_BY_CODE, logger);
	}

	@Override
	public List<ReportHistory> findClosedForAuthor(Long id) {
		String SQL_SELECT_BY_AUTHOR_ID = """
				SELECT id, code, content, author_id, inspector_id,
				updated, status_id, comment FROM %s WHERE author_id = %d""".formatted(tableName, id);

		return findManyBy(connection, SQL_SELECT_BY_AUTHOR_ID, logger);
	}

	@Override
	public List<ReportHistory> findClosedForInspector(Long id) {
		String SQL_SELECT_BY_INSPECTOR_ID = """
				SELECT id, code, content, author_id, inspector_id,
				updated, status_id, comment FROM %s WHERE inspector_id = %d""".formatted(tableName, id);

		return findManyBy(connection, SQL_SELECT_BY_INSPECTOR_ID, logger);
	}
}
