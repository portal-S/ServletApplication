package com.portal.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

@WebServlet(urlPatterns = {"/files"})
@MultipartConfig()
public class FileServlet extends HttpServlet {

    private File file;

    private  String PATH;

    @Override
    public void init() throws ServletException {
        PATH = getServletContext().getRealPath("/" + "WEB-INF/classes/files" + File.separator);
        file = new File(PATH);
       // file = new File(getServletContext().getRealPath("/") + "\\WEB-INF\\classes\\files\\");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(file.exists()) resp.getWriter().write("EXIST");
        for (File f : file.listFiles()){
            resp.getWriter().write(f.getName());
            resp.getWriter().write("\n");
        }
        resp.getWriter().write("\n");
        resp.getWriter().write(file.getAbsolutePath());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("file-name");
        String filename = part.getSubmittedFileName();
        String path = getServletContext().getRealPath(PATH + filename);
        read(part.getInputStream(), path);
        resp.getWriter().write(path);
        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer writer = resp.getWriter();
        writer.write(req.getParameter("file-delete-name"));
        File file = new File(PATH + req.getParameter("file-delete-name"));
        writer.write("\n");
        if(file.exists()) writer.write("EXIST");
        file.delete();
        doGet(req,resp);
    }

    private void read(InputStream stream, String path){
        try {
            byte[] bytes = new byte[stream.available()];
            stream.read();
            FileOutputStream outputStream = new FileOutputStream(path);
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
