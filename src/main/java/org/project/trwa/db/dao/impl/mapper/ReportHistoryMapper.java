package org.project.trwa.db.dao.impl.mapper;

import org.project.trwa.db.entity.ReportHistory;
import org.project.trwa.db.entity.Status;
import org.project.trwa.db.entity.User;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportHistoryMapper {

	public ReportHistory extractFromResultSet(@NotNull ResultSet resultSet) throws SQLException {
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
