package ru.job4j.servlet;

import ru.job4j.model.User;
import ru.job4j.store.HbmUserStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reg.do")
public class RegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
        String email = req.getParameter("login");
        String password = req.getParameter("password");
        User user = new User(name, email, password);
        try {
            HbmUserStore.instOf().saveUser(user);
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("./reg_success.html");
        } catch (Exception ex) {
            resp.setStatus(403);
            req.setAttribute("error", "Указанный логин существует");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
        }
    }
}
