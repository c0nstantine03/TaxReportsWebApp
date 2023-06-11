package org.project.trwa.server.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandController {
	Boolean execute(HttpServletRequest request, HttpServletResponse response);
}
