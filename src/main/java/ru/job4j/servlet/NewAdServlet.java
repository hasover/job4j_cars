package ru.job4j.servlet;

import ru.job4j.model.User;
import ru.job4j.store.HbmAdStore;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@WebServlet(value = "/add.do",
        initParams = @WebInitParam(name = "fileStorage", value = "C:/job4j_cars/storage")
)
@MultipartConfig
public class NewAdServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String description = req.getParameter("description");
        int brandId = Integer.parseInt(req.getParameter("selectBrand"));
        int bodyId = Integer.parseInt(req.getParameter("selectBody"));
        User user = (User) req.getSession().getAttribute("user");
        Part filePart = req.getPart("file");
        File file = new File("");
        if(filePart.getSize() != 0) {
            file = File.createTempFile("photo", ".jpg",
                    new File(getInitParameter("fileStorage")));
            try (FileOutputStream out = new FileOutputStream(file)) {
                out.write(filePart.getInputStream().readAllBytes());
            }
        }

        HbmAdStore.instOf().addAd(description, brandId, bodyId, file.getName(), user);
        resp.sendRedirect(".");
    }
}
