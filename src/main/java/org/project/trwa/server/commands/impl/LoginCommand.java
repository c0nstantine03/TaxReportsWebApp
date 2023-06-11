package org.project.trwa.server.commands.impl;

import org.jetbrains.annotations.NotNull;
import org.project.trwa.db.entity.User;
import org.project.trwa.server.commands.CommandController;
import org.project.trwa.server.services.UserService;
import org.project.trwa.server.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginCommand implements CommandController {
	@Override
	public Boolean execute(@NotNull HttpServletRequest request, HttpServletResponse response) {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		UserService userService = new UserServiceImpl();
		Optional<User> newUser = userService.loginUser(login, password);
		newUser.ifPresent(user -> request.getSession().setAttribute("user", user));
		return newUser.isPresent();
	}
}
