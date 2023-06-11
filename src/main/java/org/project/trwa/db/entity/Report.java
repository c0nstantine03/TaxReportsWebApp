package org.project.trwa.db.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "report_list")
public class Report implements Cloneable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String code;
	private String content;
	@ManyToOne
	@JoinColumn(name = "author_id")
	private User author;
	@ManyToOne
	@JoinColumn(name = "inspector_id")
	private User inspector;
	private Timestamp supplied;
	private Timestamp updated;
	@ManyToOne
	@JoinColumn(name = "status_id")
	private Status status;
	private String comment;

	@Override
	public Object clone() throws CloneNotSupportedException {
		Object object = super.clone();
		Report report = (Report) object;
		report.setAuthor((User) author.clone());
		report.setInspector((User) inspector.clone());
		report.setSupplied((Timestamp) supplied.clone());
		report.setUpdated((Timestamp) updated.clone());
		report.setStatus((Status) status.clone());
		return object;
	}
}
