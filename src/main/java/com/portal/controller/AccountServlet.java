package com.portal.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.portal.model.Event;
import com.portal.model.User;
import com.portal.service.AccountService;
import com.portal.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/accounts/*"})
@MultipartConfig()
public class AccountServlet extends HttpServlet {

    AccountService service = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       User user = service.read(Integer.parseInt(req.getParameter("account-id"))).getUser();
       Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
       String json = gson.toJson(user);

       System.out.println(json);
       resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.create(req.getParameter("name"), req.getParameter("pass"), resp.getWriter());
    }

    private boolean hasNumber(String s){
        try {
            Integer.valueOf(s);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
