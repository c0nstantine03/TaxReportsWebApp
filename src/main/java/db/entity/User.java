package db.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class User implements Cloneable {
	private final Long id;
	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private String mail;
	private Role role;
	private Residency residency;
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
