package db.dao.impl.jdbc;

import db.dao.ReportHistoryDao;
import db.dao.impl.mapper.ReportHistoryMapper;
import db.entity.ReportHistory;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReportHistoryDaoImpl implements ReportHistoryDao {
	private final Connection connection;
	private final String tableName = "report_history";

	public ReportHistoryDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(@NotNull ReportHistory entity) throws SQLException {
		String SQL_INSERT = """
				INSERT INTO %s (code, content, author_id, inspector_id, status_id, comment)
				VALUES (%s, %s, %d, %d, %d, %s)""".
				formatted(tableName, entity.getCode(), entity.getContent(), entity.getAuthor().getId(),
				entity.getInspector().getId(), entity.getStatus().getId(), entity.getComment());

		DaoImplGeneral.update(connection, SQL_INSERT);
	}

	@Override
	public ReportHistory findById(Long id) {
		ReportHistory entity = null;
		String SQL_SELECT_BY_ID = """
				SELECT id, code, content, author_id, inspector_id, updated, status_id, comment
				FROM %s WHERE id = %d""".formatted(tableName, id);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_ID)) {
				if (resultSet.next()) {
					entity = new ReportHistoryMapper().extractFromResultSet(resultSet);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public void update(@NotNull ReportHistory entity) throws SQLException {
		String SQL_UPDATE = """
				UPDATE %s SET code = %s, content = %s, author_id = %d, \
				inspector_id = %d, updated = NOW(), status_id = %d, comment = %s
				WHERE id = %d""".
				formatted(tableName, entity.getCode(), entity.getContent(), entity.getAuthor().getId(),
				entity.getInspector().getId(), entity.getStatus().getId(), entity.getComment(), entity.getId());

		DaoImplGeneral.update(connection, SQL_UPDATE);
	}

	@Override
	public void delete(@NotNull ReportHistory entity) throws SQLException {
		String SQL_DELETE = "DELETE FROM %s WHERE id = %d".formatted(tableName, entity.getId());

		DaoImplGeneral.update(connection, SQL_DELETE);
	}

	@Override
	public List<ReportHistory> getAll() {
		List<ReportHistory> entityList = new ArrayList<>();
		String SQL_SELECT_ALL = """
				SELECT id, code, content, author_id, inspector_id, \
				supplied, updated, status_id FROM %s""".formatted(tableName);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)) {
				while (resultSet.next()) {
					ReportHistory entity = new ReportHistoryMapper().extractFromResultSet(resultSet);
					entityList.add(entity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entityList;
	}

	@Override
	public List<ReportHistory> findByCode(String code) {
		List<ReportHistory> entityList = new ArrayList<>();
		String SQL_SELECT_BY_CODE = """
				SELECT id, code, content, author_id, inspector_id, \
				supplied, updated, status_id FROM %s WHERE code = %s""".formatted(tableName, code);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_CODE)) {
				while (resultSet.next()) {
					ReportHistory entity = new ReportHistoryMapper().extractFromResultSet(resultSet);
					entityList.add(entity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entityList;
	}

	@Override
	public List<ReportHistory> findClosedForAuthor(Long id) {
		List<ReportHistory> entityList = new ArrayList<>();
		String SQL_SELECT_BY_AUTHOR_ID = """
				SELECT id, code, content, author_id, inspector_id, \
				supplied, updated, status_id FROM %s WHERE author_id = %d""".formatted(tableName, id);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_AUTHOR_ID)) {
				while (resultSet.next()) {
					ReportHistory entity = new ReportHistoryMapper().extractFromResultSet(resultSet);
					entityList.add(entity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entityList;
	}

	@Override
	public List<ReportHistory> findClosedForInspector(Long id) {
		List<ReportHistory> entityList = new ArrayList<>();
		String SQL_SELECT_BY_INSPECTOR_ID = """
				SELECT id, code, content, author_id, inspector_id, \
				supplied, updated, status_id FROM %s WHERE inspector_id = %d""".formatted(tableName, id);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_INSPECTOR_ID)) {
				while (resultSet.next()) {
					ReportHistory entity = new ReportHistoryMapper().extractFromResultSet(resultSet);
					entityList.add(entity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entityList;
	}
}
