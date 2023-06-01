package org.project.trwa.db.dao.impl.mapper;

import org.project.trwa.db.entity.NextStatus;
import org.project.trwa.db.entity.Status;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NextStatusMapper {

	public NextStatus extractFromResultSet(@NotNull ResultSet resultSet) throws SQLException {
		return new NextStatus(
				resultSet.getLong("id"),
				new Status(resultSet.getLong("status_id")),
				new Status(resultSet.getLong("next_status_id"))
		);
	}
}
