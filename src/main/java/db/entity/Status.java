package db.entity;

import java.util.Objects;

public class Status implements Cloneable {
	private Long id;
	private String code;
	private String name;
	private Boolean closed;

	public Status() {}

	public Status(Long id, String code, String name, Boolean closed) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.closed = closed;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean isClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Status status = (Status) obj;
		return id.equals(status.id) && code.equals(status.code) &&
				name.equals(status.name) && closed.equals(status.closed);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, code, name, closed);
	}

	@Override
	public String toString() {
		return "Status{" +
				"id=" + id +
				", code='" + code + '\'' +
				", name='" + name + '\'' +
				", closed=" + closed +
				'}';
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
