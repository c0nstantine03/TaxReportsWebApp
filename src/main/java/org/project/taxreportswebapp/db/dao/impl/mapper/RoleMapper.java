package org.project.taxreportswebapp.db.dao.impl.mapper;

import org.project.taxreportswebapp.db.entity.Role;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper {

	public Role extractFromResultSet(@NotNull ResultSet resultSet) throws SQLException {
		return new Role(
				resultSet.getLong("id"),
				resultSet.getString("code"),
				resultSet.getString("name")
		);
	}
}
