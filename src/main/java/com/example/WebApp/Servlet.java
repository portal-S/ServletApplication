package com.example.WebApp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.portal.model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Servlet extends HttpServlet {

    private final static String DIR = "C:/Users/User/IdeaProjects/WebApp/src/main/java/com/portal/repository";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        User user = new User(Integer.parseInt(id), name);
        response.getWriter().write(gson.toJson(user));
    }
}
