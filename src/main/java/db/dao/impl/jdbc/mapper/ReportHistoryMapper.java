package db.dao.impl.jdbc.mapper;

import db.entity.ReportHistory;
import db.entity.Status;
import db.entity.User;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportHistoryMapper {

	public ReportHistory mapReportHistory(@NotNull ResultSet resultSet) throws SQLException {
		return new ReportHistory(
				resultSet.getLong("id"),
				resultSet.getString("code"),
				resultSet.getString("content"),
				new User(resultSet.getLong("author_id")),
				new User(resultSet.getLong("inspector_id")),
				resultSet.getTimestamp("updated"),
				new Status(resultSet.getLong("status_id")),
				resultSet.getString("comment")
		);
	}
}
