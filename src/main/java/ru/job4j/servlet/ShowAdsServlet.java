package ru.job4j.servlet;

import ru.job4j.model.Ad;
import ru.job4j.store.HbmAdStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = {"/index.html", "/index.jsp"})
public class ShowAdsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Ad> ads = HbmAdStore.instOf().getAllAds();
        req.setAttribute("ads", ads);
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}
