package db.entity;

import java.util.Objects;

public class NextStatus implements Cloneable {
	private Long id;
	private Status currentStatus;
	private Status nextStatus;

	public NextStatus() {}

	public NextStatus(Long id) {
		this.id = id;
	}

	public NextStatus(Long id, Long currentId, Long nextId) {
		this.id = id;
		this.currentStatus = new Status(currentId);
		this.nextStatus = new Status(nextId);
	}

	public NextStatus(Long id, Status currentStatus, Status nextStatus) {
		this.id = id;
		this.currentStatus = currentStatus;
		this.nextStatus = nextStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Status getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Status currentStatus) {
		this.currentStatus = currentStatus;
	}

	public Status getNextStatus() {
		return nextStatus;
	}

	public void setNextStatus(Status nextStatus) {
		this.nextStatus = nextStatus;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		NextStatus status = (NextStatus) obj;
		return id.equals(status.id) && currentStatus.equals(status.currentStatus)
			   && nextStatus.equals(status.nextStatus);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, currentStatus, nextStatus);
	}

	@Override
	public String toString() {
		return "Personality{" +
			   "id=" + id +
			   "currentStatus=" + currentStatus +
			   ", nextStatus=" + nextStatus +
			   '}';
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Object object = super.clone();
		NextStatus status = (NextStatus) object;
		status.setCurrentStatus((Status) currentStatus.clone());
		status.setNextStatus((Status) nextStatus.clone());
		return object;
	}
}
