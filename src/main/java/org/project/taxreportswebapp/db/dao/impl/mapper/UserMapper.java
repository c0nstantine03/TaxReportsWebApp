package org.project.taxreportswebapp.db.dao.impl.mapper;

import org.project.taxreportswebapp.db.entity.Personality;
import org.project.taxreportswebapp.db.entity.Residency;
import org.project.taxreportswebapp.db.entity.Role;
import org.project.taxreportswebapp.db.entity.User;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

	public User extractFromResultSet(@NotNull ResultSet resultSet) throws SQLException {
		return new User(
				resultSet.getLong("id"),
				resultSet.getString("login"),
				resultSet.getString("password"),
				resultSet.getString("first_name"),
				resultSet.getString("last_name"),
				resultSet.getString("mail"),
				new Role(resultSet.getLong("role_id")),
				new Residency(resultSet.getLong("residency_id")),
				new Personality(resultSet.getLong("person_id")),
				resultSet.getBoolean("is_enable"),
				resultSet.getTimestamp("created"),
				resultSet.getTimestamp("modified")
		);
	}
}
