package db.dao.impl.jdbc.mapper;

import db.entity.NextStatus;
import db.entity.Status;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NextStatusMapper {

	public NextStatus mapNextStatus(@NotNull ResultSet resultSet) throws SQLException {
		return new NextStatus(
				new Status(resultSet.getLong("status_id")),
				new Status(resultSet.getLong("next_status_id"))
		);
	}
}
