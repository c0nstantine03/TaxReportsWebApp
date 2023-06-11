package org.project.trwa.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "status_list")
public class Status implements Cloneable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String code;
	private String name;
	private Boolean closed;

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
