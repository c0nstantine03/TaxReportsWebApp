package db.dao.impl.jdbc.mapper;

import db.entity.Report;
import db.entity.Role;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper {

	public Role mapRole(@NotNull ResultSet resultSet) throws SQLException {
		return new Role(
				resultSet.getLong("id"),
				resultSet.getString("code"),
				resultSet.getString("name")
		);
	}
}
