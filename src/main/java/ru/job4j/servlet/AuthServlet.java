package ru.job4j.servlet;

import ru.job4j.model.User;
import ru.job4j.store.HbmUserStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auth.do")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = HbmUserStore.instOf().getUser(login);
        if (user != null && password.equals(user.getPassword())) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(".");
        } else {
            resp.setStatus(403);
            req.setAttribute("error", "Неверное имя пользователя или пароль");
            req.getRequestDispatcher("/auth.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(".");
    }
}
