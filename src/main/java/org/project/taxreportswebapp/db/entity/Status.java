package org.project.taxreportswebapp.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Status implements Cloneable {
	private final Long id;
	private String code;
	private String name;
	private Boolean closed;

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
