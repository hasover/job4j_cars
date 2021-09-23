package ru.job4j.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.model.CarBody;
import ru.job4j.store.HbmAdStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/car_bodies")
public class CarBodyServlet extends HttpServlet {
    private final static Gson GSON = new GsonBuilder().create();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        String json = GSON.toJson(HbmAdStore.instOf().getEntityList(CarBody.class));
        try(PrintWriter writer = resp.getWriter()) {
            writer.write(json);
        }
    }
}
