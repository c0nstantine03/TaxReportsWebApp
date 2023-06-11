package org.project.trwa.db.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_list")
public class User implements Cloneable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private String mail;
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
	@ManyToOne
	@JoinColumn(name = "residency_id")
	private Residency residency;
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Personality personality;
	private Boolean isEnable;
	private Timestamp created;
	private Timestamp modified;

	public String getName() {
		return firstName + " " + lastName;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Object object = super.clone();
		User user = (User) object;
		user.setRole((Role) role.clone());
		user.setResidency((Residency) residency.clone());
		user.setPersonality((Personality) personality.clone());
		user.setCreated((Timestamp) created.clone());
		user.setModified((Timestamp) modified.clone());
		return object;
	}
}
