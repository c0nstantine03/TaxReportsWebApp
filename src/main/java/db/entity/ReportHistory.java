package db.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class ReportHistory implements Cloneable {
	private Long id;
	private String code;
	private String content;
	private User author;
	private User inspector;
	private Timestamp updated;
	private Status status;
	private String comment;

	public ReportHistory() {}

	public ReportHistory(Long id) {
		this.id = id;
	}

	public ReportHistory(Long id, String code, String content, User author,
						 User inspector, Timestamp updated, Status status, String comment) {
		this.id = id;
		this.code = code;
		this.content = content;
		this.author = author;
		this.inspector = inspector;
		this.updated = updated;
		this.status = status;
		this.comment = comment;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		ReportHistory report = (ReportHistory) obj;
		return id.equals(report.id) && code.equals(report.code) && content.equals(report.content) &&
			   author.equals(report.author) && inspector.equals(report.inspector) &&
			   updated.equals(report.updated) && status.equals(report.status) && comment.equals(report.comment);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, code, content, author, inspector, updated, status, comment);
	}

	@Override
	public String toString() {
		return "Report{" +
			   "id=" + id +
			   ", code='" + code + '\'' +
			   ", author='" + author + '\'' +
			   ", inspector=" + inspector +
			   ", updated=" + updated +
			   ", status=" + status +
			   ", comment='" + comment + '\'' +
			   '}';
	}

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
