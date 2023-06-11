package org.project.trwa.server.services;

import org.project.trwa.db.entity.Role;
import org.project.trwa.db.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
	Optional<User> getUser(Long id);
	List<User> getAllUsers();
	List<User> getUsersByRole(Role role);
	Optional<User> loginUser(String login, String password);
	Optional<User> registerUser(User user);
	void updateUser(User user);
	void deleteUser(Long id, String password);
}
