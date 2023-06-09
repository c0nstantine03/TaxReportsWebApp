package db.dao.impl.mapper;

import db.entity.Personality;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonalityMapper {

	public Personality extractFromResultSet(@NotNull ResultSet resultSet) throws SQLException {
		return new Personality(
				resultSet.getLong("id"),
				resultSet.getString("code"),
				resultSet.getString("name")
		);
	}
}
