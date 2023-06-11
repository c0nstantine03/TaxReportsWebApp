package org.project.trwa.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "next_statuses")
public class NextStatus implements Cloneable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "status_id")
	private Status currentStatus;
	@ManyToOne
	@JoinColumn(name = "next_status_id")
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
