package com.portal.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet(urlPatterns = {"/files"})
public class FileServlet extends HttpServlet {

    private File file;

    @Override
    public void init() throws ServletException {
        file = new File(getServletContext().getRealPath("/") + "/WEB-INF/classes/files/");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if(file.exists()) resp.getWriter().write("EXIST");
        for (File f : file.listFiles()){
            resp.getWriter().write(f.getName());
        }
        resp.getWriter().write("\n");
        resp.getWriter().write(file.getAbsolutePath());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //  Part part = req.getPart("file");
        for(Part p : req.getParts()){
            resp.getWriter().write(p.getName());
        }
        /*File file1 = new File(this.file + "/" + part.getName());
        file1.createNewFile();
        FileOutputStream stream = new FileOutputStream(file1);
        while (part.getInputStream().read() != -1){
            stream.write(part.getInputStream().read());
        }
        stream.close();*/
    }
}
