package org.project.taxreportswebapp.db.dao.impl.mapper;

import org.project.taxreportswebapp.db.entity.Report;
import org.project.taxreportswebapp.db.entity.Status;
import org.project.taxreportswebapp.db.entity.User;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportMapper {

	public Report extractFromResultSet(@NotNull ResultSet resultSet) throws SQLException {
		return new Report(
				resultSet.getLong("id"),
				resultSet.getString("code"),
				resultSet.getString("content"),
				new User(resultSet.getLong("author_id")),
				new User(resultSet.getLong("inspector_id")),
				resultSet.getTimestamp("supplied"),
				resultSet.getTimestamp("updated"),
				new Status(resultSet.getLong("status_id")),
				resultSet.getString("comment")
		);
	}
}
