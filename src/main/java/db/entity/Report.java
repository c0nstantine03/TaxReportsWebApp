package db.entity;


import java.sql.Timestamp;
import java.util.Objects;

public class Report {
	private Long id;
	private String code;
	private String content;
	private User author;
	private User inspector;
	private Timestamp supplied;
	private Timestamp updated;
	private Status status;

	public Report() {}

	public Report(Long id, String code, String content, User author,
				  User inspector, Timestamp supplied, Timestamp updated, Status status) {
		this.id = id;
		this.code = code;
		this.content = content;
		this.author = author;
		this.inspector = inspector;
		this.supplied = supplied;
		this.updated = updated;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getInspector() {
		return inspector;
	}

	public void setInspector(User inspector) {
		this.inspector = inspector;
	}

	public Timestamp getSupplied() {
		return supplied;
	}

	public void setSupplied(Timestamp supplied) {
		this.supplied = supplied;
	}

	public Timestamp getUpdated() {
		return updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Report report = (Report) obj;
		return id.equals(report.id) && code.equals(report.code) && content.equals(report.content) &&
				author.equals(report.author) && inspector.equals(report.inspector) &&
				supplied.equals(report.supplied) && updated.equals(report.updated) && status.equals(report.status);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, code, content, author, inspector, supplied, updated, status);
	}

	@Override
	public String toString() {
		return "Report{" +
				"id=" + id +
				", code='" + code + '\'' +
				", author='" + author + '\'' +
				", inspector='" + inspector + '\'' +
				", supplied='" + supplied + '\'' +
				", updated='" + updated + '\'' +
				", status='" + status + '\'' +
				'}';
	}
}
