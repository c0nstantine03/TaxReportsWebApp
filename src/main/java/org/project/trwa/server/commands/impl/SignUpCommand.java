package org.project.trwa.server.commands.impl;

import org.jetbrains.annotations.NotNull;
import org.project.trwa.db.entity.Personality;
import org.project.trwa.db.entity.Residency;
import org.project.trwa.db.entity.Role;
import org.project.trwa.db.entity.User;
import org.project.trwa.server.commands.CommandController;
import org.project.trwa.server.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class SignUpCommand implements CommandController {
	@Override
	public Boolean execute(@NotNull HttpServletRequest request, HttpServletResponse response) {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		Role role = new Role(1L);
		Residency residency = new Residency(Long.valueOf(request.getParameter("residency")));
		Personality personality = new Personality(Long.valueOf(request.getParameter("personality")));
		User user = new User(0L, login, password, firstName, lastName, mail, role,
				residency, personality, true, null, null);
		Optional<User> newUser = new UserServiceImpl().registerUser(user);
		newUser.ifPresent(nuser -> request.getSession().setAttribute("user", nuser));
		return newUser.isPresent();
	}
}
