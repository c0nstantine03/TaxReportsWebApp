package db.dao.impl.jdbc;

import db.dao.ReportDao;
import db.entity.Report;
import db.entity.Status;
import db.entity.User;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReportDaoImpl implements ReportDao {
	private final Connection connection;
	private final String tableName = "report_list";

	public ReportDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(@NotNull Report entity) {
		String SQL_INSERT = """
				INSERT INTO %s (code, content, author_id, inspector_id, status_id)
				VALUES (%s, %s, %d, %d, %d)""".formatted(tableName, entity.getCode(), entity.getContent(),
				entity.getAuthor().getId(), entity.getInspector().getId(), entity.getStatus().getId());

		String SQL_SELECT_ID_BY_CODE = "SELECT id FROM %s WHERE code = %s".
				formatted(tableName, entity.getCode());

		try {
			// insert value
			try (Statement statement = connection.createStatement()) {
				statement.executeUpdate(SQL_INSERT);
			} catch (SQLException e) {
				throw new SQLException("In insert statement: ", e);
			}
			// get auto-generic id
			try (Statement statement = connection.createStatement()) {
				try(ResultSet resultSet = statement.executeQuery(SQL_SELECT_ID_BY_CODE)) {
					if(resultSet.next()) {
						entity.setId(resultSet.getLong("id"));
					}
				}
			} catch (SQLException e) {
				throw new SQLException("In select statement: ", e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Report findById(Long id) {
		Report entity = null;
		String SQL_SELECT_BY_ID = """
				SELECT id, code, content, author_id, inspector_id, supplied, updated, status_id
				FROM %s WHERE id = %d""".formatted(tableName, id);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_ID)) {
				if (resultSet.next()) {
					entity = new Report(
							resultSet.getLong("id"),
							resultSet.getString("code"),
							resultSet.getString("content"),
							new User(resultSet.getLong("author_id")),
							new User(resultSet.getLong("inspector_id")),
							resultSet.getTimestamp("supplied"),
							resultSet.getTimestamp("updated"),
							new Status(resultSet.getLong("status_id"))
					);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public void update(@NotNull Report entity) {
		String SQL_UPDATE = """
				UPDATE %s SET code = %s, content = %s, author_id = %d, \
				inspector_id = %d, updated = NOW(), status_id = %d
				WHERE id = %d""".formatted(tableName, entity.getCode(),
				entity.getContent(), entity.getAuthor().getId(),
				entity.getInspector().getId(), entity.getStatus().getId(), entity.getId());

		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(SQL_UPDATE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(@NotNull Report entity) {
		String SQL_DELETE = "DELETE FROM %s WHERE id = %d".formatted(tableName, entity.getId());

		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(SQL_DELETE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Report> getAll() {
		List<Report> entityList = new ArrayList<>();
		String SQL_SELECT_ALL = """
				SELECT id, code, content, author_id, inspector_id, \
				supplied, updated, status_id FROM %s""".formatted(tableName);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)) {
				while (resultSet.next()) {
					Report entity = new Report(
							resultSet.getLong("id"),
							resultSet.getString("code"),
							resultSet.getString("content"),
							new User(resultSet.getLong("author_id")),
							new User(resultSet.getLong("inspector_id")),
							resultSet.getTimestamp("supplied"),
							resultSet.getTimestamp("updated"),
							new Status(resultSet.getLong("status_id"))
					);
					entityList.add(entity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entityList;
	}

	@Override
	public Report findByCode(String code) {
		Report entity = null;
		String SQL_SELECT_BY_CODE = """
				SELECT id, code, content, author_id, inspector_id, supplied, updated, status_id
				FROM %s WHERE code = %s""".formatted(tableName, code);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_CODE)) {
				if (resultSet.next()) {
					entity = new Report(
							resultSet.getLong("id"),
							resultSet.getString("code"),
							resultSet.getString("content"),
							new User(resultSet.getLong("author_id")),
							new User(resultSet.getLong("inspector_id")),
							resultSet.getTimestamp("supplied"),
							resultSet.getTimestamp("updated"),
							new Status(resultSet.getLong("status_id"))
					);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public List<Report> findForAuthor(Long id) {
		List<Report> entityList = new ArrayList<>();
		String SQL_SELECT_BY_AUTHOR_ID = """
				SELECT id, code, content, author_id, inspector_id, supplied, updated, status_id
				FROM %s WHERE author_id = %d""".formatted(tableName, id);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_AUTHOR_ID)) {
				while (resultSet.next()) {
					Report entity = new Report(
							resultSet.getLong("id"),
							resultSet.getString("code"),
							resultSet.getString("content"),
							new User(resultSet.getLong("author_id")),
							new User(resultSet.getLong("inspector_id")),
							resultSet.getTimestamp("supplied"),
							resultSet.getTimestamp("updated"),
							new Status(resultSet.getLong("status_id"))
					);
					entityList.add(entity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entityList;
	}

	@Override
	public List<Report> findForInspector(Long id) {
		List<Report> entityList = new ArrayList<>();
		String SQL_SELECT_BY_INSPECTOR_ID = """
				SELECT id, code, content, author_id, inspector_id, supplied, updated, status_id
				FROM %s WHERE inspector_id = %d""".formatted(tableName, id);

		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_INSPECTOR_ID)) {
				while (resultSet.next()) {
					Report entity = new Report(
							resultSet.getLong("id"),
							resultSet.getString("code"),
							resultSet.getString("content"),
							new User(resultSet.getLong("author_id")),
							new User(resultSet.getLong("inspector_id")),
							resultSet.getTimestamp("supplied"),
							resultSet.getTimestamp("updated"),
							new Status(resultSet.getLong("status_id"))
					);
					entityList.add(entity);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entityList;
	}
}
