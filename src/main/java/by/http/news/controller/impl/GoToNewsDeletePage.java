package by.http.news.controller.impl;

import java.io.IOException;

import by.http.news.controller.Command;
import by.http.news.controller.CommandName;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToNewsDeletePage implements Command {

	final static String PATH = "/WEB-INF/jsp/" + CommandName.NEWS_TOOLS_DELETE.toString().toLowerCase() + ".jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH);
		requestDispatcher.forward(request, response);

	}

}
