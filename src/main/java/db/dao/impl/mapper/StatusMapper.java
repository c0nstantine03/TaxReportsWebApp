package db.dao.impl.mapper;

import db.entity.Status;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusMapper {

	public Status extractFromResultSet(@NotNull ResultSet resultSet) throws SQLException {
		return new Status(
				resultSet.getLong("id"),
				resultSet.getString("code"),
				resultSet.getString("name"),
				resultSet.getBoolean("closed")
		);
	}
}
