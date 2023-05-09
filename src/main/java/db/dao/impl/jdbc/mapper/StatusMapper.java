package db.dao.impl.jdbc.mapper;

import db.entity.Status;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusMapper {

	public Status mapStatus(@NotNull ResultSet resultSet) throws SQLException {
		return new Status(
				resultSet.getLong("id"),
				resultSet.getString("code"),
				resultSet.getString("name"),
				resultSet.getBoolean("closed")
		);
	}
}
