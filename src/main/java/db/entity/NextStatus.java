package db.entity;

import java.util.Objects;

public class NextStatus implements Cloneable {
	private Status currentStatus;
	private Status nextStatus;

	public NextStatus() {}

	public NextStatus(Long currentId, Long nextId) {
		this.currentStatus = new Status(currentId);
		this.nextStatus = new Status(nextId);
	}

	public NextStatus(Status currentStatus, Status nextStatus) {
		this.currentStatus = currentStatus;
		this.nextStatus = nextStatus;
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
		return currentStatus.equals(status.currentStatus) && nextStatus.equals(status.nextStatus);
	}

	@Override
	public int hashCode() {
		return Objects.hash(currentStatus, nextStatus);
	}

	@Override
	public String toString() {
		return "Personality{" +
				"currentStatus=" + currentStatus +
				", nextStatus='" + nextStatus +
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
