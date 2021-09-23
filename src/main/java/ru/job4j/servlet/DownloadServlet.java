package ru.job4j.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(value = "/download",
        initParams = @WebInitParam(name = "fileStorage", value = "C:/job4j_cars/storage"))
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("photo");
        if (name.equals("")) {
            name = "no_picture.jpg";
        }
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
        try (FileInputStream stream = new FileInputStream(
                new File(getInitParameter("fileStorage") + "/" + name))) {
            resp.getOutputStream().write(stream.readAllBytes());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
