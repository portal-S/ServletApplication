package com.portal.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet(urlPatterns = {"/files"})
@MultipartConfig(location = "/app/target/tomcat.34050/webapps/expanded/WEB-INF/classes/files/")
public class FileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File file = new File(getServletContext().getRealPath("/") + "/WEB-INF/classes/files/");


        if(file.exists()) resp.getWriter().write("EXIST");
        for (File f : file.listFiles()){
            resp.getWriter().write(f.getName());
        }
        Path currentRelativePath = Paths.get("");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("file");
        part.write(part.getSubmittedFileName());
    }
}
