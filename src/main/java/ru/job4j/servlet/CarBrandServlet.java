package ru.job4j.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.model.CarBrand;
import ru.job4j.store.HbmAdStore;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/car_brands")
public class CarBrandServlet extends HttpServlet {
    private final static Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        List<CarBrand> brands = HbmAdStore.instOf().getEntityList(CarBrand.class);
        String json = GSON.toJson(brands);
        try(PrintWriter writer = resp.getWriter()) {
            writer.write(json);
        }
    }
}
