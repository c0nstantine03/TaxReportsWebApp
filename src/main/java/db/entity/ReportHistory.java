package db.entity;

import java.sql.Timestamp;

public class ReportHistory {
	private Long id;
	private String code;
	private String content;
	private User author;
	private User inspector;
	private Timestamp created;
}
