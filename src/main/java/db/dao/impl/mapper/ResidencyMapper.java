package db.dao.impl.mapper;

import db.entity.Residency;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResidencyMapper {

	public Residency extractFromResultSet(@NotNull ResultSet resultSet) throws SQLException {
		return new Residency(
				resultSet.getLong("id"),
				resultSet.getString("code"),
				resultSet.getString("name")
		);
	}
}

