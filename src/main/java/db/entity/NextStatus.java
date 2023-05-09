package db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class NextStatus implements Cloneable {
	private final Long id;
	private Status currentStatus;
	private Status nextStatus;

	@Override
	public Object clone() throws CloneNotSupportedException {
		Object object = super.clone();
		NextStatus status = (NextStatus) object;
		status.setCurrentStatus((Status) currentStatus.clone());
		status.setNextStatus((Status) nextStatus.clone());
		return object;
	}
}
