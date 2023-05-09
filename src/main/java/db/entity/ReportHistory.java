package db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ReportHistory implements Cloneable {
	private final Long id;
	private String code;
	private String content;
	private User author;
	private User inspector;
	private Timestamp updated;
	private Status status;
	private String comment;

	@Override
	public Object clone() throws CloneNotSupportedException {
		Object object = super.clone();
		ReportHistory report = (ReportHistory) object;
		report.setAuthor((User) author.clone());
		report.setInspector((User) inspector.clone());
		report.setUpdated((Timestamp) updated.clone());
		report.setStatus((Status) status.clone());
		return object;
	}
}
