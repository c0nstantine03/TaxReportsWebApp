package db.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class User {
	private Long id;
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

	public User() {}

	public User(Long id, String login, String password, String firstName,
				String lastName, String mail, Role role, Residency residency,
				Personality personality, Boolean isEnable, Timestamp created, Timestamp modified) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.role = role;
		this.residency = residency;
		this.personality = personality;
		this.isEnable = isEnable;
		this.created = created;
		this.modified = modified;
	}

	public User(User user) {
		this.id = user.id;
		this.login = user.login;
		this.password = user.password;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.mail = user.mail;
		this.role = new Role(user.role);
		this.residency = new Residency(user.residency);
		this.personality = new Personality(user.personality);
		this.isEnable = user.isEnable;
		this.created = (Timestamp) user.created.clone();
		this.modified = (Timestamp) user.modified.clone();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return firstName + " " + lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Residency getResidency() {
		return residency;
	}

	public void setResidency(Residency residency) {
		this.residency = residency;
	}

	public Personality getPersonality() {
		return personality;
	}

	public void setPersonality(Personality personality) {
		this.personality = personality;
	}

	public Boolean getEnable() {
		return isEnable;
	}

	public void setEnable(Boolean enable) {
		isEnable = enable;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getModified() {
		return modified;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		User user = (User) obj;
		return id.equals(user.id) && login.equals(user.login) && password.equals(user.password) &&
				firstName.equals(user.firstName) && lastName.equals(user.lastName) && mail.equals(user.mail) &&
				role.equals(user.role) && residency.equals(user.residency) && personality.equals(user.personality) &&
				isEnable.equals(user.isEnable) && created.equals(user.created) && modified.equals(user.modified);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, login, password, firstName, lastName, mail,
				role, residency, personality, isEnable, created, modified);
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", mail='" + mail + '\'' +
				", role='" + role + '\'' +
				", residency='" + residency + '\'' +
				", personality='" + personality + '\'' +
				", isEnable=" + isEnable +
				", created='" + created + '\'' +
				", modified='" + modified + '\'' +
				'}';
	}

}
